package com.example.WetherAPI.service;

import com.example.WetherAPI.bean.WeatherData;
import com.example.WetherAPI.config.ApplicationProperties;
import jakarta.annotation.PostConstruct;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebServiceClientImlp {

	@Autowired
	private ApplicationProperties applicationProperties;

	private HttpResponse httpResponse = null;

	private CloseableHttpClient client = null;

	private int httpConnectionTimeOut;
	private int httpSocketTimeOut;

	private ObjectMapper mapper = new ObjectMapper();

	@PostConstruct
	private void init() {
		httpConnectionTimeOut = applicationProperties.getHttpConnectionTimeOut();
		httpSocketTimeOut = applicationProperties.getHttpSocketTimeOut();
	}

	public WeatherData getWhether(String city) throws URISyntaxException, IOException, ParseException {

		createConnection();

		WeatherData weatherData = null;
		
		List<NameValuePair> postParamList = new ArrayList<>();
		postParamList.add(new BasicNameValuePair("q", city));
		postParamList.add(new BasicNameValuePair("appid", "4e9b7581048d460728ab9204d555c218"));
		postParamList.add(new BasicNameValuePair("units", "metric"));

		String whetherAPI = applicationProperties.getOpenWhetherAPI();

		HttpGet request = new HttpGet(whetherAPI);
		URIBuilder uriWithParams = new URIBuilder(request.getURI());
		for (NameValuePair pair : postParamList) {
			uriWithParams.addParameter(pair.getName(), pair.getValue());
		}

		URI uri = uriWithParams.build();
		request.setURI(uri);
		httpResponse = client.execute(request);

		InputStream inputStream = httpResponse.getEntity().getContent();
		Object obj = new JSONParser().parse(new InputStreamReader(inputStream));

			JSONObject jsonObject = (JSONObject) obj;
			inputStream.close();

			if(jsonObject.containsKey("main")) {
				String mainJsonString = jsonObject.get("main").toString();
				weatherData = mapper.readValue(mainJsonString, WeatherData.class);
			}
			

		return weatherData;
	}

	private void createConnection() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(httpConnectionTimeOut)
				.setSocketTimeout(httpSocketTimeOut).build();
		client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
	}
}
