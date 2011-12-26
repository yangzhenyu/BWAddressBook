package com.bwContacts.provider;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.PhoneLookup;
import android.util.Log;

import com.bwContacts.R;

/**
 * @since 2011-12-15 下午9:46:12
 * @author larry
 */
public class ContactsProvider{
	private Context context;

	public ContactsProvider(Context context) {
		this.context=context;
	}
	
	/**
	 * 获取联系人数据
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> getContacts() {
		ContentResolver cr = context.getContentResolver();
		Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
				null, null, null);

		// 生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		while (cursor.moveToNext()) {
			String contact = cursor.getString(cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME));

			// 获取联系人的ID号
			String contactid = cursor.getString(cursor.getColumnIndex(PhoneLookup._ID));
			Cursor phone = cr.query(CommonDataKinds.Phone.CONTENT_URI, null,CommonDataKinds.Phone.CONTACT_ID + '='+ contactid, null, null);
			Log.i("48","conid:"+contactid+"\tcontact:"+contact);
			String strPhoneNumber = "";
			while (phone.moveToNext()) {
				strPhoneNumber = strPhoneNumber
						+" "+ phone.getString(phone
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			}
//			Uri uri=ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,Long.parseLong(contactid));
//			InputStream input=ContactsContract.Contacts.openContactPhotoInputStream(cr,uri);
//			Bitmap contactPhoto = BitmapFactory.decodeStream(input);
			strPhoneNumber=strPhoneNumber.trim();
			phone.close();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("headerImage", R.drawable.icon);// 图像资源的ID
			map.put("contactName", contact);
			map.put("phoneNumber", strPhoneNumber);
			listItem.add(map);
		}
		return listItem;
	}
}
