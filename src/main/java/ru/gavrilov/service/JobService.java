package ru.gavrilov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gavrilov.dto.JobDto;
import ru.gavrilov.model.Job;
import ru.gavrilov.model.TypeTask;
import ru.gavrilov.repository.JobRepository;

import javax.transaction.Transactional;
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

    public List<JobDto> getStatistics(String nameUser, String type, String nameDevice, Date timeFrom, Date timeTo) {
        List<Job> allBy = jobRepository.findAllBy();
        return null;
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
