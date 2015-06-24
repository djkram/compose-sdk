package org.bdigital.compose.sdk.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.tika.Tika;
import org.json.simple.JSONObject;

public class HttpRestClient {

	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";

	public static String[] doGet(URI url) throws ClientProtocolException,
			ConnectException {

		return doGet(url, null);

	}

	public static String[] doGet(URI url, String token)
			throws ClientProtocolException, ConnectException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request;
		String[] ret;

		request = new HttpGet(url);

		if (token != null) {
			request.addHeader(AUTHORIZATION, BEARER + token);
		}

		try {

			HttpResponse response = client.execute(request);

			int responseCode = response.getStatusLine().getStatusCode();

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			ret = new String[2];
			ret[0] = String.valueOf(responseCode);
			ret[1] = result.toString();

			return ret;

		} catch (IOException e) {
			throw new ConnectException("Conection error GET " + url.toString());
		}

	}

	public static String[] doPost(URI url, JSONObject info)
			throws ClientProtocolException, ConnectException {

		return doPost(url, info, null);
	}

	public static String[] doPost(URI url, JSONObject info, String token)
			throws ClientProtocolException, ConnectException {
		HttpClient client = new DefaultHttpClient();
		HttpPost request;
		String[] ret;

		request = new HttpPost(url);

		if (token != null) {
			request.addHeader(AUTHORIZATION, BEARER + token);
		}

		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		try {
			request.setEntity(new ByteArrayEntity(info.toString().getBytes(
					"UTF8")));

			HttpResponse response;
			response = client.execute(request);

			int responseCode = response.getStatusLine().getStatusCode();

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			ret = new String[2];
			ret[0] = String.valueOf(responseCode);
			ret[1] = result.toString();

			return ret;

		} catch (IOException e) {
			throw new ConnectException("Conection error POST " + url.toString());
		}

	}

	public static String[] doPut(URI url, JSONObject info)
			throws ClientProtocolException, IOException {

		return doPut(url, info, null);
	}

	public static String[] doPut(URI url, JSONObject info, String token)
			throws ClientProtocolException, ConnectException {
		HttpClient client = new DefaultHttpClient();
		String[] ret;

		try {
			HttpPut request = new HttpPut(url);

			if (token != null) {
				request.addHeader(AUTHORIZATION, BEARER + token);
			}

			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");

			request.setEntity(new ByteArrayEntity(info.toString().getBytes(
					"UTF8")));

			HttpResponse response;
			response = client.execute(request);

			int responseCode = response.getStatusLine().getStatusCode();

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			ret = new String[2];
			ret[0] = String.valueOf(responseCode);
			ret[1] = result.toString();

			return ret;

		} catch (IOException e) {
			throw new ConnectException("Conection error PUT " + url.toString());
		}

	}

	public static String[] doDelete(URI url) throws ConnectException,
			ClientProtocolException {

		return doDelete(url, null);
	}

	public static String[] doDelete(URI url, String token)
			throws ClientProtocolException, ConnectException {
		HttpClient client = new DefaultHttpClient();
		HttpDelete request;
		String[] ret;

		request = new HttpDelete(url);

		if (token != null) {
			request.addHeader(AUTHORIZATION, BEARER + token);
		}

		try {

			HttpResponse response = client.execute(request);

			int responseCode = response.getStatusLine().getStatusCode();

			ret = new String[2];
			ret[0] = String.valueOf(responseCode);
			ret[1] = null;

			if (response.getEntity() != null) {

				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				
				ret[1] = result.toString();
			}

			return ret;

		} catch (IOException e) {
			throw new ConnectException("Conection error DELETE "
					+ url.toString());
		}

	}

	public static InputStream downloadFile(URL url, String token)
			throws IOException {

		URLConnection connection = url.openConnection();
		connection.setRequestProperty(AUTHORIZATION, BEARER + token);
		InputStream response = connection.getInputStream();

		return response;

	}
	
	public static InputStream uploadFile(URL url, File file, String token) throws IOException{
		
		Tika tika = new Tika();
		String type = tika.detect(file.getName());
		
		URLConnection connection = url.openConnection();
		connection.setRequestProperty(AUTHORIZATION, BEARER + token);
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		connection.setRequestProperty("Content-Type", type);
		connection.setDoOutput(true); 
		
		OutputStream output = connection.getOutputStream();
		
		InputStream input = new FileInputStream(file);
		byte[] bytes = IOUtils.toByteArray(input);
		output.write(bytes);
		
		InputStream response = connection.getInputStream();
		
		String theString = IOUtils.toString(response, "UTF-8");
		
		return response;
	}
}
