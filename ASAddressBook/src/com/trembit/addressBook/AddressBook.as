/**
 * Created with IntelliJ IDEA.
 * User: Andrey Assaul
 * Date: 14.08.2015
 * Time: 17:06
 */
package com.trembit.addressBook {
	import com.trembit.addressBook.events.AddressBookEvent;

	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	import flash.net.URLRequest;

	import mx.collections.ArrayCollection;

	public class AddressBook extends EventDispatcher implements IAddressBook{

		private static function onStatus(event:StatusEvent):void {
			trace(event.code, event.level);
		}

		private var extContext:ExtensionContext;

		public function AddressBook() {
			extContext = ExtensionContext.createExtensionContext("com.trembit.extension.AddressBook", null);
			extContext.addEventListener(StatusEvent.STATUS, onStatus);
		}

		public function dispose():void {
			extContext.removeEventListener(StatusEvent.STATUS, onStatus);
			extContext.dispose();
		}

		public function getEmailContacts():ArrayCollection {
			var result:Array = extContext.call("getEmailContacts") as Array;
			if(result){
				extContext.actionScriptData = result;
				return new ArrayCollection(result);
			}
			return null;
		}
	}
}
