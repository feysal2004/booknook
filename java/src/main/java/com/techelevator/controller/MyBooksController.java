package com.techelevator.controller;

import com.techelevator.dao.MyBooksDao;
import com.techelevator.model.MyBooks;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RequestMapping(path="/myBookLibrary")
@RestController
@CrossOrigin
public class MyBooksController {

    private MyBooksDao myBooksDao;

    public MyBooksController(MyBooksDao myBooksDao){
        this.myBooksDao = myBooksDao;
    }

    @RequestMapping(path = "",method = RequestMethod.POST)
    public List<MyBooks> getMyBooks(){
        return myBooksDao.getMyBooks();
    }





}
