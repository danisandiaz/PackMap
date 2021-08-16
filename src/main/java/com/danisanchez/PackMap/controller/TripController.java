package com.danisanchez.PackMap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.danisanchez.PackMap.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.danisanchez.PackMap.model.Trip;
import com.danisanchez.PackMap.repository.TripRepository;
import com.danisanchez.PackMap.repository.TravelerRepository;

import com.danisanchez.PackMap.exception.ResourceNotFoundException;

@CrossOrigin
@RestController
public class TripController {
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TravelerRepository travelerRepository;

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

    @GetMapping("/traveler/{travelerId}/trip")
    public List<Trip> getAllTripsByTravelerId(@PathVariable Long travelerId){
        List<Trip> out = tripRepository.findByTravelerId(travelerId);
        return out;
    }

    @CrossOrigin
    @PostMapping("/traveler/{travelerId}/trip")
    public Trip createTrip(@PathVariable (value = "travelerId") Long travelerId,
                           @Valid @RequestBody Trip trip) {
        return travelerRepository.findById(travelerId).map(traveler -> {
            trip.setTraveler(traveler);
            return tripRepository.save(trip);
        }).orElseThrow(() -> new ResourceNotFoundException("TravelerId " + travelerId + " not found"));
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
