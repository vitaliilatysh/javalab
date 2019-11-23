package com.epam.cdp.module4.hw2.model;

import java.time.LocalDate;

/**
 * Created by maksym_govorischev.
 */
public interface Event extends IBaseModel {

    String getTitle();

    void setTitle(String title);

    LocalDate getDate();

    void setDate(LocalDate date);
}
