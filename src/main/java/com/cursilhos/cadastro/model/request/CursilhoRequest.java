package com.cursilhos.cadastro.model.request;

import com.cursilhos.cadastro.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Enumerated;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursilhoRequest {
    private String cursilhoNumber;
    private String cursilhoPeriod;
    private String cursilhoStartDate;
    private String cursilhoEndDate;
    private String cursilhoLocal;
    @Enumerated
    private Type cursilhoType;
}
