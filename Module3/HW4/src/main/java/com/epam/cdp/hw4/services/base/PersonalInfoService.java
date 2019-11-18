package com.epam.cdp.hw4.services.base;

import com.epam.cdp.hw4.models.PersonalInfo;
import com.epam.cdp.hw4.repositories.impl.base.PersonalInfoDao;
import com.epam.cdp.hw4.services.IBaseService;

public class PersonalInfoService implements IBaseService<PersonalInfo> {

    public PersonalInfoDao personalInfoDao;

    public PersonalInfoService(PersonalInfoDao employeePersonalInfo){
        this.personalInfoDao = employeePersonalInfo;
    }

    @Override
    public PersonalInfo findById(long id) {
        return personalInfoDao.findById(id);
    }

    @Override
    public void save(PersonalInfo entity) {
        personalInfoDao.save(entity);
    }

    @Override
    public void update(long id) {
        personalInfoDao.update(id);
    }

    @Override
    public void delete(long id) {
        personalInfoDao.delete(id);
    }
}
