package com.crm.ssm.entity;

import java.io.Serializable;
import java.util.Date;

public class SalPlan implements Serializable {
    private Integer plaId;

    private Integer plaChcId;

    private Date plaDate;

    private String plaTodo;

    private String plaResult;

    private static final long serialVersionUID = 1L;

    public Integer getPlaId() {
        return plaId;
    }

    public void setPlaId(Integer plaId) {
        this.plaId = plaId;
    }

    public Integer getPlaChcId() {
        return plaChcId;
    }

    public void setPlaChcId(Integer plaChcId) {
        this.plaChcId = plaChcId;
    }

    public Date getPlaDate() {
        return plaDate;
    }

    public void setPlaDate(Date plaDate) {
        this.plaDate = plaDate;
    }

    public String getPlaTodo() {
        return plaTodo;
    }

    public void setPlaTodo(String plaTodo) {
        this.plaTodo = plaTodo == null ? null : plaTodo.trim();
    }

    public String getPlaResult() {
        return plaResult;
    }

    public void setPlaResult(String plaResult) {
        this.plaResult = plaResult == null ? null : plaResult.trim();
    }
}