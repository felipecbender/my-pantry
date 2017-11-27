package com.bender.mypantry.model;

import java.io.Serializable;

/**
 * Created by Felipe Bender on 19/11/2017.
 */

public class Food implements Serializable {
    private Long id;
    private String name;
    private double value;
    private double qtd;
    private String type;
    private Long pantryId;

    public Food() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPantryId() {
        return pantryId;
    }

    public void setPantryId(Long pantryId) {
        this.pantryId = pantryId;
    }

    @Override
    public String toString() {
        return name;
    }
}