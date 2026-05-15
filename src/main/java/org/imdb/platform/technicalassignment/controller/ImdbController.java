package org.imdb.platform.technicalassignment.controller;

import lombok.RequiredArgsConstructor;
import org.imdb.platform.technicalassignment.model.dto.TitleResponse;
import org.imdb.platform.technicalassignment.service.ImdbService;
import org.imdb.platform.technicalassignment.util.PaginationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ImdbController {

    private final ImdbService service;

    @GetMapping("/same-director-writer")
    public Page<TitleResponse>
    sameDirectorWriterAlive(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<TitleResponse> titleResponses= service.sameDirectorWriterAlive();
        return PaginationUtils.paginate(titleResponses,pageable);
    }

    @GetMapping("/common-titles")
    public List<TitleResponse>
    commonTitles(
            @RequestParam String actor1,
            @RequestParam String actor2
    ) {

        return service.commonTitles(
                actor1,
                actor2
        );
    }

    @GetMapping("/best-titles")
    public Map<Short, TitleResponse>
    bestTitles(
            @RequestParam String genre
    ) {

        return service.bestTitlesPerYear(
                genre
        );
    }
}