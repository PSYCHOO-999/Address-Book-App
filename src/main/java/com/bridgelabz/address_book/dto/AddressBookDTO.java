package com.bridgelabz.address_book.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressBookDTO {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;  // Added address field
}
