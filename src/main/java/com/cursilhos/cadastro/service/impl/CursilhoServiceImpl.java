package com.cursilhos.cadastro.service.impl;

import com.cursilhos.cadastro.model.Cursilho;
import com.cursilhos.cadastro.model.request.CursilhoRequest;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.repository.CursilhoRepository;
import com.cursilhos.cadastro.service.CursilhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursilhoServiceImpl implements CursilhoService {

    private final int BAD_REQUEST = 400;
    private final int SUCCESS = 200;
    private final CursilhoRepository cursilhoRepository;

    @Override
    public ResponseModel cadastrarCursilho(CursilhoRequest cursilhoRequest) {
        Cursilho cursilhoParaSerCriado = preencherCursilho(cursilhoRequest);
        cursilhoRepository.save(cursilhoParaSerCriado);
        var msg = "Cursilho criado com sucesso!!";
        return new ResponseModel(SUCCESS, msg);
    }

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

    private Cursilho preencherCursilho(CursilhoRequest cursilhoRequest) {
        UUID uuid = UUID.randomUUID();
        Cursilho cursilhoParaSerCriado = Cursilho.builder()
                .id(uuid.toString())
                .cursilhoNumber(cursilhoRequest.getCursilhoNumber())
                .cursilhoType(cursilhoRequest.getCursilhoType())
                .cursilhoPeriod(cursilhoRequest.getCursilhoPeriod())
                .cursilhoLocal(cursilhoRequest.getCursilhoLocal())
                .cursilhoAberto(cursilhoRequest.isCursilhoAberto())
                .quantidadeParticipantes(0)
                .build();
        return cursilhoParaSerCriado;
    }

}
