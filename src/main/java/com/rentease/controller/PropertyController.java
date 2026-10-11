package com.rentease.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentease.model.Property;
import com.rentease.repository.PropertyRepository;

@RestController
@RequestMapping("/property")
@CrossOrigin(origins = "*")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    // READ: Get All Properties (with optional keyword search)
    @GetMapping("/get")
    public List<Property> getAllProperties(@RequestParam(required = false) String keyword) {
        List<Property> all = propertyRepository.findAll();
        if (keyword != null && !keyword.trim().isEmpty()) {
            String lower = keyword.toLowerCase();
            return all.stream()
                .filter(p -> (p.getTitle() != null && p.getTitle().toLowerCase().contains(lower)) ||
                             (p.getLocation() != null && p.getLocation().toLowerCase().contains(lower)))
                .toList();
        }
        return all;
    }

    // CREATE: Add New Property
    @PostMapping("/add")
    public Property addProperty(@RequestBody Property property) {
        if (property.getStatus() == null || property.getStatus().isEmpty()) {
            property.setStatus("Available");
        }
        return propertyRepository.save(property);
    }

    // UPDATE: Update Property by ID
    @PutMapping("/update/{id}")
    public Property updateProperty(@PathVariable Long id, @RequestBody Property updatedData) {
        Optional<Property> optional = propertyRepository.findById(id);
        if (optional.isPresent()) {
            Property existing = optional.get();
            if (updatedData.getTitle() != null) existing.setTitle(updatedData.getTitle());
            if (updatedData.getCategory() != null) existing.setCategory(updatedData.getCategory());
            if (updatedData.getLocation() != null) existing.setLocation(updatedData.getLocation());
            if (updatedData.getRent() != null) existing.setRent(updatedData.getRent());
            if (updatedData.getContact() != null) existing.setContact(updatedData.getContact());
            if (updatedData.getStatus() != null) existing.setStatus(updatedData.getStatus());
            return propertyRepository.save(existing);
        }
        return null;
    }

    // DELETE: Delete Property by ID
    @DeleteMapping("/delete/{id}")
    public String deleteProperty(@PathVariable Long id) {
        if (propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
            return "Property with ID " + id + " deleted successfully.";
        }
        return "Property not found.";
    }
}
