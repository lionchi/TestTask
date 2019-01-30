package ru.gavrilov.dto;

import ru.gavrilov.model.Job;
import ru.gavrilov.model.TypeTask;

import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlRootElement
public class JobDto {
    private Long id;

    private TypeTask type;

    private String user;

    private String device;

    private Integer amount;

    private String time;

    public JobDto() {
    }

    public JobDto(Long id, TypeTask type, String user, String device, Integer amount, String time) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.device = device;
        this.amount = amount;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeTask getType() {
        return type;
    }

    public void setType(TypeTask type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static JobDto jobAsJobDto(Job job) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return new JobDto(job.getId(), job.getType(), job.getUser(), job.getDevice(), job.getAmount(),
                simpleDateFormat.format(job.getTime()));
    }
}
