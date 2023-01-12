package ru.hogwarts.school.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!Production")
public class PortServiceTest implements PortService{

    @Override
    public String getPort() {
        return "1111";
    }
}
