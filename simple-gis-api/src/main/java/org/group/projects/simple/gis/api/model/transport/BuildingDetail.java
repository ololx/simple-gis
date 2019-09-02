package org.group.projects.simple.gis.api.model.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuildingDetail {

    @JsonProperty("id")
    private int id;

    @JsonProperty("lon")
    private String lon;

    @JsonProperty("lat")
    private String lat;

    @JsonProperty("post_code")
    private String postCode;

    @JsonProperty("city")
    private String city;

    @JsonProperty("district")
    private String district;

    @JsonProperty("street")
    private String street;

    @JsonProperty("number")
    private String number;

    @JsonProperty("number_other")
    private String numberOther;

    @JsonProperty("street_other")
    private String streetOther;

    @JsonProperty("name")
    private String name;

    public String getAddress() {
        return String.format("%s, %s-%s",
                this.getCity(),
                this.getStreet(),
                this.getNumber());
    }

    public String getFullAddress() {
        return String.format("%s, %s, %s-%s (%s-%s)",
                this.getPostCode(),
                this.getCity(),
                this.getStreet(),
                this.getNumber(),
                this.getStreetOther(),
                this.getNumberOther());
    }
}