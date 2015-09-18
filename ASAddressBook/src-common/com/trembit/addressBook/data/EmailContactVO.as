/**
 * Created with IntelliJ IDEA.
 * User: Andrey Assaul
 * Date: 17.09.2015
 * Time: 10:05
 */
package com.trembit.addressBook.data {
	import mx.collections.ArrayCollection;

	public class EmailContactVO {

		public var contactId:Number;

		[Bindable]
		public var name:String;
		[Bindable]
		public var emails:ArrayCollection;String;
		[Bindable]
		public var photoData:*;

		public function toString():String {
			return name+"@"+contactId;
		}
	}
}
