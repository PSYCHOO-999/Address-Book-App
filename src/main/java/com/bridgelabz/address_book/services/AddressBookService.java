package com.bridgelabz.address_book.services;
import com.bridgelabz.address_book.dto.AddressBookDTO;
import com.bridgelabz.address_book.entity.AddressBook;
import com.bridgelabz.address_book.repo.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    public ResponseEntity<String> addEntry(AddressBookDTO dto) {
        AddressBook entry = new AddressBook(dto.getName(), dto.getPhoneNumber());
        repository.save(entry);
        return ResponseEntity.ok("Entry added successfully");
    }

    public ResponseEntity<List<AddressBook>> getAllEntries() {
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<?> getEntryById(Long id) {
        Optional<AddressBook> entry = repository.findById(id);
        return entry.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Entry not found"));
    }

    public ResponseEntity<String> deleteEntry(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Entry deleted successfully");
        }
        return ResponseEntity.status(404).body("Entry not found");
    }
}
