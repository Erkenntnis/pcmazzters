package com.example.pcmazzters.classes;

public class NewOrder {
    int id;
    String model;
    String problem;
    String customer;
    String phone;
    String email;
    String date;


    public NewOrder(int id, String model, String problem, String customer, String phone, String email, String date) {
        this.id = id;
        this.model = model;
        this.problem = problem;
        this.customer = customer;
        this.phone = phone;
        this.email = email;
        this.date = date;
    }

    @Override
    public String toString() {
        return  model+
                "\n\nOrder ID: " + id +
                "\n\nProblem: " + problem +
                "\n\nCustomer: " + customer +
                "\n\nCustomer's phone: " + phone +
                "\n\nCustomer's email: " + email +
                "\n\nRegistration date: " + date;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getProblem() {
        return problem;
    }

    public String getCustomer() {
        return customer;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }
}
