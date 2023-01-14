package com.cursilhos.cadastro.resource;

import com.cursilhos.cadastro.model.Cursilhista;
import com.cursilhos.cadastro.model.Cursilho;
import com.cursilhos.cadastro.model.response.ResponseModel;
import com.cursilhos.cadastro.service.CursilhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursilho-da-cristandade/v1")
@RequiredArgsConstructor
public class CursilhoResource {

    CursilhoService cursilhoService;


    @GetMapping("/cursilhos")
    public ResponseEntity<List<Cursilho>> listarCursilhos(){
        return new ResponseEntity<>(cursilhoService.listarCursilhos(),HttpStatus.OK);
    }
    @GetMapping("/cursilho/{id}")
    public ResponseEntity<Cursilho> findCursilhoById(@PathVariable("id") String id){
        return new ResponseEntity<>(cursilhoService.findById(id), HttpStatus.OK);
    }
    @DeleteMapping("/cursilho/{id}")
    public ResponseEntity<ResponseModel> deletarCursilhoById(@PathVariable("id") String id){
        return new ResponseEntity<>(cursilhoService.deletarCursilho(id), HttpStatus.OK);
    }
}
