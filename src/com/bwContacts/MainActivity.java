package com.bwContacts;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bwContacts.R;
import com.bwContacts.action.ActionHandler;
import com.bwContacts.provider.ContactsProvider;
import com.bwContacts.ui.activity.AddContactActivity;
import com.bwContacts.ui.view.PopuMenu;

/**
 * @since 2011-12-15 下午10:56:03
 * @author larry
 */
public class MainActivity extends Activity{
	private String phoneNumber;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e(MainActivity.class.toString(), "调试信息！");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		createContactsView();
	}
	/**
	 * 绘制Contacts列表视图
	 */
	public void createContactsView() {
		ContactsProvider contactsProvider = new ContactsProvider(this);
		// 绑定Layout里面的ListView
		ListView list = (ListView) findViewById(R.id.contid);
		final List<HashMap<String, Object>>  contactsList=contactsProvider.getContacts();
		SimpleAdapter listItemAdapter = new SimpleAdapter(this,contactsList ,
				R.layout.views,
				new String[] { "headerImage", "contactName", "phoneNumber" },
				new int[] { R.id.ItemImage, R.id.ItemTitle, R.id.ItemText });
		// 添加并且显示
		list.setAdapter(listItemAdapter);
		
		// 添加点击
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				setTitle("点击第" + (arg2+1) + "个联系人");
				arg1.setSelected(true);
				Log.e("联系人消息：", "arg3:::\t"+contactsList.get(arg2).toString());
				
			}
		});
		list.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("长按事件：", "position:"+position+"\tid:"+id);
				phoneNumber=contactsList.get(position).get("phoneNumber")+"";
				return false;
			}
			
		});

		// 添加长按点击
		list.setOnCreateContextMenuListener(new PopuMenu());
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		setTitle("你点击了第" + (item.getItemId()+1) + "个菜单,标题为："+item.getTitle());
		switch (item.getItemId()) {
		case 0://call
				new ActionHandler(this).call(phoneNumber);
			break;
		case 1://sms
				break;
		case 2://souchang
				break;
		case 3://edit
				break;
		case 4://delete
				break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	/**
	 * 创建menu菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 1, "搜索");
		menu.add(0, 2, 2, "新建");
		menu.add(0, 3, 3, "多选");
		menu.add(0, 4, 4, "更多");
		return super.onCreateOptionsMenu(menu);
	}
	
	/**
	 * menu菜单点击响应
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		try {
			if (item.getItemId() == 1) {
				Toast.makeText(MainActivity.this, "搜索",Toast.LENGTH_SHORT).show();
//				Runtime.getRuntime().exec("mount -o remount,ro /system");
//				finish();
			} else if (item.getItemId() == 2) {
				Toast.makeText(MainActivity.this, "新建",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(this, AddContactActivity.class);
				startActivity(intent);
			} else if (item.getItemId() == 3) {
				Toast.makeText(MainActivity.this, "多选",Toast.LENGTH_SHORT).show();
//				Intent intent = new Intent();
//				intent.setClass(this, HelpApp.class);
//				startActivity(intent);
			} else if (item.getItemId() == 4) {
				Toast.makeText(MainActivity.this, "更多",Toast.LENGTH_SHORT).show();
//				Intent intent = new Intent();
//				intent.setClass(this, AboutApp.class);
//				startActivity(intent);
			}
		} catch (Exception e) {
			Log.e(MainActivity.this.toString(), e.getLocalizedMessage());
		}
		return super.onOptionsItemSelected(item);
	}
	
}