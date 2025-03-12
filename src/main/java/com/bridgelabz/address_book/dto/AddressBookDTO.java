package com.bridgelabz.address_book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressBookDTO {
    private String name;
    private String email;
    private String phone;
    private List<String> addresses;
}
