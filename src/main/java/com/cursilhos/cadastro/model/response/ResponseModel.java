package com.cursilhos.cadastro.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseModel {

    private Integer statusCode;
    private String body;

}
