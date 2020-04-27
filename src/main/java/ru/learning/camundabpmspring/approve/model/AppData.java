package ru.learning.camundabpmspring.approve.model;

import lombok.Data;

import java.io.Serializable;

//результат выборки
@Data
public class AppData implements Serializable {
    private String processInstanceId;
    private Long amount;
    private String description;
    private String id;
    private String comment;
    private Boolean acceptResult;
}
