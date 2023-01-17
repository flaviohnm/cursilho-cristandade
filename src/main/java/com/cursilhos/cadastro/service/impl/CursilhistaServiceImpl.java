package com.cursilhos.cadastro.service.impl;

import com.cursilhos.cadastro.exception.CursilhistaNotFoundException;
import com.cursilhos.cadastro.general.ConverterDate;
import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.Cursilho;
import com.cursilhos.cadastro.model.request.CursilhistaRequest;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.repository.CursilhistaRepository;
import com.cursilhos.cadastro.repository.CursilhoRepository;
import com.cursilhos.cadastro.service.CursilhistaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursilhistaServiceImpl implements CursilhistaService {

    private final int BAD_REQUEST = 400;
    private final int SUCCESS = 200;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final CursilhistaRepository cursilhistaRepository;
    private final CursilhoRepository cursilhoRepository;
    private final CursilhoServiceImpl cursilhoService;


    @Override
    public ResponseModel cadastrarCursilhista(CursilhistaRequest cursilhistaRequest) {

        if (verifyCpfExists(cursilhistaRequest.getCpf())){
            var msg = "CPF já está cadastrado";
            return new ResponseModel(BAD_REQUEST, msg);
        }
        Cursilhista cursilhistaParaSerCriado = preencherCursilhista(cursilhistaRequest);
        cursilhistaRepository.save(cursilhistaParaSerCriado);
        incluirCursilhistaNoCursilho(cursilhistaRequest.getCursilhiId(), cursilhistaParaSerCriado.getId());
        var msg = "Inscrição realizada com sucesso";
        return new ResponseModel(SUCCESS, msg);
    }

    public ResponseModel confirmarCursilhista(String idCursilhista){
        Cursilhista cursilhista = findById(idCursilhista);

        if(cursilhista.isConfirmed()){
            var msg = "Inscrição confirmado anteriormente em: "+ cursilhista.getConfirmationDate()
                    .format(DateTimeFormatter
                            .ofPattern("dd/MM/yyyy hh:mm:ss"));
            return new ResponseModel(BAD_REQUEST,msg);
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

    public ResponseModel incluirCursilhistaNoCursilho(String cursilhoId, String cursilhistaId) {
        Cursilho cursilhoParaAtualizar = cursilhoService.findById(cursilhoId);
        if (!cursilhoParaAtualizar.isCursilhoAberto()) {
            var msg = "Cursilho está fechado e não aceita mais inscrições!";
            return new ResponseModel(BAD_REQUEST,msg);
        }

        Cursilhista cursilhistaParaConfirmar = findById(cursilhistaId);
        confirmarCursilhista(cursilhistaParaConfirmar.getId());

        List<Cursilhista> cursilhistasDoCursilho = cursilhoParaAtualizar.getCursilhistas();
        cursilhistasDoCursilho.add(cursilhistaParaConfirmar);
        cursilhoParaAtualizar.setCursilhistas(cursilhistasDoCursilho);
        cursilhoParaAtualizar.setQuantidadeParticipantes(cursilhistasDoCursilho.size());;
        cursilhoRepository.save(cursilhoParaAtualizar);
        var msg = "Cursilhista incluido no cursilho com sucesso";
        return new ResponseModel(SUCCESS,msg);
    }

    private Cursilhista preencherCursilhista(CursilhistaRequest cursilhistaRequest) {
        ConverterDate converterDate = new ConverterDate();
        UUID uuid = UUID.randomUUID();
        Cursilhista cursilhistaParaSerCriado = Cursilhista.builder()
                .id(uuid.toString())
                .fullName(cursilhistaRequest.getFullName())
                .displayName(cursilhistaRequest.getDisplayName())
                .cpf(cursilhistaRequest.getCpf())
                .phoneNumber(cursilhistaRequest.getPhoneNumber())
                .mobileNumber(cursilhistaRequest.getMobileNumber())
                .emailAddress(cursilhistaRequest.getEmailAddress())
                .birthDate(converterDate.Data(cursilhistaRequest.getBirthDate()))
                .insertDate(LocalDateTime.now())
                .confirmationDate(LocalDateTime.now())
                .confirmed(true)
                .conjugeName(cursilhistaRequest.getConjugeName())
                .conjugePhoneNumber(cursilhistaRequest.getConjugePhoneNumber())
                .emergencyName(cursilhistaRequest.getEmergencyName())
                .emergencyPhoneNumber(cursilhistaRequest.getEmergencyPhoneNumber())
                .foodRestriction(cursilhistaRequest.isFoodRestriction())
                .foodRestrictionDescription(cursilhistaRequest.getFoodRestrictionDescription())
                .transport(cursilhistaRequest.getTransport())
                .igreja(cursilhistaRequest.getIgreja())
                .estadoCivil(cursilhistaRequest.getEstadoCivil())
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

}
