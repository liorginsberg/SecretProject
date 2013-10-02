/*
Copyright (c) 2013 eBay, Inc.
This program is licensed under the terms of the eBay Common Development and
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent  version 
thereof released by eBay.  The then-current version of the License can be found 
at http://www.opensource.org/licenses/cddl1.php and in the eBaySDKLicense file that 
is under the eBay SDK ../docs directory.
*/


package com.ebayplus.webapp.ebay;

import java.util.Locale;
import java.util.PropertyResourceBundle;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;

public class Global {
	static PropertyResourceBundle bundle;
	
	public static String getProperty(String propertyName) {
		String value ="";
		if (bundle == null) {
				bundle = (PropertyResourceBundle) PropertyResourceBundle.getBundle("authtool", Locale.US);
		}
		if (bundle != null) {
			value =  bundle.getString(propertyName);
		}
        return value;
	}
	
	public static ApiContext createApiContext( String devId, String appId, String certId, String ApiServerUrl) {
		ApiAccount ac = new ApiAccount();
		ac.setDeveloper(devId);
   	    ac.setApplication(appId);
   	    ac.setCertificate(certId);

	
		ApiCredential apiCred = new ApiCredential();
		apiCred.setApiAccount(ac);

		ApiContext apiContext = new ApiContext();
		apiContext.setApiCredential(apiCred);
		apiContext.setApiServerUrl(ApiServerUrl);
	
        return apiContext;
	}
}
