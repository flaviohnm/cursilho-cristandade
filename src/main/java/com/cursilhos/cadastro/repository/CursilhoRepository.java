package com.cursilhos.cadastro.repository;

import com.cursilhos.cadastro.model.Cursilho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursilhoRepository extends JpaRepository<Cursilho,Long> {
}
