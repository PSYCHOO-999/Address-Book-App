package com.bridgelabz.address_book.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    @ElementCollection
    private List<String> addresses;

    public AddressBook() {
    }

    public AddressBook(String name, String email, String phone, List<String> addresses) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.addresses = addresses;
    }
}
