package com.cursilhos.cadastro.service.impl;

import com.cursilhos.cadastro.enumeration.FormaPagamento;
import com.cursilhos.cadastro.exception.CursilhistaNotFoundException;
import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.request.CursilhistaConfirmedQueryString;
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
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class CursilhistaServiceImpl implements CursilhistaService {

    private final int BAD_REQUEST = 400;
    private final int SUCCESS = 200;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final CursilhistaRepository cursilhistaRepository;
    @Override
    public ResponseModel cadastrarCursilhista(CursilhistaDto cursilhistaDto) {

        if (verifyCpfExists(cursilhistaDto.getCpf())){
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
                .foodRestriction(cursilhistaDto.isFoodRestriction())
                .foodRestrictionDescription(cursilhistaDto.getFoodRestrictionDescription())
                .transport(cursilhistaDto.getTransport())
                .igreja(cursilhistaDto.getIgreja())
                .build();
        cursilhistaRepository.save(cursilhistaParaSerCriado);

        var msg = "Inscrição realizada com sucesso";
        return new ResponseModel(SUCCESS, msg);
    }

    @Override
    public Cursilhista findById(Long id) {
        Optional<Cursilhista> cursilhista = cursilhistaRepository.findById(id);
        return cursilhista.orElseThrow(() -> new CursilhistaNotFoundException());
    }

    @Override
    public List<Cursilhista> listarCursilhistas() {
        List<Cursilhista> listaCursilhistas = cursilhistaRepository.findAll();
    return listaCursilhistas;
}

    @Override
    public boolean verifyCpfExists(String cpf) {
        var listaInscritos = cursilhistaRepository.findAll();

        if(listaInscritos.stream()
                .filter( k -> Objects.equals(k.getCpf(), cpf))
                .findAny()
                .isEmpty()){
            return false;
        } return true;
    }

    @Override
    public ResponseModel confirmarCursilhista(Long idCursilhista, CursilhistaConfirmedQueryString queryString) throws CursilhistaNotFoundException {
        Cursilhista cursilhista = findById(idCursilhista);

        if(notPassRequiredQueryString(queryString)){
            var msg = "A forma de pagamento invalida, por favor informe uma forma de pagamento!!";
            return new ResponseModel(BAD_REQUEST,msg);
        }

        if(cursilhista.isConfirmed()){
            var msg = "Cursilhista já foi confirmado anteriormente";
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

        try {
            cursilhistaRepository.save(cursilhista);
            var msg = "Cursilhista confirmado!!";
            return new ResponseModel(SUCCESS,msg);
        }catch (Exception e){
            return new ResponseModel(BAD_REQUEST,e.getMessage());
        }
    }
    public LocalDate converterData(String value){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(value, formatter);
    }

    private boolean notPassRequiredQueryString(CursilhistaConfirmedQueryString queryString){
        return isNull(queryString) || isNull(queryString.getFormaPagamento()) || queryString.getFormaPagamento().isEmpty();
    }

}
