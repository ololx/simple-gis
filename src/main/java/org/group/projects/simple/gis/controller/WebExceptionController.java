package org.group.projects.simple.gis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.model.exception.ExceptionDetail;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.sql.SQLException;
import java.util.Date;

@Slf4j
@ControllerAdvice
public class WebExceptionController {

    @ExceptionHandler({
            Exception.class,
            JsonProcessingException.class,
            SQLException.class,
            JDBCConnectionException.class,
            NoHandlerFoundException.class,
            MethodArgumentNotValidException.class
    })
    public final ModelAndView handleAllExceptions(Exception e, WebRequest webRequest) {
        log.error("\n {} \n {}", webRequest, e);
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status =  HttpStatus.BAD_REQUEST;
        String comment = "Без комментариев";
        String st = "400";

        if (e instanceof JsonProcessingException) {
            //HttpStatus status = HttpStatus.BAD_REQUEST;
            comment = "Ошибка при парсинге json - неверный формат.";

            //return handleException(e, comment, headers, status, webRequest);
        } else if (e instanceof SQLException) {
            //HttpStatus status = HttpStatus.BAD_REQUEST;
            comment = "Ошибка при выполнении SQL-запроса - неверные данные.";

            //return handleException(e, comment, headers, status, webRequest);
        } else if (e instanceof JDBCConnectionException) {
            comment = "Ошибка при выполнении SQL-запроса - проверьте подключение.";
        } else if (e instanceof NoHandlerFoundException) {
            comment = "Не туда тыркнулся.";
            status = HttpStatus.NOT_FOUND;
            st = "404";
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;

            //return handleException(e, comment, headers, status, webRequest);
        }

        return getView(st, handleException(e, comment, headers, status, webRequest));

        //return handleException(e, comment, headers, status, webRequest);
    }

    private ModelAndView getView(String d, ExceptionDetail details) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("er", details);
        modelAndView.addObject("vw", "error-"+d);

        return modelAndView;
    }

    private final ExceptionDetail handleException(Exception e,
                                                  String comment,
                                                  HttpHeaders headers,
                                                  HttpStatus status,
                                                  WebRequest webRequest) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            webRequest.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, WebRequest.SCOPE_REQUEST);
            status = HttpStatus.BAD_REQUEST;
        }

        return ExceptionDetail.builder()
                .comment(comment)
                .message(e.getMessage())
                .timestamp(new Date())
                .details(webRequest.getDescription(true))
                .status(status.toString())
                .build();
    }

}
