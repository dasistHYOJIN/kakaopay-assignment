package com.ehem.kakaopay.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuaranteeController {

    @GetMapping("/")
    public String submitDataFile() {
        return "submit.html";
    }
}
