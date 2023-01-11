package com.cursilhos.cadastro.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EnderecoDto {

    private String id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String pontoReferencia;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

}
