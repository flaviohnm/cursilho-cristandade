package com.cursilhos.cadastro.service;

import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.resource.dto.CursilhistaDto;

import java.util.List;

public interface CursilhistaService {

    ResponseModel cadastrarCursilhista (CursilhistaDto cursilhistaDto);
    Cursilhista verCursilhista (Long id);

    List<Cursilhista> listarCursilhistas();

    boolean consultarCPF (String cpf);

    Cursilhista confirmarCursilhista(Long idCursilhista, int formaPagamento);


}

