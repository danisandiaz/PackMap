package com.danisanchez.PackMap.controller;

import com.danisanchez.PackMap.model.Traveler;
import com.danisanchez.PackMap.model.Trip;
import com.danisanchez.PackMap.repository.TravelerRepository;
import com.danisanchez.PackMap.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class TravelerController {

    @Autowired
    private TravelerRepository travelerRepository;

    @CrossOrigin
    @PostMapping("/traveler")
    public Traveler createTraveler(@Valid @RequestBody Traveler traveler) {
        return travelerRepository.save(traveler);
    }

    @GetMapping("/travelers")
    public List<Traveler> getAllTravelers() { return travelerRepository.findAll(); }

    @CrossOrigin
    @GetMapping("/traveler/userprofile/{id}")
    public ResponseEntity<Traveler> getTravelerById(@PathVariable(value = "id") Long travelerId)
            throws ResourceNotFoundException {
        Traveler traveler = travelerRepository.findById(travelerId)
                .orElseThrow(() -> new ResourceNotFoundException("Traveler not found for this id :: " + travelerId));
        return ResponseEntity.ok().body(traveler);
    }

    @CrossOrigin
    @GetMapping("/traveler/{email}")
    public ResponseEntity<Traveler> getUserByEmail(@PathVariable(value = "email") String travelerEmail)
            throws ResourceNotFoundException {
        Traveler traveler = travelerRepository.findByEmail(travelerEmail);
        return ResponseEntity.ok().body(traveler);
    }
}
