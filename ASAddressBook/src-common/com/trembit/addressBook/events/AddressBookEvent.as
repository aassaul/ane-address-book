/**
 * Created with IntelliJ IDEA.
 * User: Andrey Assaul
 * Date: 14.08.2015
 * Time: 17:33
 */
package com.trembit.addressBook.events {
	import flash.events.Event;

	public class AddressBookEvent extends Event {

		public function AddressBookEvent(type:String, bubbles:Boolean, cancelable:Boolean) {
			super(type, bubbles, cancelable);
		}
	}
}
