package com.aprendendospring.cursospring.services;


import com.aprendendospring.cursospring.domain.Pedido;

import com.aprendendospring.cursospring.repositories.PedidoRepository;

import com.aprendendospring.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repo;


    public Pedido find (Integer id){
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo:" + Pedido.class.getName()));
    }






}

