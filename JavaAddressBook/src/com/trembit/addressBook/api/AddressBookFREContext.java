package com.trembit.addressBook.api;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.trembit.addressBook.api.functions.AddressBookGetEmailContactsFREFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey Assaul
 * Date: 14.08.2015
 * Time: 15:51
 */
public final class AddressBookFREContext extends FREContext {

    private Map<String, FREFunction> functionMap;

    @Override
    public Map<String, FREFunction> getFunctions() {
        if(functionMap == null){
            functionMap = new HashMap<String, FREFunction>();
            functionMap.put(AddressBookGetEmailContactsFREFunction.KEY, new AddressBookGetEmailContactsFREFunction());
        }
        return functionMap;
    }

    @Override
    public void dispose() {

    }
}
