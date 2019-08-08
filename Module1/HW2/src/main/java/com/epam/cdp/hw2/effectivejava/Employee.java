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

        public Builder setFirstName(String firstName) {
            Employee.this.firstName = firstName;

            return this;
        }

        public Builder setLastName(String lastName) {
            Employee.this.lastName = lastName;

            return this;
        }

        public Builder setBirtDay(Date birthDay) {
            Employee.this.birthDay = birthDay;

            return this;
        }

        public Builder setHomeTown(String homeTown) {
            Employee.this.homeTown = homeTown;

            return this;
        }

        public Employee build() {
            return Employee.this;
        }

    }

}
