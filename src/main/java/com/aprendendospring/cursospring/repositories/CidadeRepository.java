package com.aprendendospring.cursospring.repositories;


import com.aprendendospring.cursospring.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository <Cidade, Integer> {



    }

