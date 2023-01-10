package com.cursilhos.cadastro.service;

import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.request.CursilhistaConfirmedQueryString;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.resource.dto.CursilhistaDto;

import java.util.List;

public interface CursilhistaService {

    ResponseModel cadastrarCursilhista (CursilhistaDto cursilhistaDto);
    ResponseModel confirmarCursilhista(Long idCursilhista, CursilhistaConfirmedQueryString formaPagamento);
    Cursilhista findById (Long id);

    List<Cursilhista> listarCursilhistas();

    boolean verifyCpfExists (String cpf);

    ResponseModel deletarCusrilhistaById (Long id);
}

