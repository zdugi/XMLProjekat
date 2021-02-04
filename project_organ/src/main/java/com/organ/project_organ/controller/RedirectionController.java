package com.organ.project_organ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/")
public class RedirectionController {

    @GetMapping
    public void redirectToStatic(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "/webclient/index.html");
        httpServletResponse.setStatus(302);
    }
}
