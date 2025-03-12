package com.bridgelabz.address_book.service;

import com.bridgelabz.address_book.dto.AddressBookDTO;
import com.bridgelabz.address_book.entity.AddressBook;
import com.bridgelabz.address_book.repo.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j  // Enables logging
@Service
public class AddressBookService {

    private final AddressBookRepository repository;

    public AddressBookService(AddressBookRepository repository) {
        this.repository = repository;
    }

    public List<AddressBook> getAllEntries() {
        log.info("Fetching all address book entries.");
        return repository.findAll();
    }

    public Optional<AddressBook> getEntryById(Long id) {
        log.info("Fetching entry with ID: {}", id);
        return repository.findById(id);
    }

    public AddressBook addEntry(AddressBookDTO addressBookDTO) {
        log.info("Adding new entry: {}", addressBookDTO);
        AddressBook entry = new AddressBook(
                addressBookDTO.getName(),
                addressBookDTO.getPhoneNumber(),
                addressBookDTO.getEmail(),
                addressBookDTO.getAddress()  // Added address field
        );
        return repository.save(entry);
    }

    public Optional<AddressBook> updateEntry(Long id, AddressBookDTO addressBookDTO) {
        return repository.findById(id).map(existingEntry -> {
            log.info("Updating entry with ID: {}", id);
            existingEntry.setName(addressBookDTO.getName());
            existingEntry.setPhoneNumber(addressBookDTO.getPhoneNumber());
            existingEntry.setEmail(addressBookDTO.getEmail());
            existingEntry.setAddress(addressBookDTO.getAddress());  // Updated address
            return repository.save(existingEntry);
        });
    }

    public boolean deleteEntry(Long id) {
        if (repository.existsById(id)) {
            log.info("Deleting entry with ID: {}", id);
            repository.deleteById(id);
            return true;
        } else {
            log.warn("Attempted to delete non-existing entry with ID: {}", id);
            return false;
        }
    }
}
