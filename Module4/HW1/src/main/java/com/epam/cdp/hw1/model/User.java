package com.epam.cdp.hw1.model;

/**
 * Created by maksym_govorischev on 14/03/14.
 */
public interface User extends IBaseModel {

    String getName();

    void setName(String name);

    /**
     * User email. UNIQUE.
     *
     * @return User email.
     */
    String getEmail();

    void setEmail(String email);
}
