package com.example.WetherAPI.service;

import com.example.WetherAPI.bean.WhetherBean;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebServiceClientImlp {

    @Autowired
    private ApplicationProperties applicationProperties;

    private HttpResponse httpResponse=null;

    private CloseableHttpClient client= null;

    private int	httpConnectionTimeOut;
    private int	httpSocketTimeOut;

    @PostConstruct
    private void init()
    {
        httpConnectionTimeOut = applicationProperties.getHttpConnectionTimeOut();
        httpSocketTimeOut = applicationProperties.getHttpSocketTimeOut();
        // mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    public void getWhether(String city) throws URISyntaxException, IOException {

        createConnection();

        List<NameValuePair> postParamList=new ArrayList<>();
        postParamList.add(new BasicNameValuePair("q",city));
        postParamList.add(new BasicNameValuePair("appid","4e9b7581048d460728ab9204d555c218"));

        String whetherAPI= applicationProperties.getOpenWhetherAPI();

        HttpGet request =new HttpGet(whetherAPI);
        URIBuilder uriWithParams = new URIBuilder(request.getURI());
        for(NameValuePair pair:postParamList)
        {
                uriWithParams.addParameter(pair.getName(), pair.getValue());
        }

        URI uri=uriWithParams.build();
        request.setURI(uri);
        httpResponse=client.execute(request);

        InputStream inputStream=httpResponse.getEntity().getContent();

        BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder stringBuilder=new StringBuilder();

        String line="";

        while((line= br.readLine())!=null){
            stringBuilder.append(line);
        }

        br.close();
        inputStream.close();

        String nstr=stringBuilder.toString();

        System.out.println(nstr);

//        return new WhetherBean();
    }

    private void createConnection()
    {
        // client = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(httpConnectionTimeOut).setSocketTimeout(httpSocketTimeOut).build();
        client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
    }
}
