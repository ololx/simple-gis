package org.group.projects.simple.gis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.model.exception.ExceptionDetail;
import org.hibernate.exception.JDBCConnectionException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.WebUtils;

import java.sql.SQLException;
import java.util.Date;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @Autowired
    ModelMapper modelMapper;

    /*@ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Data is Not Valid")*/
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            JsonProcessingException.class
    })
    public ModelAndView handleViolationException(Exception e, WebRequest webRequest) {
        log.error("\n Exception: {} \n WebRequest {}", e, webRequest);

        return this.getError(this.getDetail(e, webRequest, HttpStatus.BAD_REQUEST, "Проверьте данные - они не валидные"));
    }

    /*@ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "No Handler Exception Found")*/
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFoundException(Exception e, WebRequest webRequest) {
        log.error("\n Exception: {} \n WebRequest {}", e, webRequest);

        return this.getError(this.getDetail(e, webRequest, HttpStatus.NOT_FOUND, "Не правильно указан адрес"));
    }

    /*@ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Other Reason Exception")*/
    @ExceptionHandler(Exception.class)
    public final ModelAndView handleAllExceptions(Exception e, WebRequest webRequest) throws Exception {
        log.error("\n Exception: {} \n WebRequest {}", e, webRequest);

        /*if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }*/

        return this.getError(this.getDetail(e, webRequest, HttpStatus.BAD_REQUEST, "N/A"));
    }

    private ModelAndView getError(ExceptionDetail exceptionDetail) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("detail", exceptionDetail);

        return modelAndView;
    }

    private final ExceptionDetail getDetail(Exception e, WebRequest webRequest, HttpStatus status, String comment) {
        ExceptionDetail exceptionDetail = modelMapper.map(e, ExceptionDetail.class);
        exceptionDetail.setDetails(webRequest.toString());
        exceptionDetail.setStatus(status.toString());
        exceptionDetail.setComment(comment);
        exceptionDetail.setTimestamp(new Date());

        return exceptionDetail;
    }

}
