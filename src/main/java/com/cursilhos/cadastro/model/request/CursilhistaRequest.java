package com.cursilhos.cadastro.model.request;


import com.cursilhos.cadastro.enumeration.EstadoCivil;
import com.cursilhos.cadastro.enumeration.Igreja;
import com.cursilhos.cadastro.enumeration.Transport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursilhistaRequest {

    private String cursilhoId;
    private String fullName;
    private String displayName;
    private String cpf;
    private String phoneNumber;
    private String mobileNumber;
    private String emailAddress;
    private String birthDate;
    private String insertDate;
    private boolean confirmed;
    private String confirmationDate;
    private String conjugeName;
    private String conjugePhoneNumber;
    private String emergencyName;
    private String emergencyPhoneNumber;
    private boolean foodRestriction;
    private String foodRestrictionDescription;
    private String indicationName;
    private String indicationPhoneNumber;
    @Enumerated
    private Transport transport;
    @Enumerated
    private Igreja igreja;
    @Enumerated
    private EstadoCivil estadoCivil;
}
