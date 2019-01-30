package ru.gavrilov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gavrilov.dto.JobDto;
import ru.gavrilov.model.Job;
import ru.gavrilov.model.TypeTask;
import ru.gavrilov.repository.JobRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public void saveAll(List<JobDto> jobDtos) {
        List<Job> jobs = jobDtos.stream()
                .map(Job::jobDtoAsJob)
                .collect(Collectors.toList());
        jobRepository.saveAll(jobs);
    }

    @Transactional(readOnly = true)
    public List<JobDto> getStatistics(String nameUser, TypeTask type, String nameDevice, Date timeFrom, Date timeTo) {
        return jobRepository.findAllBy(nameUser, type, nameDevice, timeFrom, timeTo).stream()
                .map(JobDto::jobAsJobDto)
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getTotalNumberOfPages(List<JobDto> jobDtos) {
        Map<String, Integer> map = new HashMap<>();
        for (JobDto job : jobDtos) {
            map.computeIfAbsent(job.getUser(), (user) -> calculations(user, jobDtos));
        }
        return map;
    }

    private Integer calculations(String user, List<JobDto> jobDtos) {
        return jobDtos.stream()
                .filter(job1 -> job1.getUser().equals(user))
                .map(JobDto::getAmount)
                .reduce((integer, integer2) -> integer + integer2)
                .orElse(0);
    }
}
