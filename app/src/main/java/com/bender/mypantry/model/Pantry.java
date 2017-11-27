package com.bender.mypantry.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bender on 21/11/17.
 */

public class Pantry implements Serializable {

    private Long id;
    private String name;
    private String supermarket;
    private List<Food> food;

    public Pantry() {
    }

    public Pantry(Long id, String name, List<Food> food, String supermarket) {
        this.id = id;
        this.name = name;
        this.food = food;
        this.supermarket = supermarket;
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

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public String getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }

    @Override
    public String toString() {
        return name;
    }
}
