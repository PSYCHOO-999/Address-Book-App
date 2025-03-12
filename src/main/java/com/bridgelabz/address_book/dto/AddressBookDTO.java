package com.bridgelabz.address_book.dto;

public class AddressBookDTO {
    private String name;
    private String phoneNumber;

    public AddressBookDTO() {}

    public AddressBookDTO(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

