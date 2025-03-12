package com.bridgelabz.address_book.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "address_book")
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String email; // Ensure this field exists in Entity

    // Default Constructor
    public AddressBook() {}

    // Constructor
    public AddressBook(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }  // Fixed Missing Getter
    public void setEmail(String email) { this.email = email; }  // Fixed Missing Setter
}
