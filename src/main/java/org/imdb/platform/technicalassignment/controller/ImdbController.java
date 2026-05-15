package org.imdb.platform.technicalassignment.controller;

import lombok.RequiredArgsConstructor;
import org.imdb.platform.technicalassignment.model.dto.ServiceResponse;
import org.imdb.platform.technicalassignment.service.ImdbService;
import org.imdb.platform.technicalassignment.util.PaginationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ImdbController {

    private final ImdbService service;

    @GetMapping("v1/same-alive-director-writer")
    public Page<ServiceResponse>
    sameDirectorWriterAlive(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ServiceResponse> titleResponses = service.sameDirectorWriterAlive();
        return PaginationUtils.paginate(titleResponses, pageable);
    }

    @GetMapping("v1/common-titles")
    public List<ServiceResponse>
    commonTitles(
            @RequestParam String actor1,
            @RequestParam String actor2
    ) {

        return service.commonTitles(
                actor1,
                actor2
        );
    }

    @GetMapping("v1/genre/{genre}/best-titles")
    public Map<Short, ServiceResponse>
    bestTitles(
            @PathVariable("genre") String genre
    ) {

        return service.bestTitlesPerYear(
                genre
        );
    }
}