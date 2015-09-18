/**
 * Created with IntelliJ IDEA.
 * User: Andrey Assaul
 * Date: 14.08.2015
 * Time: 17:06
 */
package com.trembit.addressBook {

	import com.trembit.addressBook.data.EmailContactVO;

	import flash.events.EventDispatcher;
	import flash.net.URLRequest;

	import mx.collections.ArrayCollection;

	public class AddressBook extends EventDispatcher implements IAddressBook{
		public function dispose():void {}

		public function getEmailContacts():ArrayCollection {
			var result:ArrayCollection = new ArrayCollection();
			var contact:EmailContactVO = new EmailContactVO();
			contact.contactId = -1;
			contact.emails = new ArrayCollection(["test@test.com", "test1@test.com"]);
			contact.name = "Test";
			result.addItem(contact);
			contact = new EmailContactVO();
			contact.contactId = -2;
			contact.emails = new ArrayCollection(["test2@tesst.com"]);
			result.addItem(contact);
			return result;
		}
	}
}
