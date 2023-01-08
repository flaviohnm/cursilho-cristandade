package com.cursilhos.cadastro.resource.dto;

import com.cursilhos.cadastro.enumeration.FormaPagamento;
import com.cursilhos.cadastro.enumeration.Igreja;
import com.cursilhos.cadastro.enumeration.Transport;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CursilhistaDto {

    private String fullName;
    private String displayName;
    private String cpf;
    private String phoneNumber;
    private String mobileNumber;
    private String emailAddress;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String birthDate;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private String insertDate;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private String confirmationDate;
    private String conjugeName;
    private String conjugePhoneNumber;
    private String emergencyName;
    private String emergencyPhoneNumber;
    private String foodRestriction;
    private FormaPagamento formaPagamento;
    @Enumerated
    private Transport transport;
    @Enumerated
    private Igreja igreja;
    @Builder.Default
    private boolean confirmed = false;


}
