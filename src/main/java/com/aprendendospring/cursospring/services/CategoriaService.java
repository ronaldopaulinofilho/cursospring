package com.aprendendospring.cursospring.services;

import com.aprendendospring.cursospring.domain.Categoria;
import com.aprendendospring.cursospring.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElse(null);
    }


}

