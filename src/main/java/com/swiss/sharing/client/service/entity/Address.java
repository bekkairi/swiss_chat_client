package com.swiss.sharing.client.service.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

@Data
public class Address {
    private String buildingNumber;
    private String address;
    private String postalCode;
    private String city;
    private String country;
    @GeoSpatialIndexed(name = "address", type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint localisation;
}
