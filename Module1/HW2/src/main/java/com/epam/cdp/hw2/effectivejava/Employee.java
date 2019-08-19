package com.epam.cdp.hw2.effectivejava;

import java.util.Date;

class Employee {

    private String firstName;
    private String lastName;
    private Date birthDay;
    private String homeTown;

    private Employee(String firstName, String lastName, Date birthDay, String homeTown) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.homeTown = homeTown;
    }

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
        return new Builder();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private Date birthDay;
        private String homeTown;

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
            this.firstName = firstName;
            return this;
        }

        /**
         * Get Employee with lastName
         * @param lastName lastName
         * @return employee object
         */
        Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Get Employee with birthDay
         * @param birthDay birthDay
         * @return employee object
         */
        Builder setBirthDay(Date birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        /**
         * Get Employee with homeTown
         * @param homeTown homeTown
         * @return employee object
         */
        Builder setHomeTown(String homeTown) {
            this.homeTown = homeTown;
            return this;
        }

        Employee build() {
            return new Employee(firstName, lastName, birthDay, homeTown);
        }

    }

}
