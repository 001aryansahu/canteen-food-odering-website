package com.canteen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class view {


      @GetMapping("/")
    public String index() {
        return "index";
    }
      @GetMapping("/a")
    public String admin() {
        return "admin";
    }

  @GetMapping("/m")
    public String menu() {
        return "menu";
    }
      @GetMapping("/r")
    public String recipt() {
        return "receipt";
    }
     
    }
    

