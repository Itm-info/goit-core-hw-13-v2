package itm.one;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {
    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("username")
    public String username;
    @JsonProperty("email")
    public String email;

    @JsonProperty("address")
    public Address address;
    public static class Address {
        @JsonProperty("street")
        public String street;
        @JsonProperty("suite")
        public String suite;
        @JsonProperty("city")
        public String city;
        @JsonProperty("zipcode")
        public String zipcode;

        @JsonProperty("geo")
        public Geo geo;
        public static class Geo {
            @JsonProperty("lat")
            public String lat;
            @JsonProperty("lng")
            public String lng;
        }
    }

    @JsonProperty("phone")
    public String phone;
    @JsonProperty("website")
    public String website;

    @JsonProperty("company")
    public Company company;
    public static class Company {
        @JsonProperty("name")
        public String name;
        @JsonProperty("catchPhrase")
        public String catchPhrase;
        @JsonProperty("bs")
        public String bs;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try { json = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}