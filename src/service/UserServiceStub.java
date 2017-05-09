/**
 * 
 */
package service;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

/**
 * @author duocai
 * @date 2017年5月9日
 * @time 上午12:55:20
 */
public class UserServiceStub implements UserService {
	SoapSerializationEnvelope envelope;
	HttpTransportSE ht;
	
	public UserServiceStub() {
		
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
	    
	    ht =  new HttpTransportSE("http://120.76.125.35/axis2/services/UserService?wsdl"); 
	    
	}

	@Override
	public boolean login(String u, String p) {
		SoapObject request = new SoapObject("http://service", "login");
		request.addProperty("username", u);
		request.addProperty("password", p);
		envelope.bodyOut = request;
		
		try {
			try {
				ht.call(null, envelope);
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SoapObject soapObject = null;
		try {
			soapObject = (SoapObject) envelope.getResponse();
		} catch (SoapFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String str = soapObject.getProperty(0).toString().replaceAll("anyType", "").replaceAll(":", Constant.colonReplace);
	    //	    	System.out.println("amy"+"beforee");
//			JSONObject object = new JSONObject(str);
//			System.out.println("amy"+object);
		if (str.indexOf("exception") > -1) {
			return false;
		}
		else {
			//Constant.setToken(object.getString("token").replaceAll(Constant.colonReplace,":"));
			return true;
		}
		//return false;
	}

	@Override
	public boolean register(String u, String p) {
		SoapObject request = new SoapObject("http://service", "register");
		request.addProperty("username", u);
		request.addProperty("password", p);
		envelope.bodyOut = request;
		
		try {
			try {
				ht.call(null, envelope);
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SoapObject soapObject = null;
		try {
			soapObject = (SoapObject) envelope.getResponse();
		} catch (SoapFault e) {
			e.printStackTrace();
		}
		String str = soapObject.getProperty(0).toString().replaceAll("anyType", "").replaceAll(":", Constant.colonReplace);
		//		JSONObject object = new JSONObject(str);
		//		System.out.println("amy"+object);
		if (str.indexOf("exception") > -1) {
			return false;
		}
		else {
			//Constant.setToken(object.getString("token").replaceAll(Constant.colonReplace,":"));
			return true;
		}
		//return false;
	}

}
