package com.cursilhos.cadastro.resource;

import com.cursilhos.cadastro.model.Endereco;
import com.cursilhos.cadastro.model.request.EnderecoRequest;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cursilho-da-cristandade/v1/")
@RequiredArgsConstructor
public class EnderecoResource {
    private final EnderecoService enderecoService;

    @PostMapping("/endereco")
    public ResponseEntity<ResponseModel> cadastrarEndereco(@Valid @RequestBody EnderecoRequest enderecoRequest){
        return new ResponseEntity<>(enderecoService.cadastrarEndereco(enderecoRequest), HttpStatus.CREATED);
    }

    @GetMapping("/endereco/{id}")
    public ResponseEntity<Endereco> findEnderecoById(@PathVariable("id") String id){
        return new ResponseEntity<>(enderecoService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/endereco")
    public List<Endereco> listarEnderecos(){
        return enderecoService.listarEnderecos();
    }

    @DeleteMapping("/enredeco/{id}")
    public ResponseEntity<ResponseModel> deletarEnderecoById(@PathVariable("id") String id){
        return new ResponseEntity<>(enderecoService.deleteEnderecoById(id), HttpStatus.NO_CONTENT);
    }
}
