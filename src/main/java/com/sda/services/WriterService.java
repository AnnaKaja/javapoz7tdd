package com.sda.services;

public class WriterService {
    public String write(String name) {
        String nameToDisplay = name == null ? "my friend" : name;
        return "Hello, " + nameToDisplay + ".";

    }


}
