package com.epam.cdp.module4.hw2.model;

/**
 * Created by maksym_govorischev.
 */
public interface Ticket extends IBaseModel {

    long getEventId();

    void setEventId(long eventId);

    long getUserId();

    void setUserId(long userId);

    Category getCategory();

    void setCategory(Category category);

    int getPlace();

    void setPlace(int place);

    public enum Category {STANDARD, PREMIUM, BAR}

}
