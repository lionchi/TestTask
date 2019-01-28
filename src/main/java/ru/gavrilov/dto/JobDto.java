package ru.gavrilov.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JobDto {
    private Long id;

    private String type;

    private String user;

    private String device;

    private Integer amount;

    public JobDto() {
    }

    public JobDto(Long id, String type, String user, String device, Integer amount) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.device = device;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
}
