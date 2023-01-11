package com.cursilhos.cadastro.service;

import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.request.CursilhistaConfirmedQueryString;
import com.cursilhos.cadastro.model.request.CursilhistaRequest;
import com.cursilhos.cadastro.model.response.ResponseModel;

import java.util.List;

public interface CursilhistaService {

    ResponseModel cadastrarCursilhista (CursilhistaRequest cursilhistaRequest);
    ResponseModel confirmarCursilhista(String idCursilhista, CursilhistaConfirmedQueryString formaPagamento);
    Cursilhista findById (String cursilhistaId);

    List<Cursilhista> listarCursilhistas();

    ResponseModel deletarCusrilhistaById (String id);
}

