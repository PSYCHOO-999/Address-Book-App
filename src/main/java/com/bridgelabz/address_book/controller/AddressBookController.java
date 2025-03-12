package com.bridgelabz.address_book.controller;

import com.bridgelabz.address_book.dto.AddressBookDTO;
import com.bridgelabz.address_book.entity.AddressBook;
import com.bridgelabz.address_book.services.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    @GetMapping("/get")
    public ResponseEntity<List<AddressBook>> getAllEntries() {
        return ResponseEntity.ok(service.getAllEntries());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getEntry(@PathVariable Long id) {
        Optional<AddressBook> entry = service.getEntryById(id);
        return entry.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Entry not found"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEntry(@PathVariable Long id, @RequestBody AddressBookDTO addressBookDTO) {
        Optional<AddressBook> updatedEntry = service.updateEntry(id, addressBookDTO);
        return updatedEntry.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Entry not found"));
    }


    @PostMapping("/add")
    public ResponseEntity<AddressBook> addEntry(@RequestBody AddressBookDTO addressBookDTO) {
        return ResponseEntity.ok(service.addEntry(addressBookDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable Long id) {
        return service.deleteEntry(id)
                ? ResponseEntity.ok("{\"message\": \"Entry deleted successfully\"}")
                : ResponseEntity.status(404).body("{\"error\": \"Entry not found\"}");
    }
}
