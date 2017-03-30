package com.example.pcmazzters.classes;

public class CompleteOrder {
    int id;
    String name;
    String problem;
    String customer;
    String phone;
    String email;
    String date;
    String user;
    double cost;

    public CompleteOrder(int id, String name, String problem, String customer, String phone, String email, String date, String user, double cost) {
        this.id = id;
        this.name = name;
        this.problem = problem;
        this.customer = customer;
        this.phone = phone;
        this.email = email;
        this.date = date;
        this.user = user;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return  name +
                "\n\nOrder ID: " + id +
                "\n\nproblem: " + problem +
                "\n\nCustomer: " + customer  +
                "\n\nCustomer's phone: " + phone +
                "\n\nCustomer's email: " + email +
                "\n\nCompletement date: " + date +
                "\n\nUser:  " + user +
                "\n\nTotal cost: " + cost/10000 + " rub";
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public String getUser() {
        return user;
    }

    public double getCost() {
        return cost;
    }
}
