package com.trembit.addressBook.api;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey Assaul
 * Date: 14.08.2015
 * Time: 16:00
 */
public final class AddressBookFREExtension implements FREExtension {
    public static final String ERROR_EVENT = "AddressBookError";

    private static AddressBookFREContext context;

    @Override
    public FREContext createContext(String s) {
        if(context == null){
            context = new AddressBookFREContext();
        }
        return context;
    }

    @Override
    public void dispose() {}

    @Override
    public void initialize() {}
}
