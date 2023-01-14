package com.webservice.authors.config;

import com.webservice.authors.service.interfaces.AppEnvironment;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevEnvironment implements AppEnvironment {
    @Override
    public String name() {
        return "dev";
    }
}
