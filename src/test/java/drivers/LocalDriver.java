package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import static config.ConfigProvider.localConfig;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class LocalDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        UiAutomator2Options options = createOptions().merge(capabilities);
        AndroidDriver driver = new AndroidDriver(getAppiumServerUrl(), options);
        driver.setSetting(Setting.ALLOW_INVISIBLE_ELEMENTS, true);
        return driver;
    }

    private UiAutomator2Options createOptions() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setDeviceName(localConfig.deviceName())
                .setAppPackage(localConfig.appPackage())
                .setAppActivity(localConfig.appActivity())
                .setFullReset(true)
                .setDisableWindowAnimation(true)
                .setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(120));
        if (!localConfig.platformVersion().isBlank()) {
            options.setPlatformVersion(localConfig.platformVersion());
        }
        if (!localConfig.deviceUdid().isBlank()) {
            options.setUdid(localConfig.deviceUdid());
        }
        options.setApp(getAppPath());
        return options;
    }

    private URL getAppiumServerUrl() {
        try {
            return new URL(localConfig.appiumUrl());
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Некорректный local.appiumUrl: " + localConfig.appiumUrl(), e);
        }
    }

    private String getAppPath() {
        Path appPath = Path.of(localConfig.appPath()).toAbsolutePath();
        if (Files.isRegularFile(appPath)) {
            return appPath.toString();
        }
        if (Files.exists(appPath)) {
            throw new IllegalArgumentException("local.appPath должен указывать на APK-файл: " + appPath);
        }
        if (localConfig.appUrl().isBlank()) {
            throw new IllegalArgumentException("APK не найден: " + appPath
                    + ". Укажите local.appPath или прямую ссылку local.appUrl для скачивания.");
        }

        try {
            Files.createDirectories(appPath.getParent());
            Path download = Files.createTempFile(appPath.getParent(), "wikipedia-", ".part");
            try {
                URLConnection connection = new URL(localConfig.appUrl()).openConnection();
                connection.setConnectTimeout(15000);
                connection.setReadTimeout(60000);
                try (InputStream in = connection.getInputStream()) {
                    Files.copy(in, download, StandardCopyOption.REPLACE_EXISTING);
                }
                Files.move(download, appPath, StandardCopyOption.REPLACE_EXISTING);
            } finally {
                Files.deleteIfExists(download);
            }
        } catch (IOException e) {
            throw new AssertionError("Не удалось скачать APK из local.appUrl", e);
        }
        return appPath.toString();
    }
}
