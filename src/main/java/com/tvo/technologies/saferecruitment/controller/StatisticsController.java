package com.tvo.technologies.saferecruitment.controller;

import com.tvo.technologies.saferecruitment.model.statistics.Statistics;
import com.tvo.technologies.saferecruitment.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<Statistics> getStatistics(){
        return ResponseEntity.ok(this.statisticsService.getStatistics());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Statistics> getStatistics(@PathVariable String userId){
        return ResponseEntity.ok(this.statisticsService.getUserStatistics(userId));
    }
}
