/**
 * 
 */
package com.fduss.traveler.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.widget.Toast;
import service.SceneService;
import service.SceneServiceStub;

/**
 * @author duocai
 * @date 2017年5月9日
 * @time 上午3:50:57
 */
public class ScenePlugin extends CordovaPlugin{

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
	    throws JSONException {
		
		
		SceneService service = new SceneServiceStub();
		if (action.equals("getScene")) {
			int id = args.getInt(0);
			callbackContext.success(service.getScene(id));
		}
		else if (action.equals("getScenePage")) {
			show("getscenepage");
			int offset = args.getInt(0);
			int limit = args.getInt(1);
			show(offset+":"+limit);
			JSONArray array = service.getScenePage(offset, limit);
			show(array.toString());
			callbackContext.success(array);
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
