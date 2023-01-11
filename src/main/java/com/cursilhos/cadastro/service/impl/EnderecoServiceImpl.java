package com.cursilhos.cadastro.service.impl;

import com.cursilhos.cadastro.exception.EnderecoNotFoundException;
import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.Endereco;
import com.cursilhos.cadastro.model.request.EnderecoRequest;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.repository.CursilhistaRepository;
import com.cursilhos.cadastro.repository.EnderecoRepository;
import com.cursilhos.cadastro.service.EnderecoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final int BAD_REQUEST = 400;
    private final int SUCCESS = 200;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final EnderecoRepository enderecoRepository;
    private final CursilhistaRepository cursilhistaRepository;
    private final CursilhistaServiceImpl cursilhistaService;

    @Override
    public ResponseModel cadastrarEndereco(EnderecoRequest enderecoRequest) {
        Cursilhista cursilhistaParaAtualizar = cursilhistaService.findById(enderecoRequest.getCursilhistaId());
        Endereco enderecoParaSerCriado = preencherEndereco(enderecoRequest);
        enderecoRepository.save(enderecoParaSerCriado);

        cursilhistaParaAtualizar.setEndereco(enderecoParaSerCriado);
        cursilhistaRepository.save(cursilhistaParaAtualizar);
        var msg = "Endereço criado com sucesso!!";
        return new ResponseModel(SUCCESS, msg);
    }

    @Override
    public Endereco findById(String id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.orElseThrow(() -> new EnderecoNotFoundException());
    }

    @Override
    public List<Endereco> listarEnderecos() {
        List<Endereco> listaEnderecos = enderecoRepository.findAll();
        return listaEnderecos;
    }

    @Override
    public ResponseModel deleteEnderecoById(String id) {
        findById(id);
        enderecoRepository.deleteById(id);
        var msg = "Endereço apagado com sucesso";
        return new ResponseModel(SUCCESS,msg);
    }

    private static Endereco preencherEndereco(EnderecoRequest enderecoRequest) {
        UUID uuid = UUID.randomUUID();
        Endereco enderecoParaSerCriado = Endereco.builder()
                .id(uuid.toString())
                .logradouro(enderecoRequest.getLogradouro())
                .numero(enderecoRequest.getNumero())
                .bairro(enderecoRequest.getBairro())
                .complemento(enderecoRequest.getComplemento())
                .pontoReferencia(enderecoRequest.getPontoReferencia())
                .cidade(enderecoRequest.getCidade())
                .uf(enderecoRequest.getUf())
                .cep(enderecoRequest.getCep())
                .build();
        return enderecoParaSerCriado;
    }

}
