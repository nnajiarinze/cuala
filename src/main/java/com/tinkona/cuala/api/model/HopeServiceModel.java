package com.tinkona.cuala.api.model;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public class HopeServiceModel {
    private int id;
    private String name;

    public HopeServiceModel() {
    }

    public HopeServiceModel(int id, String name) {
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
}
