package dev.solocoding.shorterurl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.solocoding.shorterurl.exception.NotValidUrlException;
import dev.solocoding.shorterurl.exception.UrlNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String catchAll(Exception ex) {
        var msg = "Internal error: ".concat(ex.getMessage());
        log.error(msg, ex);
        return msg;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotValidUrlException.class)
    public String notValidUrlException(NotValidUrlException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UrlNotFoundException.class)
    public String urlNotFoundException(HttpServletRequest req, UrlNotFoundException ex) {
        return ex.getMessage().concat(": ").concat(req.getRequestURI());
    }
}
