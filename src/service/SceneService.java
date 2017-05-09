/**
 * 
 */
package service;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author duocai
 * @date 2017年5月9日
 * @time 上午1:17:58
 */
public interface SceneService {
	
	JSONArray getScenePage(int offset, int limit);
	JSONObject getScene(int id);
	boolean upload(String token, JSONObject scene);
}
