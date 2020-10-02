package dev.solocoding.shorterurl.service.impl;

import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import dev.solocoding.shorterurl.dto.UrlDto;
import dev.solocoding.shorterurl.entity.Url;
import dev.solocoding.shorterurl.exception.NotValidUrlException;
import dev.solocoding.shorterurl.exception.UrlNotFoundException;
import dev.solocoding.shorterurl.repository.UrlRepository;
import dev.solocoding.shorterurl.service.UrlService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository repo;

    @Override
    public UrlDto getUrlByShortUrl(String shortUrl) {
        return new UrlDto(checkExistence(shortUrl));
    }

    @Override
    public UrlDto saveUrl(UrlDto dto) {
        if(! isValidUrl(dto)) {
            throw new NotValidUrlException();
        }
        var url = repo.save(new Url(dto));
        return new UrlDto(url);
    }

    @Override
    public void deleteUrlById(String id) {
        if (ObjectId.isValid(id)) {
            Url url = repo.findById(new ObjectId(id)).orElseThrow(() -> new UrlNotFoundException());
            repo.deleteById(url.getId());
        }

    }

    @Override
    public UrlDto getAndRedirect(String shortUrl) {
        Url url = checkExistence(shortUrl);
        url.setCount(url.getCount()+1);
        return new UrlDto(repo.save(url));
    }

    protected Url checkExistence (String shortUrl) {
        return repo.findByShortUrl(shortUrl).orElseThrow(() -> new UrlNotFoundException());
    }

    protected boolean isValidUrl(UrlDto url) {
        String regex = "((http|https)://)(www.)?"
              + "[a-zA-Z0-9@:%._\\+~#?&/=]"
              + "{2,256}\\.[a-z]"
              + "{2,6}\\b([-a-zA-Z0-9@:%"
              + "._\\+~#?&/=]*)"; 
        return  url.getFullUrl() != null && Pattern.matches(regex, url.getFullUrl());
    }
    
}
