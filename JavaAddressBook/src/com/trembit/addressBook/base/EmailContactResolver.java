package com.trembit.addressBook.base;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.trembit.addressBook.base.data.EmailContactFieldDescriptor;
import com.trembit.addressBook.base.data.EmailPhoneContact;

import java.util.LinkedList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Email;
import static android.provider.ContactsContract.CommonDataKinds.Phone;
import static android.provider.ContactsContract.Contacts.DISPLAY_NAME;

/**
 * Created by Andrey Assaul on 30.06.2015.
 */
public final class EmailContactResolver {

    private final static EmailContactFieldDescriptor FIELDS = new EmailContactFieldDescriptor(Email.CONTACT_ID, DISPLAY_NAME, Email.DATA, Email.PHOTO_THUMBNAIL_URI);

    private final static String ORDER = FIELDS.NAME + " ASC," +FIELDS.ID + " COLLATE NOCASE";
    private final static String FILTER_PATTERN = Email.DATA + " NOT LIKE '' AND " + FIELDS.ID + " IN (%s)";
    private final static String[] EMAIL_CONTENT_PROJECTION = new String[] { FIELDS.NAME, FIELDS.EMAIL, FIELDS.ID, FIELDS.PHOTO };
    private final static String[] PHONE_CONTENT_PROJECTION = new String[] { FIELDS.ID };

    private Context context;

    public EmailContactResolver(Context context) {
        this.context = context;
    }

    public List<EmailPhoneContact> get(){
        String[] phoneContactIds = getPhoneContactIds();
        if (phoneContactIds == null){
            return null;
        }
        String filter = String.format(FILTER_PATTERN, TextUtils.join(",",phoneContactIds));
        Cursor cursor = context.getContentResolver().query(Email.CONTENT_URI, EMAIL_CONTENT_PROJECTION, filter, null, ORDER);
        return getEmailPhoneContacts(cursor);
    }

    private String[] getPhoneContactIds(){
        Cursor contactCursor =  context.getContentResolver().query(Phone.CONTENT_URI, PHONE_CONTENT_PROJECTION, null, null, null);
        int count = contactCursor.getCount();
        if (0==count){
            return null;
        }
        String[] res = new String[count];
        contactCursor.moveToFirst();
        for (int i = 0; i < count ; i++) {
            res[i] = contactCursor.getString(0);
            contactCursor.moveToNext();
        }
        contactCursor.close();
        return res;
    }

    private List<EmailPhoneContact> getEmailPhoneContacts(Cursor cursor){
        if (cursor.getCount() == 0){
            return null;
        }
        LinkedList<EmailPhoneContact> contacts = new LinkedList<EmailPhoneContact>();
        int nameIndex = cursor.getColumnIndex(FIELDS.NAME);
        int photoUrlIndex = cursor.getColumnIndex(FIELDS.PHOTO);
        int emailIndex = cursor.getColumnIndex(FIELDS.EMAIL);
        int contactIdIndex = cursor.getColumnIndex(FIELDS.ID);
        cursor.moveToFirst();
        EmailPhoneContact lastContact = null;
        do {
            Integer currentContactId = cursor.getInt(contactIdIndex);
            if(lastContact == null || !lastContact.getContactId().equals(currentContactId)){
                lastContact = new EmailPhoneContact(cursor, currentContactId, nameIndex, photoUrlIndex, emailIndex);
                contacts.add(lastContact);
            } else {
                lastContact.getEmails().add(cursor.getString(emailIndex));
            }
        }while (cursor.moveToNext());
        cursor.close();
        return contacts;
    }
}
