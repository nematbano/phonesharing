package com.sample.phonesharing.service;

import com.sample.phonesharing.model.Phone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneSharingServiceTest {

    @TestConfiguration
    public static class TestPhoneSharingServiceConfig {
        @Bean
        public PhoneSharingService PhoneSharingService() {
            return new PhoneSharingService();
        }
    }

    @Autowired
    private PhoneSharingService phoneSharingService ;

    @Test
    public void testGetPhone_firstTime() {
        Phone phone = phoneSharingService.getPhone("Samsung Galaxy S9");
        assertEquals("Samsung Galaxy S9", phone.getModel());
        assertEquals("Yes", phone.getAvailability());
        assertEquals("Mocked Technology", phone.getTechnology());
        assertEquals("Mocked 2G Bands", phone.get_2gBands());
        assertEquals("Mocked 3G Bands", phone.get_3gBands());
        assertEquals("Mocked 4G Bands", phone.get_4gBands());
        assertNull(phone.getBookedBy());
        assertNull(phone.getBookingTime());
    }

    @Test
    public void testGetPhone_alreadyBooked() {
        phoneSharingService.bookPhone("Samsung Galaxy S9","Name");
        Phone phone = phoneSharingService.getPhone("Samsung Galaxy S9");
        assertEquals("Samsung Galaxy S9", phone.getModel());
        assertEquals("No", phone.getAvailability());
        assertEquals("Mocked Technology", phone.getTechnology());
        assertEquals("Mocked 2G Bands", phone.get_2gBands());
        assertEquals("Mocked 3G Bands", phone.get_3gBands());
        assertEquals("Mocked 4G Bands", phone.get_4gBands());
        assertEquals("Name",phone.getBookedBy());
        assertNotNull(phone.getBookingTime());
        phoneSharingService.returnPhone("Samsung Galaxy S9");
    }

    @Test
    void bookPhone_firstTime() {
       boolean isBooked= phoneSharingService.bookPhone("Samsung Galaxy S9","Name");
       assertTrue(isBooked);
        phoneSharingService.returnPhone("Samsung Galaxy S9");
    }

    @Test
    void bookPhone_alreadyBooked() {
        phoneSharingService.bookPhone("Samsung Galaxy S9","Name");
        boolean isBooked= phoneSharingService.bookPhone("Samsung Galaxy S9","Name");
        assertFalse(isBooked);
        phoneSharingService.returnPhone("Samsung Galaxy S9");
    }

    @Test
    void returnPhone_alreadyBooked() {
        phoneSharingService.bookPhone("Samsung Galaxy S9","Name");
        boolean isReturned= phoneSharingService.returnPhone("Samsung Galaxy S9");
        assertTrue(isReturned);
    }

    @Test
    void returnPhone_neverBooked() {

        boolean isReturned= phoneSharingService.returnPhone("Samsung Galaxy S9");
        assertFalse(isReturned);
    }
}