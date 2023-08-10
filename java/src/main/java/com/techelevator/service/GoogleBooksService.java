package com.techelevator.service;


import com.techelevator.model.GoogleApiItem;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class GoogleBooksService {

    String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=";
    String apiKey = "&key=AIzaSyD7CXjdwbiWpjFADwOGsX0XZoJPXUgej7c";
    private RestTemplate restTemplate = new RestTemplate();

    public GoogleApiItem[] getBooksByQuery(String input) {
        GoogleApiItem[] apiItems = null;

        try {
            apiItems = restTemplate.getForObject(baseUrl + input + apiKey, GoogleApiItem[].class);
        } catch (RestClientException e) {
            System.out.println(e.getStackTrace());
        }

        System.out.println(apiItems);
        return apiItems;
    }

}
