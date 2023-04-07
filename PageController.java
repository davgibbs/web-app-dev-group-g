package com.G_Database.G_Database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;

@Controller
public class PageController {
    // redirects website to all other pages using request mapping
    @RequestMapping("/admin")
    public String admin() { return "admin";}
    /*
    @GetMapping("/admin_bookview")
    public String bookForm(Model model) {
        // add new book
        model.addAttribute("book", new Book());
        return "admin_bookview";
    }

    @PostMapping("/admin_bookview")
    public String bookSubmit(@ModelAttribute Book book, Model model)  {
        // model, Gson, and http setup
        model.addAttribute("book", book);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // convert to JSON, create httpEntity
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(book), headers);

        // POST Request
        RestOperations restTemplate = new RestTemplate();
        restTemplate.postForObject("http://localhost:8080/admincontrols/add", request, String.class);

        return "admin";
    }

    @Autowired
    private LibraryRepository bookRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("/admin_bookview") // POST to add to all fields within books database (Insert new Book)
    public void add(@RequestBody books book) {
        System.out.println("POST Request Received");
        bookRepository.save(book);
    }
    */
    @RequestMapping("/admin_bookview")
    public String admin_bookview() { return "admin_bookview";}
    @RequestMapping("/admin_newbook")
    public String admin_newbook() { return "admin_newbook";}
}