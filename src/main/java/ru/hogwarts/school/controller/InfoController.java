package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.PortService;

@RestController
public class InfoController {
    private final PortService portService;

    public InfoController(PortService portService) {
        this.portService = portService;
    }

    @GetMapping("info/port")
    public String getPort() {
        return portService.getPort();
    }
}
