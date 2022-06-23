package com.esn.backend.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class TestConfig {

    private static Config conf = ConfigFactory.load();

    public static String getBase(){
        return conf.getString("app.base");
    }
}
