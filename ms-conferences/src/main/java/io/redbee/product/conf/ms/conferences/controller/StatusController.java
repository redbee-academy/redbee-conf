package io.redbee.product.conf.ms.conferences.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/conferences/{id}")
    public Map<String,?> getAllUsers() {
        Map<String, String> map = new HashMap<>();

        map.put("id", "1");
        map.put("name", "Conf 5");
        map.put("startDate", LocalDate.now().toString());
        map.put("endDate", "");
        map.put("description", "fiofeiiof v veoievoveoafe ");
        map.put("visibility", "VISIBLE");
        return map;
    }

}
