package org.group.projects.simple.gis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.model.ErrorDetails;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.sql.SQLException;
import java.util.Date;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({
            Exception.class,
            JsonProcessingException.class,
            SQLException.class,
            JDBCConnectionException.class,
            NoHandlerFoundException.class
    })
    public final ModelAndView handleAllExceptions(Exception e, WebRequest webRequest) {
        log.error("\n {} \n {}", webRequest, e);
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = null;
        String comment = "Без комментариев";

        if(e instanceof JsonProcessingException) {
            //HttpStatus status = HttpStatus.BAD_REQUEST;
            comment = "Ошибка при парсинге json - неверный формат.";

            //return handleException(e, comment, headers, status, webRequest);
        } else if(e instanceof SQLException) {
            //HttpStatus status = HttpStatus.BAD_REQUEST;
            comment = "Ошибка при выполнении SQL-запроса - неверные данные.";

            //return handleException(e, comment, headers, status, webRequest);
        } else if(e instanceof JDBCConnectionException) {
            comment = "Ошибка при выполнении SQL-запроса - проверьте подключение.";
        } else {
            //HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

            //return handleException(e, comment, headers, status, webRequest);
        }

        return getView(handleException(e, comment, headers, status, webRequest));

        //return handleException(e, comment, headers, status, webRequest);
    }

    private ModelAndView getView(ErrorDetails details) {
        ModelAndView modelAndView = new ModelAndView("error.html");
        modelAndView.addObject("error", details);

        return modelAndView;
    }

    private final ErrorDetails handleException(Exception e,
                                               String comment,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest webRequest) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            webRequest.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, WebRequest.SCOPE_REQUEST);
            status = HttpStatus.BAD_REQUEST;
        }

        return ErrorDetails.builder()
                .comment(comment)
                .message(e.getMessage())
                .timestamp(new Date())
                .details(webRequest.getDescription(true))
                .build();
    }

}
