package com.aprendendospring.cursospring.controllers;

import com.aprendendospring.cursospring.domain.Categoria;
import com.aprendendospring.cursospring.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity <?> find (@PathVariable Integer id){

    Categoria obj = service.buscar(id);
    return ResponseEntity.ok().body(obj);

    }
}
