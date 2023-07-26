package org.example.usersApp.model;

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

    public User(Long userId, String firstName, String lastName, String age) {
    }
}

