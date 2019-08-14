package com.epam.cdp.hw2.effectivejava;

import java.util.Date;

class Employee {

    private String firstName;
    private String lastName;
    private Date birthDay;
    private String homeTown;

    private Employee() {}

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    Date getBirthDay() {
        return birthDay;
    }

    String getHomeTown() {
        return homeTown;
    }

    static Builder newBuilder() {
        return new Employee().new Builder();
    }

    public class Builder {

        private Builder() {}

        /**
         * Get Employee with firstName
         * @param firstName firstName
         * @return employee object
         */
        Builder setFirstName(String firstName) {
            if(firstName == null){
                throw new IllegalArgumentException("Name cannot be null");
            }

            if(firstName.length() < 3){
                throw new IllegalArgumentException("Name cannot be shorter than 3 symbols");
            }
            Employee.this.firstName = firstName;

            return this;
        }

        /**
         * Get Employee with lastName
         * @param lastName lastName
         * @return employee object
         */
        Builder setLastName(String lastName) {
            Employee.this.lastName = lastName;

            return this;
        }

        /**
         * Get Employee with birthDay
         * @param birthDay birthDay
         * @return employee object
         */
        Builder setBirtDay(Date birthDay) {
            Employee.this.birthDay = birthDay;

            return this;
        }

        /**
         * Get Employee with homeTown
         * @param homeTown homeTown
         * @return employee object
         */
        Builder setHomeTown(String homeTown) {
            Employee.this.homeTown = homeTown;

            return this;
        }

        Employee build() {
            return Employee.this;
        }

    }

}
