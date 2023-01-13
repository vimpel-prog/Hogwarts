package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.PortService;

import java.util.stream.Stream;

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

    @GetMapping("info/sum")
    public int getTestSum() {
        int sum = Stream.iterate(1, a -> a +1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, Integer::sum);
        return sum;
    }
}
