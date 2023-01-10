package com.cursilhos.cadastro.resource;


import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.request.CursilhistaConfirmedQueryString;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.resource.dto.CursilhistaDto;
import com.cursilhos.cadastro.service.CursilhistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cursilho-da-cristandade/cursilhistas")
@RequiredArgsConstructor
public class CursilhistaResource {
    private final CursilhistaService cursilhistaService;

    @PostMapping
    public ResponseEntity<ResponseModel> cadastrarCursilhista(@Valid @RequestBody CursilhistaDto cursilhistaDto){
        return new ResponseEntity<>(cursilhistaService.cadastrarCursilhista(cursilhistaDto), HttpStatus.CREATED);

    }
    @PatchMapping("/confirmarCursilhista/{cursilhistaId}")
    public ResponseModel confirmarCursilhista(@PathVariable("cursilhistaId") Long cursilhistaId,
                                              @RequestParam("formaPagamento") CursilhistaConfirmedQueryString queryString){
        return cursilhistaService.confirmarCursilhista(cursilhistaId, queryString);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cursilhista> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(cursilhistaService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public List<Cursilhista> listarCursilhistas(){
        return cursilhistaService.listarCursilhistas();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deletarCursilhistaById(@PathVariable("id") Long id){
        return new ResponseEntity<>(cursilhistaService.deletarCusrilhistaById(id), HttpStatus.OK);
    }

}
