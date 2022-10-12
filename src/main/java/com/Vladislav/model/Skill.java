package com.Vladislav.model;

public class Skill implements Comparable<Skill> {
    private int id;
    private String name;


    public Skill(String name) {
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

    @Override
    public int compareTo(Skill o) {
        return this.getId() - o.getId();
    }
}
