package com.cursilhos.cadastro.service.impl;

import com.cursilhos.cadastro.enumeration.FormaPagamento;
import com.cursilhos.cadastro.exception.CursilhistaNotFoundException;
import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.Endereco;
import com.cursilhos.cadastro.model.request.CursilhistaConfirmedQueryString;
import com.cursilhos.cadastro.model.request.CursilhistaRequest;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.repository.CursilhistaRepository;
import com.cursilhos.cadastro.repository.EnderecoRepository;
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
import java.util.Optional;
import java.util.UUID;

import static java.lang.Integer.parseInt;

@Service
@RequiredArgsConstructor
public class CursilhistaServiceImpl implements CursilhistaService {

    private final int BAD_REQUEST = 400;
    private final int SUCCESS = 200;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final CursilhistaRepository cursilhistaRepository;
    @Override
    public ResponseModel cadastrarCursilhista(CursilhistaRequest cursilhistaRequest) {

        if (verifyCpfExists(cursilhistaRequest.getCpf())){
            var msg = "CPF já está cadastrado";
            return new ResponseModel(BAD_REQUEST, msg);
        }
        Cursilhista cursilhistaParaSerCriado = preencherCursilhista(cursilhistaRequest);
        cursilhistaRepository.save(cursilhistaParaSerCriado);
        var msg = "Inscrição realizada com sucesso";
        return new ResponseModel(SUCCESS, msg);
    }

    @Override
    public ResponseModel confirmarCursilhista(String idCursilhista, CursilhistaConfirmedQueryString queryString){
        Cursilhista cursilhista = findById(idCursilhista);

        if(cursilhista.isConfirmed()){
            var msg = "Inscrição confirmado anteriormente em: "+ cursilhista.getConfirmationDate()
                    .format(DateTimeFormatter
                            .ofPattern("dd/MM/yyyy hh:mm:ss"));
            return new ResponseModel(BAD_REQUEST,msg);
        }

        if(parseInt(queryString.getFormaPagamento()) == 0){
            cursilhista.setFormaPagamento(FormaPagamento.DINHEIRO);
        } else if (parseInt(queryString.getFormaPagamento()) == 1) {
            cursilhista.setFormaPagamento(FormaPagamento.PIX);
        } else if (parseInt(queryString.getFormaPagamento()) == 2) {
            cursilhista.setFormaPagamento(FormaPagamento.DEBITO);
        } else {
            cursilhista.setFormaPagamento(FormaPagamento.CREDITO);
        }
        cursilhista.setConfirmed(true);
        cursilhista.setConfirmationDate(LocalDateTime.now());

        cursilhistaRepository.save(cursilhista);
        var msg = "Inscrição confirmada!!";
        return new ResponseModel(SUCCESS,msg);
    }

    @Override
    public Cursilhista findById(String id) {
        Optional<Cursilhista> cursilhista = cursilhistaRepository.findById(id);
        return cursilhista.orElseThrow(() -> new CursilhistaNotFoundException());
    }

    @Override
    public List<Cursilhista> listarCursilhistas() {
        List<Cursilhista> listaCursilhistas = cursilhistaRepository.findAll();
        return listaCursilhistas;
    }

    @Override
    public ResponseModel deletarCusrilhistaById(String id) {
        findById(id);
        cursilhistaRepository.deleteById(id);
        var msg = "Inscrição apagada com sucesso";
        return new ResponseModel(SUCCESS,msg);
    }

    private Cursilhista preencherCursilhista(CursilhistaRequest cursilhistaRequest) {
        UUID uuid = UUID.randomUUID();
        Cursilhista cursilhistaParaSerCriado = Cursilhista.builder()
                .id(uuid.toString())
                .fullName(cursilhistaRequest.getFullName())
                .displayName(cursilhistaRequest.getDisplayName())
                .cpf(cursilhistaRequest.getCpf())
                .phoneNumber(cursilhistaRequest.getPhoneNumber())
                .mobileNumber(cursilhistaRequest.getMobileNumber())
                .emailAddress(cursilhistaRequest.getEmailAddress())
                .birthDate(converterData(cursilhistaRequest.getBirthDate()))
                .insertDate(LocalDateTime.now())
                .conjugeName(cursilhistaRequest.getConjugeName())
                .conjugePhoneNumber(cursilhistaRequest.getConjugePhoneNumber())
                .emergencyName(cursilhistaRequest.getEmergencyName())
                .emergencyPhoneNumber(cursilhistaRequest.getEmergencyPhoneNumber())
                .foodRestriction(cursilhistaRequest.isFoodRestriction())
                .foodRestrictionDescription(cursilhistaRequest.getFoodRestrictionDescription())
                .transport(cursilhistaRequest.getTransport())
                .igreja(cursilhistaRequest.getIgreja())
                .build();
        return cursilhistaParaSerCriado;
    }

    public boolean verifyCpfExists(String cpf) {
        var listaInscritos = cursilhistaRepository.findAll();

        if(listaInscritos.stream()
                .filter( k -> Objects.equals(k.getCpf(), cpf))
                .findAny()
                .isEmpty()){
            return false;
        } return true;
    }

    public LocalDate converterData(String value){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(value, formatter);
    }

}
