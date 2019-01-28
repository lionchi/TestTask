package ru.gavrilov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gavrilov.dto.JobDto;
import ru.gavrilov.model.Job;
import ru.gavrilov.model.TypeTask;
import ru.gavrilov.repository.JobRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/statistics")
    private void getStatistics(@RequestParam(value = "user", required = false) String nameUser,
                               @RequestParam(value = "type", required = false) String type,
                               @RequestParam(value = "device", required = false) String nameDevice) {

    }

    @PostMapping(value = "/jobs", consumes = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    private ResponseEntity<List<?>> setData(@RequestBody  List<JobDto> jobDtos) {
        List<Job> jobs = jobDtos.stream()
                .map(jobDto -> new Job(TypeTask.valueOf(jobDto.getType().toUpperCase()), jobDto.getUser(), jobDto.getDevice(), jobDto.getAmount()))
                .collect(Collectors.toList());
        jobRepository.saveAll(jobs);
        List<Job> jobList = jobRepository.findAllBy();
        return ResponseEntity.ok(null);
    }
}
