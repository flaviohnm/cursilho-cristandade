package com.cursilhos.cadastro.service.impl;

import com.cursilhos.cadastro.enumeration.FormaPagamento;
import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.repository.CursilhistaRepository;
import com.cursilhos.cadastro.resource.dto.CursilhistaDto;
import com.cursilhos.cadastro.service.CursilhistaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CursilhistaServiceImpl implements CursilhistaService {

    private final int BAD_REQUEST = 400;
    private final int SUCCESS = 200;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final CursilhistaRepository cursilhistaRepository;
    @Override
    public ResponseModel cadastrarCursilhista(CursilhistaDto cursilhistaDto) {

        if (consultarCPF(cursilhistaDto.getCpf())){
            var msg = "CPF já está cadastrado";
            return new ResponseModel(BAD_REQUEST, msg);
        }
        Cursilhista cursilhistaParaSerCriado = Cursilhista.builder()
                .fullName(cursilhistaDto.getFullName())
                .displayName(cursilhistaDto.getDisplayName())
                .cpf(cursilhistaDto.getCpf())
                .phoneNumber(cursilhistaDto.getPhoneNumber())
                .mobileNumber(cursilhistaDto.getMobileNumber())
                .emailAddress(cursilhistaDto.getEmailAddress())
                .birthDate(converterData(cursilhistaDto.getBirthDate()))
                .insertDate(LocalDateTime.now())
                .conjugeName(cursilhistaDto.getConjugeName())
                .conjugePhoneNumber(cursilhistaDto.getConjugePhoneNumber())
                .emergencyName(cursilhistaDto.getEmergencyName())
                .emergencyPhoneNumber(cursilhistaDto.getEmergencyPhoneNumber())
                .foodRestriction(cursilhistaDto.getFoodRestriction())
                .transport(cursilhistaDto.getTransport())
                .igreja(cursilhistaDto.getIgreja())
                .build();
        cursilhistaRepository.save(cursilhistaParaSerCriado);
        var msg = "Inscrição realizada com sucesso";
        return new ResponseModel(SUCCESS, msg);
    }

    @Override
    public Cursilhista verCursilhista(Long id) {
        return cursilhistaRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException ("Esse cursilhista não foi cadastrado!");
                }
        );
    }

    @Override
    public List<Cursilhista> listarCursilhistas() {
        List<Cursilhista> listaCursilhistas = cursilhistaRepository.findAll();
//        var cursilhistas = gson.toJson(listaCursilhistas);
//        return new ResponseModel(SUCCESS,cursilhistas);
return listaCursilhistas;
}
    @Override
    public boolean consultarCPF(String cpf) {
        var listaInscritos = cursilhistaRepository.findAll();

        if(listaInscritos.stream()
                .filter( k -> Objects.equals(k.getCpf(), cpf))
                .findAny()
                .isEmpty()){
            return false;
        } return true;
    }

    @Override
    public Cursilhista confirmarCursilhista(Long idCursilhista, int numeroFormaPagamento) {
        Cursilhista cursilhista = verCursilhista(idCursilhista);

        if(cursilhista.isConfirmed()){
            throw new RuntimeException ("Cursilhista já está confirmado");
        }

        if(numeroFormaPagamento == 0){
            cursilhista.setFormaPagamento(FormaPagamento.DINHEIRO);
        } else if (numeroFormaPagamento == 1) {
            cursilhista.setFormaPagamento(FormaPagamento.PIX);
        } else if (numeroFormaPagamento == 2) {
            cursilhista.setFormaPagamento(FormaPagamento.DEBITO);
        } else {
            cursilhista.setFormaPagamento(FormaPagamento.CREDITO);
        }
        cursilhista.setConfirmed(true);
        cursilhista.setConfirmationDate(LocalDateTime.now());

        return cursilhistaRepository.save(cursilhista);

    }
    public LocalDate converterData(String value){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(value, formatter);
    }


}
