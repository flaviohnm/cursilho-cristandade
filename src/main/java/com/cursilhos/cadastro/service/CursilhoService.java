package com.cursilhos.cadastro.service;

import com.cursilhos.cadastro.model.Cursilho;
import com.cursilhos.cadastro.model.response.ResponseModel;

import java.util.List;

public interface CursilhoService {

    List<Cursilho> listarCursilhos();
    Cursilho findById (String id);
    ResponseModel deletarCursilho (String id);

}
