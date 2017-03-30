package com.example.pcmazzters.classes;

public class CompleteOperations {
    String description;
    double cost;

    public CompleteOperations(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return  description +
                "\nCost=" + cost/10000 + " rub";
    }
}
