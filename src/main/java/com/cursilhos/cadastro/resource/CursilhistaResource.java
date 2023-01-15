package com.cursilhos.cadastro.resource;


import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.request.CursilhistaConfirmedQueryString;
import com.cursilhos.cadastro.model.request.CursilhistaRequest;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.service.CursilhistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cursilho-da-cristandade/v1")
@RequiredArgsConstructor
public class CursilhistaResource {
    private final CursilhistaService cursilhistaService;

    @PostMapping("/cursilhista")
    public ResponseEntity<ResponseModel> cadastrarCursilhista(@Valid @RequestBody CursilhistaRequest cursilhistaRequest){
        return new ResponseEntity<>(cursilhistaService.cadastrarCursilhista(cursilhistaRequest), HttpStatus.CREATED);
    }

    @GetMapping("/cursilhista/{id}")
    public ResponseEntity<Cursilhista> findCursilhistaById(@PathVariable("id") String id){
        return new ResponseEntity<>(cursilhistaService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/cursilhista")
    public ResponseEntity<List<Cursilhista>> listarCursilhistas(){
        return new ResponseEntity<>(cursilhistaService.listarCursilhistas(),HttpStatus.OK);
    }

    @DeleteMapping("/cursilhista/{id}")
    public ResponseEntity<ResponseModel> deletarCursilhistaById(@PathVariable("id") String id){
        return new ResponseEntity<>(cursilhistaService.deletarCusrilhistaById(id), HttpStatus.OK);
    }


}
