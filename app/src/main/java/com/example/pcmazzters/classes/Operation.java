package com.example.pcmazzters.classes;

public class Operation {

    int id_operation;
    String model;
    String description;
    double cost;

    public Operation(int id_operation, String model, String description, double cost) {
        this.id_operation = id_operation;
        this.model = model;
        this.description = description;
        this.cost = cost;
    }

    public int getId_operation() {
        return id_operation;
    }

    public String getModel() {
        return model;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id_operation=" + id_operation +
                ", model='" + model + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
