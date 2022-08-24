package io.haedoang.springdatamongodb;

import lombok.Data;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
@Data
public class Address {
    private String country;
    private String postCode;
    private String city;

    public Address(String country, String postCode, String city) {
        this.country = country;
        this.postCode = postCode;
        this.city = city;
    }
}
