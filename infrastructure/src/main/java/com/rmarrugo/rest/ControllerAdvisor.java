package com.rmarrugo.rest;

import com.rmarrugo.exception.BadRequestException;
import com.rmarrugo.exception.NotFoundException;
import com.rmarrugo.exception.PreconditionFailedException;
import com.rmarrugo.rest.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorResponse> handle(BadRequestException ex,
                                                HttpServletRequest request) {
        log.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        request.getRequestURL().toString()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({PreconditionFailedException.class})
    public ResponseEntity<ErrorResponse> handle(PreconditionFailedException ex,
                                                HttpServletRequest request) {
        log.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.PRECONDITION_FAILED.value(),
                        ex.getMessage(),
                        request.getRequestURL().toString()
                ),
                HttpStatus.PRECONDITION_FAILED
        );
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handle(NotFoundException ex,
                                                HttpServletRequest request) {
        log.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        request.getRequestURL().toString()
                ),
                HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            MissingServletRequestPartException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            MissingRequestHeaderException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex,
                                                          HttpServletRequest request) {
        log.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        request.getRequestURL().toString()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception ex,
                                                HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ex.getMessage(),
                        request.getRequestURL().toString()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


}
