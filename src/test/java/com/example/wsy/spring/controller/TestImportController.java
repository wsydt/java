package com.example.wsy.spring.controller;

import org.springframework.stereotype.Controller;

@Controller
public class TestImportController {

    public TestImportController(){
        System.out.println("Import Controller Construct invoke....");
    }

    public void sayMoon(String name){
        if (name == null) {
            name = "june";
        }
        System.out.println("L Y :" + name);
    }

}
