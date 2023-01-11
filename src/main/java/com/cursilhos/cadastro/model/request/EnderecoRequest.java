package com.cursilhos.cadastro.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequest{

    private String id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String pontoReferencia;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String cursilhistaId;
}
