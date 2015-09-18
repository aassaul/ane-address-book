package com.trembit.addressBook.base.data;

import android.database.Cursor;

import android.util.Log;
import com.adobe.fre.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrey Assaul on 01.07.2015.
 */
public final class EmailPhoneContact {
    private final static String AS_CONTACT_NAME = "com.trembit.addressBook.data.EmailContactVO";
    private final static String AS_COLLECTION_NAME = "mx.collections.ArrayCollection";

    private final static EmailContactFieldDescriptor FIELDS = new EmailContactFieldDescriptor("contactId", "name", "emails", "photoData");

    private final String name;
    private final String photoUrl;
    private final List<String> emails;
    private final Integer contactId;

    public EmailPhoneContact(Cursor cursor, int contactId, int nameIndex, int photoUrlIndex, int emailIndex) {
        this.contactId = contactId;
        this.name = cursor.getString(nameIndex);
        this.photoUrl = cursor.getString(photoUrlIndex);
        this.emails = new LinkedList<String>();
        this.emails.add(cursor.getString(emailIndex));
    }

    public String getName() {
        return name;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }
    public List<String> getEmails() {
        return emails;
    }
    public Integer getContactId() {
        return contactId;
    }

    public FREObject toFREObject() throws FRETypeMismatchException, FREInvalidObjectException, FREASErrorException, FRENoSuchNameException, FREWrongThreadException, IllegalStateException, FREReadOnlyException {
        FREObject object = FREObject.newObject(AS_CONTACT_NAME, null);
        object.setProperty(FIELDS.ID, FREObject.newObject(getContactId()));
        object.setProperty(FIELDS.NAME, FREObject.newObject(getName()));
        object.setProperty(FIELDS.PHOTO, FREObject.newObject(getPhotoUrl()));
        FREArray emails = FREArray.newArray(getEmails().size());
        int i = 0;
        for (String email : getEmails()){
            emails.setObjectAt(i++, FREObject.newObject(email));
        }
        object.setProperty(FIELDS.EMAIL, FREObject.newObject(AS_COLLECTION_NAME, new FREObject[]{emails}));
        return object;
    }
}
