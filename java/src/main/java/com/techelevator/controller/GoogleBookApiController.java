package com.techelevator.controller;

import com.techelevator.model.GoogleApiItem;
import com.techelevator.service.GoogleBooksService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController


public class GoogleBookApiController {


    private String url = "https://www.googleapis.com/books/";
    private String baseURL = "https://www.googleapis.com/books/v1/volumes?q=";
    private String apiKey = "AIzaSyD7CXjdwbiWpjFADwOGsX0XZoJPXUgej7c";

    private GoogleBooksService googleBooksService = new GoogleBooksService();

    @RequestMapping(path="/googleBooks/{input}", method= RequestMethod.GET)
    public GoogleApiItem[] getListOfBooks(@PathVariable String input) {
        return googleBooksService.getBooksByQuery(input);
    }

}
