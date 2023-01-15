package com.cursilhos.cadastro.service;

import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.request.CursilhistaRequest;
import com.cursilhos.cadastro.model.response.ResponseModel;

import java.util.List;

public interface CursilhistaService {

    ResponseModel cadastrarCursilhista (CursilhistaRequest cursilhistaRequest);
    Cursilhista findById (String cursilhistaId);

    List<Cursilhista> listarCursilhistas();

    ResponseModel deletarCusrilhistaById (String id);

}

