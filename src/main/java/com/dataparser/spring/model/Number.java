package com.dataparser.spring.model;

import com.opencsv.bean.CsvBindByName;

public class Number {

    @CsvBindByName
    private Long id;

    @CsvBindByName
    private String number;

    @CsvBindByName
    private String definition;

    public Number(Long id, String number, String definition) {
        this.id = id;
        this.number = number;
        this.definition = definition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
