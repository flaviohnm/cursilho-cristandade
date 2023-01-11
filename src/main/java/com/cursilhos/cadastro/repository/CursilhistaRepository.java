package com.cursilhos.cadastro.repository;

import com.cursilhos.cadastro.model.Cursilhista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursilhistaRepository extends JpaRepository<Cursilhista, String> {

}
