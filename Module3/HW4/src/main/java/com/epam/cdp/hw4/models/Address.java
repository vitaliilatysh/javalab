package com.epam.cdp.hw4.models;

import javax.persistence.*;

@Embeddable
public class Address {

    @Column(name = "address_line")
    private String addressLine;
    private String city;
    private String state;
    private String country;

    public Address() {
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static class AddressBuilder{
        private Address address;

        public AddressBuilder(){
            address = new Address();
        }

        public AddressBuilder setCountry(String country){
            address.country = country;
            return this;
        }

        public AddressBuilder setState(String state){
            address.state = state;
            return this;
        }

        public AddressBuilder setAddressLine(String addressLine){
            address.addressLine = addressLine;
            return this;
        }

        public AddressBuilder setCity(String city){
            address.city = city;
            return this;
        }

        public Address build(){
            return address;
        }
    }
}
