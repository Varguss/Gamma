package ru.gamma_station.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties properties = new Properties();

    static {
        ClassPathResource classPathResource = new ClassPathResource("application.properties");

        try {
            properties.load(classPathResource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();

            // default values
            properties.put("cache_update_delay", "3600000");
            properties.put("showing_ban_delay", "1800000");
        }
    }

    public static Long getCacheDelay() {
        return Long.parseLong(properties.getProperty("cache_update_delay"));
    }

    public static Long getBanDelay() {
        return Long.parseLong(properties.getProperty("showing_ban_delay"));
    }

    public static Long getVisitDelay() { return Long.parseLong(properties.getProperty("home_visit_logging_delay")); }

    public static Integer getBanhubRequestsLimit() { return Integer.parseInt(properties.getProperty("banhub_requests_limit")); }
}
