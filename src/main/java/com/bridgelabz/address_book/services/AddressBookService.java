package com.bridgelabz.address_book.service;

import com.bridgelabz.address_book.dto.AddressBookDTO;
import com.bridgelabz.address_book.entity.AddressBook;
import com.bridgelabz.address_book.repo.AddressBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    private final AddressBookRepository repository;

    public AddressBookService(AddressBookRepository repository) {
        this.repository = repository;
    }

    public AddressBook addEntry(AddressBookDTO addressBookDTO) {
        AddressBook newEntry = new AddressBook();
        newEntry.setName(addressBookDTO.getName());
        newEntry.setPhoneNumber(addressBookDTO.getPhoneNumber());
        newEntry.setEmail(addressBookDTO.getEmail()); // Ensure Email is Set
        return repository.save(newEntry);
    }

    public List<AddressBook> getAllEntries() {
        return repository.findAll();
    }

    public Optional<AddressBook> getEntryById(Long id) {
        return repository.findById(id);
    }

    public Optional<AddressBook> updateEntry(Long id, AddressBookDTO addressBookDTO) {
        return repository.findById(id).map(existingEntry -> {
            existingEntry.setName(addressBookDTO.getName());
            existingEntry.setPhoneNumber(addressBookDTO.getPhoneNumber());
            existingEntry.setEmail(addressBookDTO.getEmail()); // Ensure Email is Updated
            return repository.save(existingEntry);
        });
    }

    public boolean deleteEntry(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
