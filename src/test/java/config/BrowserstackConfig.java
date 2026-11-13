package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:.gradle/browserstack.properties", "classpath:config.properties"})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface BrowserstackConfig extends Config{

    @Key("browserstackUrl") String browserstackUrl();
    @Key("userName") String userName();
    @Key("accessKey") String accessKey();
    @Key("projectName") String projectName();
    @Key("buildName") String buildName();
    @Key("sessionName") String sessionName();
    @Key("browserstackDevice") String browserstackDevice();
    @Key("browserstackOsVersion") String browserstackOsVersion();
    @Key("appiumVersion") String appiumVersion();
    @Key("app") String app();
}
