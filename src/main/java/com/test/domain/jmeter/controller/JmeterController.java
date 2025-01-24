package com.test.domain.jmeter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmeterController {

    @GetMapping("/posts/{postId}")
    public String getPost(@PathVariable Long postId) {
        return "JMeter Test postId : " + postId;
    }
}
