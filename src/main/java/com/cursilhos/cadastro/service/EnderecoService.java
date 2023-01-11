package com.cursilhos.cadastro.service;

import com.cursilhos.cadastro.model.Endereco;
import com.cursilhos.cadastro.model.request.EnderecoRequest;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.resource.dto.EnderecoDto;

import java.util.List;

public interface EnderecoService {

    ResponseModel cadastrarEndereco(EnderecoRequest enderecoRequest);
    Endereco findById(String id);

    List<Endereco>listarEnderecos();

    ResponseModel deleteEnderecoById (String id);

}
