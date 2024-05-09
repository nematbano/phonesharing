package com.sample.phonesharing.model;

import java.util.HashMap;
import java.util.Map;

public class MockFonoApi {

    static Map<String, String> getPhoneDetails(String model) {
        Map<String, String> mockResponse = new HashMap<>();
        mockResponse.put("DeviceName", model);
        mockResponse.put("technology", "Mocked Technology");
        mockResponse.put("2g_bands", "Mocked 2G Bands");
        mockResponse.put("3g_bands", "Mocked 3G Bands");
        mockResponse.put("4g_bands", "Mocked 4G Bands");
        return mockResponse;
    }
}
