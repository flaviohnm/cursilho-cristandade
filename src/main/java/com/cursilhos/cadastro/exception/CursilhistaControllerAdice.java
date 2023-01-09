package com.cursilhos.cadastro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice(basePackages ="com.cursilhos.cadastro.resource")
public class CursilhistaControllerAdice {

    @ResponseBody
    @ExceptionHandler(CursilhistaNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> cursilhistaNotFoundException(CursilhistaNotFoundException cursilhistaNotFoundException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Cursilhista Não Encontrado");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
