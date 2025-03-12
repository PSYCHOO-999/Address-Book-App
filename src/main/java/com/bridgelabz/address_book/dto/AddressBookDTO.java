package com.bridgelabz.address_book.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class AddressBookDTO {
    private String name;
    private String email;
    private String phone;
    private List<String> addresses;
}
