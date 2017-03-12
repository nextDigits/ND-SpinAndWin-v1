/**
 * 
 */
package com.nd.utils;

import com.nd.constants.MessageConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author NextDigit
 *
 */
public class EmailUtil {
	
	public static ClientResponse sendSimpleMessage() {
	    Client client = Client.create();
	    client.addFilter(new HTTPBasicAuthFilter("api", MessageConstants.MAILGUN_API_KEY));
	    WebResource webResource = client.resource("https://api.mailgun.net/v3/"+MessageConstants.MAILGUN_DOMAIN+"/messages");
	    MultivaluedMapImpl formData = new MultivaluedMapImpl();
	    formData.add("from", "Mailgun Sandbox <"+MessageConstants.MAILGUN_DOMAIN_NAME+">");
	    formData.add("to", "Anusha <kanushak89@gmail.com>");
	    formData.add("subject", "Hello Anusha");
	    formData.add("text", "Congratulations Anusha, you just sent an email with Mailgun!  You are truly awesome!");
	    return webResource.type(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).
	                                        post(ClientResponse.class, formData);
	}

}
