package com.aprendendospring.cursospring.repositories;
import com.aprendendospring.cursospring.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Integer> {



    }

