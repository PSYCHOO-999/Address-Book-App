package com.bridgelabz.address_book.controller;
import com.bridgelabz.address_book.entity.AddressBook;
import com.bridgelabz.address_book.repo.AddressBookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private final AddressBookRepository repository;

    public AddressBookController(AddressBookRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEntry(@RequestBody AddressBook entry) {
        repository.save(entry);
        return ResponseEntity.ok("Entry added successfully");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getEntry(@PathVariable Long id) {
        Optional<AddressBook> entry = repository.findById(id);
        return entry.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Entry not found"));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllEntries() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEntry(@PathVariable Long id, @RequestBody AddressBook updatedEntry) {
        if (repository.existsById(id)) {
            updatedEntry.setId(id);
            repository.save(updatedEntry);
            return ResponseEntity.ok("Entry updated successfully");
        }
        return ResponseEntity.status(404).body("Entry not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Entry deleted successfully");
        }
        return ResponseEntity.status(404).body("Entry not found");
    }
}

