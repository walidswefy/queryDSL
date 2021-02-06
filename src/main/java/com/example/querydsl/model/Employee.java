package com.example.querydsl.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author walid.sewaify
 * @since 05-Feb-21
 */
@Data
@Document("employee")
public class Employee extends AbstractAuditingEntity {
    private String name;
    private long id;
    private boolean confirmed;
    private Address address;

    @Data
    public static class Address {
        private String street;
        private GeoLocation location;
    }

    @Data
    public static class GeoLocation {
        private String longitude;
        private String latitude;
    }
}
