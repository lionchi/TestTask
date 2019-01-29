package ru.gavrilov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gavrilov.dto.JobDto;
import ru.gavrilov.model.Job;
import ru.gavrilov.model.TypeTask;
import ru.gavrilov.repository.JobRepository;

import java.text.SimpleDateFormat;
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
                .map(jobDto -> new Job(jobDto.getId(), TypeTask.valueOf(jobDto.getType().toUpperCase()), jobDto.getUser(),
                        jobDto.getDevice(), jobDto.getAmount(), new Date()))
                .collect(Collectors.toList());
        jobRepository.saveAll(jobs);
    }

    @Transactional(readOnly = true)
    public List<JobDto> getStatistics(String nameUser, String type, String nameDevice, Date timeFrom, Date timeTo) {
        TypeTask typeTask = type == null ? null : TypeTask.valueOf(type.toUpperCase());
        return jobRepository.findAllBy(nameUser, typeTask, nameDevice, timeFrom, timeTo).stream()
                .map(job -> {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    return new JobDto(job.getId(), job.getType().getNameType(), job.getUser(), job.getDevice(), job.getAmount(), simpleDateFormat.format(job.getTime()));
                })
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getTotalNumberOfPages(List<JobDto> jobDtos) {
        Map<String, Integer> map = new HashMap<>();
        for (JobDto job : jobDtos) {
            if (!map.containsKey(job.getUser())) {
                Integer allAmountForUser = jobDtos.stream()
                        .filter(job1 -> job1.getUser().equals(job.getUser()))
                        .map(JobDto::getAmount)
                        .reduce((integer, integer2) -> integer + integer2)
                        .orElse(0);
                map.put(job.getUser(), allAmountForUser);
            }
        }
        return map;
    }
}
