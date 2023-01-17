package com.cursilhos.cadastro.service.impl;

import com.cursilhos.cadastro.exception.CursilhoNotFoundException;
import com.cursilhos.cadastro.general.ConverterDate;
import com.cursilhos.cadastro.model.Cursilho;
import com.cursilhos.cadastro.model.request.CursilhoRequest;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.repository.CursilhoRepository;
import com.cursilhos.cadastro.service.CursilhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursilhoServiceImpl implements CursilhoService {

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
        Optional<Cursilho> cursilho = cursilhoRepository.findById(id);
        return cursilho.orElseThrow(() -> new CursilhoNotFoundException());
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
        ConverterDate converterDate = new ConverterDate();
        UUID uuid = UUID.randomUUID();
        Cursilho cursilhoParaSerCriado = Cursilho.builder()
                .id(uuid.toString())
                .cursilhoNumber(cursilhoRequest.getCursilhoNumber())
                .cursilhoType(cursilhoRequest.getCursilhoType())
                .cursilhoStartDate(converterDate.Data(cursilhoRequest.getCursilhoStartDate()))
                .cursilhoEndDate(converterDate.Data(cursilhoRequest.getCursilhoEndDate()))
                .cursilhoLocal(cursilhoRequest.getCursilhoLocal())
                .cursilhoAberto(true)
                .build();
        return cursilhoParaSerCriado;
    }

}
