package com.cursilhos.cadastro.service.impl;

import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.Cursilho;
import com.cursilhos.cadastro.model.request.CursilhistaIncludedQueryString;
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
    private final CursilhistaServiceImpl cursilhistaServiceImpl;

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

    @Override
    public ResponseModel incluirCursilhistaNoCursilho(String cursilhoId, CursilhistaIncludedQueryString queryString) {
        Cursilho cursilhoParaAtualizar = findById(cursilhoId);
        if (!cursilhoParaAtualizar.isCursilhoAberto()) {
            var msg = "Cursilho está fechado e não aceita mais inscrições!";
            return new ResponseModel(BAD_REQUEST,msg);
        }

        Cursilhista cursilhistaParaConfirmar = cursilhistaServiceImpl.findById(queryString.getId());
        cursilhistaServiceImpl.confirmarCursilhista(cursilhistaParaConfirmar.getId(), queryString.getFormaPagamento());

        List<Cursilhista> cursilhistasDoCursilho = cursilhoParaAtualizar.getCursilhistas();
        cursilhistasDoCursilho.add(cursilhistaParaConfirmar);
        cursilhoParaAtualizar.setCursilhistas(cursilhistasDoCursilho);
        cursilhoParaAtualizar.setQuantidadeParticipantes(cursilhistasDoCursilho.size());;
        cursilhoRepository.save(cursilhoParaAtualizar);
        var msg = "Cursilhista incluido no cursilho com sucesso";
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
                .cursilhoAberto(true)
                .build();
        return cursilhoParaSerCriado;
    }

}
