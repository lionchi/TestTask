package ru.gavrilov.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeTask type;

    @Column(name = "user" /*unique = true*/)
    private String user;

    @Column(name = "device")
    private String device;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "time")
    private Date time;

    public Job() {
    }

    public Job(Long id, TypeTask type, String user, String device, Integer amount, Date date) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.device = device;
        this.amount = amount;
        this.time = date;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
