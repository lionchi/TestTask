package ru.gavrilov;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponentsBuilder;
import ru.gavrilov.dto.JobDto;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MainControllerTest {
    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    private List<JobDto> jobDtos = new ArrayList<>();

    @Before
    public void setJobDtos() {
        JobDto jobDto1 = new JobDto(1L, "print", "user1", "device1", 10, null);
        JobDto jobDto2 = new JobDto(2L, "scan", "user1", "device1", 12, null);
        JobDto jobDto3 = new JobDto(3L, "fax", "user2", "device1", 5, null);
        jobDtos.add(jobDto1);
        jobDtos.add(jobDto2);
        jobDtos.add(jobDto3);
    }

    @Test
    public void setDataTest() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/xml");
        HttpEntity<List<JobDto>> request = new HttpEntity<>(jobDtos, httpHeaders);
        ResponseEntity<String> response = testRestTemplate
                .exchange("http://localhost:8080/api/jobs", HttpMethod.POST, request, String.class);
        String body = response.getBody();
        assertThat(body, notNullValue());
    }

    @Test
    public void getStatisticsTest() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/statistics")
                .queryParam("user", "user1")
                .queryParam("type", "scan")
                .queryParam("device", "device1");
                //.queryParam("timeFrom", "desc")
                //.queryParam("timeTo", "hello search");

        ResponseEntity<List<JobDto>> responseEntity = testRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<JobDto>>() {
                });
        List<JobDto> actualList = responseEntity.getBody();
        assertThat(actualList, hasSize(greaterThan(0)));
    }
}
