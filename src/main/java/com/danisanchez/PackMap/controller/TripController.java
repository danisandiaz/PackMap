package com.danisanchez.PackMap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.danisanchez.PackMap.model.Trip;
import com.danisanchez.PackMap.repository.TripRepository;
import com.danisanchez.PackMap.exception.ResourceNotFoundException;

@CrossOrigin
@RestController
public class TripController {
    @Autowired
    private TripRepository tripRepository;

    @GetMapping("/trips")
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/trip/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable(value = "id") Long tripId)
            throws ResourceNotFoundException {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found for this id :: " + tripId));
        return ResponseEntity.ok().body(trip);
    }

    @CrossOrigin
    @PostMapping("/trips")
    public Trip createTrip(@Valid @RequestBody Trip trip) {
        return tripRepository.save(trip);
    }

    @PutMapping("/trip/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable(value = "id") Long tripId,
        @Valid @RequestBody Trip tripDetails) throws ResourceNotFoundException {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found for this id :: " + tripId));

        trip.setName(tripDetails.getName());
        trip.setStartdate(tripDetails.getStartdate());
        trip.setEnddate(tripDetails.getEnddate());
        trip.setLocation(tripDetails.getLocation());
        trip.setTransportation(tripDetails.getTransportation());

        final Trip updatedTrip = tripRepository.save(trip);
        return ResponseEntity.ok(updatedTrip);
    }

    @DeleteMapping("/trip/{id}")
    public Map<String, Boolean> deleteTrip(@PathVariable(value = "id") Long tripId)
            throws ResourceNotFoundException {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found for this id :: " + tripId));

        tripRepository.delete(trip);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
