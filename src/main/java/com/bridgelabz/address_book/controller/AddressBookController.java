package com.bridgelabz.address_book.controller;

import com.bridgelabz.address_book.dto.AddressBookDTO;
import com.bridgelabz.address_book.entity.AddressBook;
import com.bridgelabz.address_book.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private final AddressBookService service;

    public AddressBookController(AddressBookService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AddressBook>> getAllEntries() {
        log.info("Received request to fetch all entries.");
        List<AddressBook> entries = service.getAllEntries();
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getEntry(@PathVariable Long id) {
        log.info("Received request to fetch entry with ID: {}", id);
        Optional<AddressBook> entry = service.getEntryById(id);
        return entry.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Entry with ID: {} not found.", id);
                    return ResponseEntity.status(404).body("Entry not found");
                });
    }

    @PostMapping("/add")
    public ResponseEntity<AddressBook> addEntry(@RequestBody AddressBookDTO addressBookDTO) {
        log.info("Received request to add new entry: {}", addressBookDTO);
        AddressBook createdEntry = service.addEntry(addressBookDTO);
        return ResponseEntity.ok(createdEntry);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEntry(@PathVariable Long id, @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Received request to update entry with ID: {}", id);
        Optional<AddressBook> updatedEntry = service.updateEntry(id, addressBookDTO);
        return updatedEntry.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Entry with ID: {} not found for update.", id);
                    return ResponseEntity.status(404).body("Entry not found");
                });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long id) {
        log.info("Received request to delete entry with ID: {}", id);
        boolean isDeleted = service.deleteEntry(id);
        if (isDeleted) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            log.warn("Entry with ID: {} not found for deletion.", id);
            return ResponseEntity.status(404).body("Entry not found");
        }
    }
}
