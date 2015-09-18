package com.trembit.addressBook.api.functions;

import android.content.Context;
import com.adobe.fre.FREArray;
import com.adobe.fre.FREObject;
import com.trembit.addressBook.api.AddressBookFREContext;
import com.trembit.addressBook.api.AddressBookFREExtension;
import com.trembit.addressBook.base.EmailContactResolver;
import com.trembit.addressBook.base.data.EmailPhoneContact;

import java.util.List;

/**
 * Created by Andrey Assaul on 01.07.2015.
 */
public final class AddressBookGetEmailContactsFREFunction extends AddressBookFREFunction {

    public static final String KEY = "getEmailContacts";

    @Override
    public FREObject processCall(AddressBookFREContext freContext, FREObject[] freObjects) {
        try {
            Context context = freContext.getActivity();
            EmailContactResolver contactResolver = new EmailContactResolver(context);
            List<EmailPhoneContact> contacts = contactResolver.get();
            if (contacts == null){
                return FREArray.newArray(0);
            }
            FREArray contactsFre = FREArray.newArray(contacts.size());
            int i = 0;
            for (EmailPhoneContact contact : contacts){
                contactsFre.setObjectAt(i++, contact.toFREObject());
            }
            return contactsFre;
        }catch (Exception e) {
            freContext.dispatchStatusEventAsync(AddressBookFREExtension.ERROR_EVENT,KEY+e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
