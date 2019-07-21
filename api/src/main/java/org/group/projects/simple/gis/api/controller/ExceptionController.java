package org.group.projects.simple.gis.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.api.model.exception.ExceptionDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @Autowired
    ModelMapper modelMapper;

    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Data is Not Valid"
    )
    @ResponseBody
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            JsonProcessingException.class
    })
    public ExceptionDetail handleViolationException(Exception e, WebRequest webRequest) {
        log.error("\n Exception: {} \n WebRequest {}", e, webRequest);

        return this.getDetail(e, webRequest, HttpStatus.BAD_REQUEST, "Проверьте данные - они не валидные");
    }

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "No Handler Exception Found"
    )
    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public ExceptionDetail handleNoHandlerFoundException(Exception e, WebRequest webRequest) {
        log.error("\n Exception: {} \n WebRequest {}", e, webRequest);

        return this.getDetail(e, webRequest, HttpStatus.NOT_FOUND, "Не правильно указан адрес");
    }

    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Other Reason Exception"
    )
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public final ExceptionDetail handleAllExceptions(Exception e, WebRequest webRequest) throws Exception{
        log.error("\n Exception: {} \n WebRequest {}", e, webRequest);

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        return this.getDetail(e, webRequest, HttpStatus.BAD_REQUEST, "N/A");
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
