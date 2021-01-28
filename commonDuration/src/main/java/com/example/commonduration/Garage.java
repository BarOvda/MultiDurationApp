package com.example.commonduration;

import java.util.ArrayList;
import java.util.List;

public class Garage {

    private List<String> Cars = null ;
    private boolean open = true;
    private String address = "";
    private String name = "";

    public Garage() {

    }

    public List<String> getCars() {
        return Cars;
    }

    public Garage setCars(List<String> cars) {
        this.Cars = cars;
        return this;
    }

    public boolean isOpen() {
        return open;
    }

    public Garage setOpen(boolean open) {
        this.open = open;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Garage setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getName() {
        return name;
    }

    public Garage setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return
                "Cars=" + Cars +
                "\n, open=" + open +
                "\n, address='" + address + '\'' +
                "\n, name='" + name + '\'' +
                '}';
    }
}
