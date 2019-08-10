package com.epam.cdp.hw2.effectivejava;

import java.util.Date;

public class Employee {

    private String firstName;
    private String lastName;
    private Date birthDay;
    private String homeTown;

    private Employee() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public static Builder newBuilder() {
        return new Employee().new Builder();
    }

    public class Builder {

        private Builder() {}

        /**
         * Get Employee with firstName
         * @param firstName firstName
         * @return employee object
         */
        public Builder setFirstName(String firstName) {
            Employee.this.firstName = firstName;

            return this;
        }

        /**
         * Get Employee with lastName
         * @param lastName lastName
         * @return employee object
         */
        public Builder setLastName(String lastName) {
            Employee.this.lastName = lastName;

            return this;
        }

        /**
         * Get Employee with birthDay
         * @param birthDay birthDay
         * @return employee object
         */
        public Builder setBirtDay(Date birthDay) {
            Employee.this.birthDay = birthDay;

            return this;
        }

        /**
         * Get Employee with homeTown
         * @param homeTown homeTown
         * @return employee object
         */
        public Builder setHomeTown(String homeTown) {
            Employee.this.homeTown = homeTown;

            return this;
        }

        public Employee build() {
            return Employee.this;
        }

    }

}
