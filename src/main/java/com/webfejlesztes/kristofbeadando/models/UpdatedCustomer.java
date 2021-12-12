package com.webfejlesztes.kristofbeadando.models;

public class UpdatedCustomer {

    public UpdatedCustomer(){}
    public UpdatedCustomer(String _id, String _firstName, String _lastName, String _age){
        this.id = _id;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.Age = _age;
    }

    private String id;

    private String firstName;
    private String lastName;
    private String Age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
