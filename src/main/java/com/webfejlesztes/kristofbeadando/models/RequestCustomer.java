package com.webfejlesztes.kristofbeadando.models;

public class RequestCustomer {

    public RequestCustomer(){}

    public RequestCustomer(String _firstName, String _lastName, String _age){
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.Age = _age;
    }
    private String firstName;
    private String lastName;
    private String Age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }
}
