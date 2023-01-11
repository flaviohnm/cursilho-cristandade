package com.cursilhos.cadastro.service.impl;

import com.cursilhos.cadastro.model.Cursilho;
import com.cursilhos.cadastro.repository.CursilhoRepository;
import com.cursilhos.cadastro.service.CursilhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursilhoServiceImpl implements CursilhoService {

    private final CursilhoRepository cursilhoRepository;
    @Override
    public Cursilho verCursilho(String id) {
        return cursilhoRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException ("Esse cursilho não foi cadastrado!");
                }
        );
    }

}
