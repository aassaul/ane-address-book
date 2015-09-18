package com.trembit.addressBook.base.data;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey Assaul
 * Date: 18.09.2015
 * Time: 13:47
 */
public final class EmailContactFieldDescriptor {

    public final String ID;
    public final String NAME;
    public final String EMAIL;
    public final String PHOTO;

    public EmailContactFieldDescriptor(String ID, String NAME, String EMAIL, String PHOTO) {
        this.ID = ID;
        this.NAME = NAME;
        this.EMAIL = EMAIL;
        this.PHOTO = PHOTO;
    }
}
