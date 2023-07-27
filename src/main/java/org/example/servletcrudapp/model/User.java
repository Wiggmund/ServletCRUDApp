package org.example.servletcrudapp.model;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;

    public User(String first_name, String last_name, Integer age) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
