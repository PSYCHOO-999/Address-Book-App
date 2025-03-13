package com.bridgelabz.address_book.controller;

import com.bridgelabz.address_book.dto.AddressBookDTO;
import com.bridgelabz.address_book.entity.AddressBook;
import com.bridgelabz.address_book.services.AddressBookService;
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
        log.debug("Received request to fetch all entries.");
        return ResponseEntity.ok(service.getAllEntries());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getEntry(@PathVariable Long id) {
        log.info("Fetching entry with ID: {}", id);
        Optional<AddressBook> entry = service.getEntryById(id);
        return entry.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Entry not found"));
    }

    @PostMapping("/add")
    public ResponseEntity<AddressBook> addEntry(@RequestBody AddressBookDTO addressBookDTO) {
        log.info("Adding new entry: {}", addressBookDTO.getName());
        return ResponseEntity.ok(service.addEntry(addressBookDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEntry(@PathVariable Long id, @RequestBody AddressBookDTO addressBookDTO) {
        log.warn("Updating entry with ID: {}", id);
        Optional<AddressBook> updatedEntry = service.updateEntry(id, addressBookDTO);
        return updatedEntry.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Entry not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long id) {
        log.error("Deleting entry with ID: {}", id);
        boolean isDeleted = service.deleteEntry(id);
        return isDeleted ? ResponseEntity.ok("Deleted successfully") :
                ResponseEntity.status(404).body("Entry not found");
    }
}
