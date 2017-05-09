/**
 * 
 */
package com.fduss.traveler.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import android.annotation.SuppressLint;
import android.widget.Toast;
import service.UserService;
import service.UserServiceStub;

/*
 * @author duocai
 * @date 2017年5月8日
 * @time 下午9:51:35
 */
public class UserPlugin extends CordovaPlugin {

	@SuppressLint("ShowToast")
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
	    throws JSONException {
		
		
		UserService uService = new UserServiceStub();
		String u = args.getString(0);
		String p = args.getString(1);
		show(u+":"+p);
		if (action.equals("login")) {
			
			boolean re = uService.login(u, p);
			if (re) {
				callbackContext.success();
				show("success");
			}
			else {
				callbackContext.error(0);
				show("fail");
			}
		}
		else if (action.equals("register")) {
			boolean re = uService.register(u, p);
			if (re) {
				callbackContext.success();
				show("success");
			}
			else {
				callbackContext.error(0);
				show("fail");
			}
		}
		else {
			callbackContext.error(1);
		}
		return true;
	}
	
	public void show(String str) {
		Toast.makeText(this.cordova.getActivity(), str, Toast.LENGTH_SHORT).show();
	}
}
