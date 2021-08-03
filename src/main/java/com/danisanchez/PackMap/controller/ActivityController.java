package com.danisanchez.PackMap.controller;

import com.danisanchez.PackMap.model.Activity;
import com.danisanchez.PackMap.model.Trip;
import com.danisanchez.PackMap.repository.ActivityRepository;
import com.danisanchez.PackMap.exception.ResourceNotFoundException;
import com.danisanchez.PackMap.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ActivityController {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private TripRepository tripRepository;

//    @PostMapping("/activity/{tripId}")
//    public Activity createActivity(@Valid @RequestBody Activity activity, @PathVariable long tripId) {
//        Trip targetTrip = tripRepository.findById(tripId);
//    }
}


