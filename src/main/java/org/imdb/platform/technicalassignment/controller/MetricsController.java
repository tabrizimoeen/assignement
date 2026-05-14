package org.imdb.platform.technicalassignment.controller;

import org.imdb.platform.technicalassignment.config.RequestCounterFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    @GetMapping("/metrics/requests")
    public long requests() {

        return RequestCounterFilter
                .COUNTER
                .get();
    }
}
