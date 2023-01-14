package com.cursilhos.cadastro.service;

import com.cursilhos.cadastro.model.Endereco;
import com.cursilhos.cadastro.model.request.EnderecoRequest;
import com.cursilhos.cadastro.model.response.ResponseModel;

import java.util.List;

public interface EnderecoService {

    ResponseModel cadastrarEndereco(EnderecoRequest enderecoRequest);
    List<Endereco>listarEnderecos();
    Endereco findById(String id);
    ResponseModel deleteEnderecoById (String id);

}
