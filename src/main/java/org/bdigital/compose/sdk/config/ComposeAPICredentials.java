package org.bdigital.compose.sdk.config;

/**
 * ComposeAPICredentials Class
 * API credentials configuration
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 */
public class ComposeAPICredentials {

	private String idm_host;
	private String lcm_host;
	private String sdk_host;
	private String servioticy_host;
	
	/**
	 * Default API credentials:
	 * idm_host: http://idm4.147.83.30.133.xip.io
	 * lcm_host: http://compose-lcm4.147.83.30.133.xip.io
	 * sdk_host: http://compose-sdk4.147.83.30.133.xip.io
	 * servioticy_host: http://api.servioticy.com:9090
	 */
	public ComposeAPICredentials(){
		super();
		String host =  "147.83.30.133.xip.io";
		this.idm_host = "http://idm4." + host;
		this.lcm_host = "http://compose-lcm4." + host;
		this.sdk_host = "http://compose-sdk4." + host; 
		this.servioticy_host = "http://api.servioticy.com:9090";
	}
	
	/**
	 * Custom API Credentials 
	 * 
	 * @param idm_host Host for Compose IDM API
	 * @param lcm_host Host for Compose LCM API
	 */
	public ComposeAPICredentials(String idm_host, String lcm_host, String sdk_host){
		super();
		
		String httppromt = "http://";
		
		if(!idm_host.startsWith(httppromt)){
			idm_host = httppromt + idm_host;
		}
		
		if(idm_host.endsWith("/")){
			idm_host = idm_host.substring(0, idm_host.length()-1);
		}
		
		if(!lcm_host.startsWith(httppromt)){
			lcm_host = httppromt + lcm_host;
		}
		
		if(lcm_host.endsWith("/")){
			lcm_host = lcm_host.substring(0, lcm_host.length()-1);
		}
		
		if(!sdk_host.startsWith(httppromt)){
			sdk_host = httppromt + sdk_host;
		}
		
		if(sdk_host.endsWith("/")){
			sdk_host = sdk_host.substring(0, sdk_host.length()-1);
		}
		
		this.idm_host = idm_host;
		this.lcm_host = lcm_host;
		this.sdk_host = sdk_host;
	}
	
	public String getIdm_host() {
		return idm_host;
	}
	public void setIdm_host(String idm_host) {
		this.idm_host = idm_host;
	}
	public String getLcm_host() {
		return lcm_host;
	}
	public void setLcm_host(String lcm_host) {
		this.lcm_host = lcm_host;
	}
	public String getSdk_host() {
		return sdk_host;
	}
	public void setSdk_host(String sdk_host) {
		this.sdk_host = sdk_host;
	}

	public String getServioticy_host() {
	    return servioticy_host;
	}

	public void setServioticy_host(String servioticy_host) {
	    this.servioticy_host = servioticy_host;
	}
	
}
