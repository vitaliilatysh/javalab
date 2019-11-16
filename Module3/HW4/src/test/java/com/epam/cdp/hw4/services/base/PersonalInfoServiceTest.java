package com.epam.cdp.hw4.services.base;

import com.epam.cdp.hw4.BaseTest;
import com.epam.cdp.hw4.models.Address;
import com.epam.cdp.hw4.models.Employee;
import com.epam.cdp.hw4.models.EmployeeStatus;
import com.epam.cdp.hw4.models.PersonalInfo;
import com.epam.cdp.hw4.repositories.impl.base.PersonalInfoDao;
import org.hibernate.Session;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class PersonalInfoServiceTest extends BaseTest {

    private Session session = entityManager.unwrap(Session.class);
    private PersonalInfoDao personalInfoDao = new PersonalInfoDao(session);
    private PersonalInfoService personalInfoService = new PersonalInfoService(personalInfoDao);

    @Test
    public void shouldFindById() {
        assertNotNull(personalInfoService.findById(1));
    }

    @Test
    public void shouldSavePersonalInfo() throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
        Date birthDate = formatter.parse("2002-11-15");
        Date hireDate = formatter.parse("2012-11-20");

        Address address = new Address.AddressBuilder()
                .setCountry("Ukraine")
                .setState("Kharkiv state")
                .setCity("Kharkiv")
                .setAddressLine("Nauky ave, 14")
                .build();
        Employee employee = new Employee.EmployeeBuilder()
                .setName("Danylo")
                .setSurname("Markin")
                .setAddress(address)
                .setStatus(EmployeeStatus.HIRED)
                .build();

        PersonalInfo personalInfo = new PersonalInfo.PersonalBuilder()
                .setBirthDate(birthDate)
                .setHireDate(hireDate)
                .setEmployee(employee)
                .build();

        personalInfoService.save(personalInfo);

        assertNotNull(personalInfoService.findById(3));
    }

    @Test
    public void shouldUpdatePersonalInfo() throws ParseException {
        PersonalInfo personalInfo = personalInfoService.findById(1);

        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
        Date fireDate = formatter.parse("2019-11-15");
        personalInfo.setFireDate(fireDate);

        personalInfoService.update(personalInfo.getId());
        PersonalInfo updated = personalInfoService.findById(personalInfo.getId());

        assertEquals(updated.getFireDate(), fireDate);
    }

    @Test
    public void shouldDeletePersonalInfo() {
        personalInfoService.delete(2);
        assertNull(personalInfoService.findById(2));
    }
}