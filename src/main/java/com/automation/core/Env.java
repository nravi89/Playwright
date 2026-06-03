package com.automation.core;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class Env {
    private static Properties prop;

    static{
        prop = new Properties();
        try {
            log.debug("Loading default application.properties file.");
            prop.load(Env.class.getClassLoader().getResourceAsStream("application.properties"));

            String env = System.getenv(prop.getProperty("env.var.name","env"));
            if(env!=null && !env.isBlank() && !env.isEmpty() && !env.trim().equalsIgnoreCase("default")){
                log.debug("Loading environment properties file. Environment :: "+env);
                Properties envProperties = new Properties();
                envProperties.load(Env.class.getClassLoader().getResourceAsStream(env.trim()+"_application.properties"));
                prop.putAll(envProperties);
                System.out.println(prop.getProperty("slowmo")+" "+prop.getProperty("base.url"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final boolean HEADLESS = Boolean.parseBoolean(Env.prop.getProperty("headless","true"));
    public static final int SLOWMO = Integer.parseInt(Env.prop.getProperty("slowmo","0"));

}
