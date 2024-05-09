package com.sample.phonesharing.controller;

import com.sample.phonesharing.model.Phone;
import com.sample.phonesharing.service.PhoneSharingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PhoneSharingController {
@Autowired
    private PhoneSharingService phoneSharingService;

    @GetMapping("/phones/{model}")
    public Phone getPhone(@PathVariable String model) {
        return phoneSharingService.getPhone(model);
    }

    @PostMapping("/phones/{model}/book")
    public ResponseEntity<String> bookPhone(@PathVariable String model,
                          @RequestParam String bookedBy) {
        boolean bookPhone = phoneSharingService.bookPhone(model, bookedBy);
        if(bookPhone)
        return new ResponseEntity<>("Booked successfully", HttpStatus.OK);
        else return new ResponseEntity<>("Not available", HttpStatus.OK);
    }

    @GetMapping("/phones/{model}/return")
    public ResponseEntity<String> returnPhone(@PathVariable String model) {

        boolean isPhoneReturned=phoneSharingService.returnPhone(model);
        if(isPhoneReturned)
            return new ResponseEntity<>("Returned successfully", HttpStatus.OK);
        else return new ResponseEntity<>("Never Booked", HttpStatus.OK);
    }
}
