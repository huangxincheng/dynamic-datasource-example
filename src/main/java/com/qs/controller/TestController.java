package com.qs.controller;

import com.limaila.support.global.gzip.annotation.GzipCompress;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping
    @GzipCompress
    public String index() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < 1000; i++) {
            builder.append("hahahahahhahahahaha" + i + "\n");
        }
        return builder.toString();
    }
}
