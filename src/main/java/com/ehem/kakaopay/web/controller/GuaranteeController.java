package com.ehem.kakaopay.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guarantee")
public class GuaranteeController {

    @GetMapping
    public String submitDataFile() {
        return "submit.html";
    }
}
