package com.danisanchez.PackMap.controller;

import com.danisanchez.PackMap.model.Item;
import com.danisanchez.PackMap.model.Trip;
import com.danisanchez.PackMap.repository.ItemRepository;
import com.danisanchez.PackMap.exception.ResourceNotFoundException;
import com.danisanchez.PackMap.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TripRepository tripRepository;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/trips/{tripId}/item")
    public List<Item> getAllItemsByTripId(@PathVariable Long tripId){
        List<Item> out = itemRepository.findByTripId(tripId);
        return out;
    }


    @PostMapping("/trips/{tripId}/item")
    public Item createItem(@PathVariable (value = "tripId") Long tripId,
                           @Valid @RequestBody Item item) {
        return tripRepository.findById(tripId).map(trip -> {
            item.setTrip(trip);
            return itemRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("TripId " + tripId + " not found"));
    }


    @PutMapping("/item/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable(value = "id") Long itemId,
                                           @Valid @RequestBody Item itemDetails) throws ResourceNotFoundException {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemId));

        item.setName(itemDetails.getName());
        item.setType(itemDetails.getType());
        item.setPacked(itemDetails.getPacked());

        final Item updatedItem = itemRepository.save(item);
        return ResponseEntity.ok(updatedItem);
    }
    @DeleteMapping("/item/{id}")
    public Map<String, Boolean> deleteItem(@PathVariable(value = "id") Long itemId)
            throws ResourceNotFoundException {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemId));

        itemRepository.delete(item);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
