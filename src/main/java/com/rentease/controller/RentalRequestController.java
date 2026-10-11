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

import com.rentease.model.RentalRequest;
import com.rentease.repository.RentalRequestRepository;

@RestController
@RequestMapping("/request")
@CrossOrigin(origins = "*")
public class RentalRequestController {

    @Autowired
    private RentalRequestRepository requestRepository;

    // READ: Get All Rental Requests
    @GetMapping("/get")
    public List<RentalRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    // CREATE: Submit Rental Request
    @PostMapping("/add")
    public RentalRequest createRequest(@RequestBody RentalRequest request) {
        if (request.getStatus() == null || request.getStatus().isEmpty()) {
            request.setStatus("PENDING");
        }
        return requestRepository.save(request);
    }

    // UPDATE: Update Request Status (PENDING, APPROVED, REJECTED)
    @PutMapping("/status/{id}")
    public RentalRequest updateStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<RentalRequest> optional = requestRepository.findById(id);
        if (optional.isPresent()) {
            RentalRequest existing = optional.get();
            existing.setStatus(status);
            return requestRepository.save(existing);
        }
        return null;
    }

    // DELETE: Delete Request by ID
    @DeleteMapping("/delete/{id}")
    public String deleteRequest(@PathVariable Long id) {
        if (requestRepository.existsById(id)) {
            requestRepository.deleteById(id);
            return "Request deleted successfully.";
        }
        return "Request not found.";
    }
}
