package com.ef.services;

import com.ef.entities.Config;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ConfigService {
    public Config getInstance(String[] args) {
        Config config = new Config();

        Arrays.asList(args).stream().forEach(arg -> {
            String[] aArg = arg.split("=");
            String key = aArg[0];
            String val = aArg[1];

            switch(key) {
                case "--startDate":
                    config.setStartDate(val);
                    break;
                case "--duration":
                    config.setDuration(val);
                    break;
                case "--accesslog":
                    config.setAccesslog(val);
                    break;
                case "--threshold":
                    config.setThreshold(Integer.valueOf(val));
                    break;
            }
        });

        return config;
    }
}
