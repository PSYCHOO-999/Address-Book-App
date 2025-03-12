package com.bridgelabz.address_book.services;

import com.bridgelabz.address_book.dto.AddressBookDTO;
import com.bridgelabz.address_book.entity.AddressBook;
import com.bridgelabz.address_book.repo.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    public List<AddressBook> getAllEntries() {
        return repository.findAll();
    }

    public Optional<AddressBook> getEntryById(Long id) {
        return repository.findById(id);
    }

    public AddressBook addEntry(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook(
                addressBookDTO.getName(),
                addressBookDTO.getEmail(),
                addressBookDTO.getPhone(),
                addressBookDTO.getAddresses()
        );
        return repository.save(addressBook);
    }

    public Optional<AddressBook> updateEntry(Long id, AddressBookDTO addressBookDTO) {
        Optional<AddressBook> existingEntry = repository.findById(id);
        if (existingEntry.isPresent()) {
            AddressBook addressBook = existingEntry.get();
            addressBook.setName(addressBookDTO.getName());
            addressBook.setEmail(addressBookDTO.getEmail());
            addressBook.setPhone(addressBookDTO.getPhone());
            addressBook.setAddresses(addressBookDTO.getAddresses());
            return Optional.of(repository.save(addressBook));
        }
        return Optional.empty();
    }

    public boolean deleteEntry(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
