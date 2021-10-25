package io.redbee.product.conf.ms.conferences.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Joaco Campero
 * <p>
 * created at 14/10/21
 */
@RestController
public class StatusController {

    @GetMapping("/status")
    public String status() {
        return "ok";
    }

}

