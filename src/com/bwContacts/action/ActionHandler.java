package com.bwContacts.action;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @since 2011-12-23 上午1:39:32
 * @author larry
 */
public class ActionHandler {
	private Context context;
	public ActionHandler(Context context){
		this.context=context;
	}
	
	public void call(String phoneNumber){
		Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneNumber));  
        context.startActivity(intent);
	}

}
