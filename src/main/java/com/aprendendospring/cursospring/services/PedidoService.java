package com.aprendendospring.cursospring.services;


import com.aprendendospring.cursospring.domain.ItemPedido;
import com.aprendendospring.cursospring.domain.PagamentoComBoleto;
import com.aprendendospring.cursospring.domain.Pedido;

import com.aprendendospring.cursospring.domain.enums.EstadoPagamento;
import com.aprendendospring.cursospring.repositories.ItemPedidoRepository;
import com.aprendendospring.cursospring.repositories.PagamentoRepository;
import com.aprendendospring.cursospring.repositories.PedidoRepository;


import com.aprendendospring.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repo;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;



    public Pedido find (Integer id){
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo:" + Pedido.class.getName()));
    }

   @Transactional
   public Pedido insert (Pedido obj){
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if(obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto =(PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for(ItemPedido ip : obj.getItens()){
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);

        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;

   }
}
