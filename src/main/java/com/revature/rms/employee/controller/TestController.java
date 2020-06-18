package com.revature.rms.employee.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String test(){
        return "/hello this is hoang le check ";
    }

    @GetMapping(value= "/colorHex", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getColorHex(){
        return "ffa500";
    }

}
