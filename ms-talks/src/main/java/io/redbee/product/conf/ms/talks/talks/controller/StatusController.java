package io.redbee.product.conf.ms.talks.talks.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class StatusController {
    @GetMapping("/status")
    public String status() {
        return "ok";
    }
}
