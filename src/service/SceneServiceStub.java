/**
 * 
 */
package service;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.widget.Toast;

/**
 * @author duocai
 * @date 2017年5月9日
 * @time 上午1:25:50
 */
public class SceneServiceStub implements SceneService {
	
	SoapSerializationEnvelope envelope;
	HttpTransportSE ht;
	
	public SceneServiceStub() {
		
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
	    
	    ht =  new HttpTransportSE("http://120.76.125.35/axis2/services/SceneService?wsdl"); 
	    
	}

	@Override
	public JSONArray getScenePage(int offset, int limit) {
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < limit; i++) {
			JSONObject object = getOffSetScene(offset+i);
			System.err.println(object);
			if (object != null) {
				jsonArray.put(object);
			}
		}
		return jsonArray;
	}
	
	private JSONObject getOffSetScene(int offset) {
		SoapObject request = new SoapObject("http://service", "getScenePage");
		
		request.addProperty("offset", offset);
		request.addProperty("limit", 1);
	    envelope.bodyOut = request;

		try {
			try {
				ht.call(null, envelope);
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		
	    SoapObject soapObject;
		try {
			soapObject = (SoapObject) envelope.getResponse();
			System.err.println(soapObject);
			if (soapObject == null) {
				return null;
			}
			else {
				String str = soapObject.toString().replaceAll("Scene", "")
						.replaceAll(":", Constant.colonReplace)
						.replaceAll("/", Constant.slashReplace);
				try {
					JSONObject object = new JSONObject(str);
					String url = object.getString("url")
							.replaceAll(Constant.colonReplace, ":")
							.replaceAll(Constant.slashReplace, "/");
					object.put("url", url);
					return object;
				} catch (JSONException e) {
					e.printStackTrace();
				}		
			}
		} catch (SoapFault e) {
			e.printStackTrace();
		}
	    return null;
	}

	@Override
	public JSONObject getScene(int id) {
		SoapObject request = new SoapObject("http://service", "getScene");
		request.addProperty("id", id); 
	    envelope.bodyOut = request;

		try {
			try {
				ht.call(null, envelope);
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		
	    SoapObject soapObject;
		try {
			soapObject = (SoapObject) envelope.getResponse();
			if (soapObject == null) {
				return null;
			}
			else {
				String str = soapObject.toString().replaceAll("Scene", "")
						.replaceAll(":", Constant.colonReplace)
						.replaceAll("/", Constant.slashReplace);
				try {
					JSONObject object = new JSONObject(str);
					String url = object.getString("url")
							.replaceAll(Constant.colonReplace, ":")
							.replaceAll(Constant.slashReplace, "/");
					object.put("url", url);
					return object;
				} catch (JSONException e) {
					e.printStackTrace();
				}		
			}
		} catch (SoapFault e) {
			e.printStackTrace();
		}
	    return null;
	}

	@Override
	public boolean upload(String token, JSONObject scene) {
		// TODO Auto-generated method stub
		return false;
	}

}
