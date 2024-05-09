package com.sample.phonesharing.service;

import com.sample.phonesharing.model.Availability;
import com.sample.phonesharing.model.Phone;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class PhoneSharingService {
    public Map<String, Phone> phoneInventory;

    public PhoneSharingService() {
        phoneInventory = new HashMap<>();
        initializeInventory();
    }
    private void initializeInventory() {
        String[] phoneModels = {
                "Samsung Galaxy S9",
                "Samsung Galaxy S8",
                "Samsung Galaxy S8",
                "Motorola Nexus 6",
                "Oneplus 9",
                "Apple iPhone 13",
                "Apple iPhone 12",
                "Apple iPhone 11",
                "iPhone X",
                "Nokia 3310"
        };

        for (String model : phoneModels) {
            phoneInventory.put(model, new Phone(model));
        }
    }

    public Phone getPhone(String model) {
        return phoneInventory.get(model);
    }

    public boolean bookPhone(String model, String bookedBy) {
        Phone phone = phoneInventory.get(model);

        if (phone != null && (phone.getAvailability().equals(Availability.YES.getValue()))) {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.getDefault()).format(new Date());
            phone.setAvailability(Availability.NO.getValue());
            phone.setBookedBy(bookedBy);
            phone.setBookingTime(timeStamp);
            return true;
        }
        return false;
    }

    public boolean returnPhone(String model) {
        Phone phone = phoneInventory.get(model);
        if (phone != null && (phone.getAvailability().equals(Availability.NO.getValue()))) {
            phone.setAvailability(Availability.YES.getValue());
            phone.setBookedBy(null);
            phone.setBookingTime(null);
            return true;
        }
        return false;
    }
}
