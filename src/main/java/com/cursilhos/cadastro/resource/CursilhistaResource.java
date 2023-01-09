package com.cursilhos.cadastro.resource;


import com.cursilhos.cadastro.exception.CursilhistaNotFoundException;
import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.request.CursilhistaConfirmedQueryString;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.resource.dto.CursilhistaDto;
import com.cursilhos.cadastro.service.CursilhistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursilho-da-cristandade/cursilhistas")
@RequiredArgsConstructor
public class CursilhistaResource {
    private final CursilhistaService cursilhistaService;

    @PostMapping
    public ResponseModel cadastrarCursilhista(@RequestBody CursilhistaDto cursilhistaDto){
        return cursilhistaService.cadastrarCursilhista(cursilhistaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cursilhista> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(cursilhistaService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public List<Cursilhista> listarCursilhistas(){
        return cursilhistaService.listarCursilhistas();
    }

    @PatchMapping("/confirmarCursilhista/{cursilhistaId}")
    public ResponseModel confirmarCursilhista(@PathVariable("cursilhistaId") Long cursilhistaId,
                                            @RequestParam("formaPagamento") CursilhistaConfirmedQueryString queryString) throws CursilhistaNotFoundException{
        return cursilhistaService.confirmarCursilhista(cursilhistaId, queryString);
    }

}
