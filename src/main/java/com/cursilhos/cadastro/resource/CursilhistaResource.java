package com.cursilhos.cadastro.resource;


import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.resource.dto.CursilhistaDto;
import com.cursilhos.cadastro.service.CursilhistaService;
import lombok.RequiredArgsConstructor;
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
    public Cursilhista verCursilhista(@PathVariable("id") Long id) {
        return cursilhistaService.verCursilhista(id);
    }

    @GetMapping
    public List<Cursilhista> listarCursilhistas(){
        return cursilhistaService.listarCursilhistas();
    }

    @PatchMapping("/confirmarCursilhista/{cursilhistaId}")
    public Cursilhista confirmarCursilhista(@PathVariable("cursilhistaId") Long cursilhistaId,
                                            @RequestParam("formaPagamento") int formaPagamento){
        return cursilhistaService.confirmarCursilhista(cursilhistaId, formaPagamento);
    }

}
