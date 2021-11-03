package com.app.aeportal.dto.request;

import com.app.aeportal.domain.Location;

import javax.validation.constraints.NotBlank;

public class LocationRequestDto {

    @NotBlank(message = "Location is a mandatory parameter.")
    private String location;

    @NotBlank(message = "Country is a mandatory parameter.")
    private String country;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Location toLocation() {
        return new Location(
                location,
                country
        );
    }
}
