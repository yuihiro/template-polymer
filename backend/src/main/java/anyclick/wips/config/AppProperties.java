package anyclick.wips.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public class AppProperties {

	public static String name;
	public static String version;
	public static boolean debug;
	public static String buildTime;

	public static final String CRYOTO_KEY = "ANYCLICK-KO-100001";

	public void setName(String name) {
		this.name = name;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void setBuildTime(String time) {
		this.buildTime = time;
	}
}
