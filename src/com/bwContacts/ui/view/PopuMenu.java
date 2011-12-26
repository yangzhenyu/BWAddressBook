package com.bwContacts.ui.view;

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;

/**
 * @since 2011-12-23 上午12:53:35
 * @author larry
 */
public class PopuMenu implements OnCreateContextMenuListener{
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
			menu.setHeaderTitle("请选择");
			menu.add(0, 0, 0, "拨打电话");
			menu.add(0, 1, 0, "发短信");
			menu.add(0, 2, 0, "收藏");
			menu.add(0, 3, 0, "编辑");
			menu.add(0, 3, 0, "删除");
	}

}
