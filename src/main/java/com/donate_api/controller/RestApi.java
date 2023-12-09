package com.donate_api.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donate_api.model.User;
import com.donate_api.service.RestService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/blood_donation_api")


public class RestApi {

    public RestService restService;

    public RestApi(RestService restService) {
        this.restService = restService;
        log.info("RestApi service is Ok"+restService);
    }

    @GetMapping("/BD000Connect")
    public String connectApi() throws InterruptedException, ExecutionException {
        log.debug("Api connection ok! ");
        return "ApiOk";
    }

    @PostMapping("/BD001P")
    public String postDonate(@RequestBody User users) {
        try {
            log.debug("Create Data is Ok "+restService.postDonate(users));
            return restService.postDonate(users);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/BD002G/{documentId}")
    public User getDonate(@PathVariable String documentId) throws InterruptedException, ExecutionException {
        log.debug("Fire base get Data is ok "+restService.getDonate(documentId));
        return restService.getDonate(documentId);
    }

    @PutMapping("/BD003C")
    public String putDonate(@RequestBody User users) throws InterruptedException, ExecutionException {
        return restService.putDonate(users);
    }

    @PutMapping("/BD004D/{documentId}")
    public String delDonate(@PathVariable String documentId) throws InterruptedException, ExecutionException {
        return restService.delDonate(documentId);
    }
}
