package com.epam.cdp.hw4.services.base;

import com.epam.cdp.hw4.repositories.impl.base.EmployeePersonalInfoDao;

public class EmployeePersonalInfoService {

    public EmployeePersonalInfoDao personalInfoDao;

    public EmployeePersonalInfoService(){
        personalInfoDao = new EmployeePersonalInfoDao();
    }
}
