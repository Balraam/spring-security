package com.learning.security.springsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

    @GetMapping("/MyNotice")
    public String getNoticeDetails(){
        return "Notice Details";
    }
}
