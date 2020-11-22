package com.kwgdev.projectmanagement.dto;

import com.kwgdev.projectmanagement.entities.Manager;

import java.util.Date;

public interface TimeChartData {

    public String getProjectName();
    public Date getStartDate();
    public Date getEndDate();
}
