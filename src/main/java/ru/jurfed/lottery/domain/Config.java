package ru.jurfed.lottery.domain;


import javax.persistence.*;

@Entity
@Table(name = "PARAMETERS")
public class Config {


    public Config() {
    }

    @Id
    @Column(name = "parameter_name")
    private String name;

    @Column(name = "parameter_value")
    private String parameterValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }
}
