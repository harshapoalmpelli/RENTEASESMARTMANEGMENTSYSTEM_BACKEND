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
import org.springframework.web.bind.annotation.RestController;

import com.rentease.model.Tenant;
import com.rentease.repository.TenantRepository;

@RestController
@RequestMapping("/tenant")
@CrossOrigin(origins = "*")
public class TenantController {

    @Autowired
    private TenantRepository tenantRepository;

    // READ: Get All Tenants
    @GetMapping("/get")
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    // CREATE: Register New Tenant
    @PostMapping("/add")
    public Tenant addTenant(@RequestBody Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    // UPDATE: Update Tenant by ID
    @PutMapping("/update/{id}")
    public Tenant updateTenant(@PathVariable Long id, @RequestBody Tenant updatedData) {
        Optional<Tenant> optional = tenantRepository.findById(id);
        if (optional.isPresent()) {
            Tenant existing = optional.get();
            if (updatedData.getName() != null) existing.setName(updatedData.getName());
            if (updatedData.getEmail() != null) existing.setEmail(updatedData.getEmail());
            if (updatedData.getPhone() != null) existing.setPhone(updatedData.getPhone());
            return tenantRepository.save(existing);
        }
        return null;
    }

    // DELETE: Delete Tenant by ID
    @DeleteMapping("/delete/{id}")
    public String deleteTenant(@PathVariable Long id) {
        if (tenantRepository.existsById(id)) {
            tenantRepository.deleteById(id);
            return "Tenant with ID " + id + " deleted successfully.";
        }
        return "Tenant not found.";
    }
}
