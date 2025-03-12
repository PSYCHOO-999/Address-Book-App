package com.bridgelabz.address_book.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @PostMapping("/add")
    public String addEntry() {
        return "Entry added successfully";
    }

    @GetMapping("/get/{id}")
    public String getEntry(@PathVariable int id) {
        return "Fetching entry for ID: " + id;
    }

    @PutMapping("/update/{id}")
    public String updateEntry(@PathVariable int id) {
        return "Entry with ID " + id + " updated successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEntry(@PathVariable int id) {
        return "Entry with ID " + id + " deleted successfully";
    }
}
