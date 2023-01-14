package com.cursilhos.cadastro.service.impl;

import com.cursilhos.cadastro.model.Cursilho;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.repository.CursilhoRepository;
import com.cursilhos.cadastro.service.CursilhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursilhoServiceImpl implements CursilhoService {

    private final int BAD_REQUEST = 400;
    private final int SUCCESS = 200;
    private final CursilhoRepository cursilhoRepository;

    @Override
    public List<Cursilho> listarCursilhos() {
        List<Cursilho> listaCursilhos = cursilhoRepository.findAll();
        return listaCursilhos;
    }

    @Override
    public Cursilho findById(String id) {
        return cursilhoRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException ("Esse cursilho não foi cadastrado!");
                }
        );
    }

    @Override
    public ResponseModel
    deletarCursilho(String id) {
        findById(id);
        cursilhoRepository.deleteById(id);
        var msg = "Cursilho apagado com sucesso";
        return new ResponseModel(SUCCESS,msg);
    }

}
