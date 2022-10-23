package com.Vladislav.model;

public class Specialty {
    private int id;
    private String name;

    public Specialty(String name) {
        this.name = name;
    }

    public Specialty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + id + " name: " + name;
    }
}
