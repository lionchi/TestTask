package ru.gavrilov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gavrilov.dto.JobDto;
import ru.gavrilov.model.Job;
import ru.gavrilov.model.TypeTask;
import ru.gavrilov.repository.JobRepository;
import ru.gavrilov.service.JobService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private JobService jobService;

    @GetMapping("/statistics")
    private ResponseEntity<List<JobDto>> getStatistics(@RequestParam(value = "user", required = false) String nameUser,
                                                       @RequestParam(value = "type", required = false) TypeTask type,
                                                       @RequestParam(value = "device", required = false) String nameDevice,
                                                       @RequestParam(value = "timeFrom", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date timeFrom,
                                                       @RequestParam(value = "timeTo", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date timeTo) {
        return ResponseEntity.ok(jobService.getStatistics(nameUser, type, nameDevice, timeFrom, timeTo));
    }

    @PostMapping(value = "/jobs", consumes = {MediaType.TEXT_XML_VALUE})
    private ResponseEntity<Map<String, Integer>> setData(@RequestBody List<JobDto> jobDtos) {
        jobService.saveAll(jobDtos);
        Map<String, Integer> map = jobService.getTotalNumberOfPages(jobDtos);
        return ResponseEntity.ok(map);
    }
}
