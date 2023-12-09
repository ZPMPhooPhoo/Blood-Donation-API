package com.donate_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FormController {

    @GetMapping("/")
    public String Index() {
        log.info("Api welcome Form only!");
        return "index/welcome";
    }
}
