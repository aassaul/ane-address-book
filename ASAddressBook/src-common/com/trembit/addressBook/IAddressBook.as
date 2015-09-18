/**
 * Created with IntelliJ IDEA.
 * User: Andrey Assaul
 * Date: 14.08.2015
 * Time: 17:06
 */
package com.trembit.addressBook {

	import com.trembit.addressBook.data.EmailContactVO;

	import flash.events.IEventDispatcher;

	import mx.collections.ArrayCollection;

	public interface IAddressBook extends IEventDispatcher {
		function dispose():void;
		function getEmailContacts():ArrayCollection;EmailContactVO;
	}
}
