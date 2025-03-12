package com.bridgelabz.address_book.controller;

import com.bridgelabz.address_book.dto.AddressBookDTO;
import com.bridgelabz.address_book.entity.AddressBook;
import com.bridgelabz.address_book.services.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    @PostMapping("/add")
    public ResponseEntity<String> addEntry(@RequestBody AddressBookDTO dto) {
        return service.addEntry(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressBook>> getAllEntries() {
        return service.getAllEntries();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEntryById(@PathVariable Long id) {
        return service.getEntryById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long id) {
        return service.deleteEntry(id);
    }
}
