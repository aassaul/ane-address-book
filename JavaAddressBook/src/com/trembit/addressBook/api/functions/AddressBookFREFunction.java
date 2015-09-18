package com.trembit.addressBook.api.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.trembit.addressBook.api.AddressBookFREContext;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey Assaul
 * Date: 17.09.2015
 * Time: 17:03
 */
public abstract class AddressBookFREFunction implements FREFunction {

    @Override
    public final FREObject call(FREContext freContext, FREObject[] freObjects) {
        return processCall((AddressBookFREContext)freContext, freObjects);
    }

    protected abstract FREObject processCall(AddressBookFREContext freContext, FREObject[] freObjects);
}
