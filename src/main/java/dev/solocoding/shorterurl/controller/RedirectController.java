package dev.solocoding.shorterurl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dev.solocoding.shorterurl.service.UrlService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RedirectController {

    private final UrlService urlService;
    
    @GetMapping("/redirect/{shortUrl}")
    public String redirect(@PathVariable String shortUrl) {
        return "redirect:".concat(urlService.getAndRedirect(shortUrl).getFullUrl());
    }
}
