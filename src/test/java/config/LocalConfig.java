package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:local.properties", "classpath:local.properties.example"})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface LocalConfig extends Config {

    @Key("local.appiumUrl") String appiumUrl();
    @Key("local.deviceName") String deviceName();
    @Key("local.platformVersion") @DefaultValue("") String platformVersion();
    @Key("local.udid") @DefaultValue("") String deviceUdid();
    @Key("local.appPackage") String appPackage();
    @Key("local.appActivity") String appActivity();
    @Key("local.appPath") String appPath();
    @Key("local.appUrl") @DefaultValue("") String appUrl();
}
