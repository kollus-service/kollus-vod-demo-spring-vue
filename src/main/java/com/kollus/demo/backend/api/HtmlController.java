package com.kollus.demo.eduwillvod.api;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController implements ErrorController {

    @GetMapping("/error")
    public String view() {
        return "index.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
