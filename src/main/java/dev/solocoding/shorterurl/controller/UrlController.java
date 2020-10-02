package dev.solocoding.shorterurl.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.solocoding.shorterurl.dto.UrlDto;
import dev.solocoding.shorterurl.service.UrlService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/url")
@RequiredArgsConstructor
public class UrlController {
    
    private final UrlService urlService;

    @GetMapping("/{shortUrl}")
    public  UrlDto getUrlByShortUrl (@PathVariable String shortUrl) {
        return urlService.getUrlByShortUrl(shortUrl);
    }

    @PostMapping
    public  UrlDto saveUrl (@RequestBody UrlDto dto) {
        return urlService.saveUrl(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUrlById (@PathVariable String id) {
        urlService.deleteUrlById(id);
    }
}
