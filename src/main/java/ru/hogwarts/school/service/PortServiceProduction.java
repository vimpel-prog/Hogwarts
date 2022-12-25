package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("Production")
public class PortServiceProduction implements PortService{
    @Value("${server.port}")
    String port;
    @Override
    public String getPort() {
        return port;
    }

}
