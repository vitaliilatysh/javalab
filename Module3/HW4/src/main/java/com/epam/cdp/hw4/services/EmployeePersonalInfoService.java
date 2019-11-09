package com.epam.cdp.hw4.services;

import com.epam.cdp.hw4.repositories.impl.EmployeePersonalInfoDao;

public class EmployeePersonalInfoService {

    public EmployeePersonalInfoDao personalInfoDao;

    public EmployeePersonalInfoService(){
        personalInfoDao = new EmployeePersonalInfoDao();
    }
}
