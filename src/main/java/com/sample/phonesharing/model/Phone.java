package com.sample.phonesharing.model;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class Phone {

    @Getter @Setter private String model;
    @Getter @Setter private String availability;
    @Getter @Setter private String bookedBy;
    @Getter @Setter private String bookingTime;
    @Getter  private String technology;
    @Getter  private String _2gBands;
    @Getter  private String _3gBands;
    @Getter  private String _4gBands;

    public Phone(String model) {
        this.model = model;
        this.availability=Availability.YES.getValue();
        this.bookedBy=null;
        this.bookingTime=null;
        fetchPhoneDetails();
    }

    public Phone(String model, String availability, String bookedBy, String bookingTime) {
        this.model = model;
        this.availability = availability;
        this.bookedBy = bookedBy;
        this.bookingTime = bookingTime;
        fetchPhoneDetails();
    }

    public Phone(String model, String availability, String bookedBy, String bookingTime,
                 String technology, String _2gBands, String _3gBands, String _4gBands) {
        this.model = model;
        this.availability = availability;
        this.bookedBy = bookedBy;
        this.bookingTime = bookingTime;
        this.technology = technology;
        this._2gBands = _2gBands;
        this._3gBands = _3gBands;
        this._4gBands = _4gBands;
    }


    private void fetchPhoneDetails() {
        Map<String, String> deviceData = MockFonoApi.getPhoneDetails(model);
        technology = deviceData.get("technology");
        _2gBands = deviceData.get("2g_bands");
        _3gBands = deviceData.get("3g_bands");
        _4gBands = deviceData.get("4g_bands");
    }
}
