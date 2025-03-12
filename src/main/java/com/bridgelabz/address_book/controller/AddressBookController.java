package com.bridgelabz.address_book.controller;

import com.bridgelabz.address_book.dto.AddressBookDTO;
import com.bridgelabz.address_book.entity.AddressBook;
import com.bridgelabz.address_book.service.AddressBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private final AddressBookService service;

    public AddressBookController(AddressBookService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<AddressBook> addEntry(@RequestBody AddressBookDTO addressBookDTO) {
        AddressBook savedEntry = service.addEntry(addressBookDTO);
        return ResponseEntity.ok(savedEntry);
    }

    @GetMapping("/getAll")
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long id) {
        boolean deleted = service.deleteEntry(id);
        if (deleted) {
            return ResponseEntity.ok("Entry deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Entry not found");
        }
    }
}
