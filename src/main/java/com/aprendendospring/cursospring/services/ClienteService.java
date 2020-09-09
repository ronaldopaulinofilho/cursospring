package com.aprendendospring.cursospring.services;

import com.aprendendospring.cursospring.domain.Cidade;
import com.aprendendospring.cursospring.domain.Cliente;
import com.aprendendospring.cursospring.domain.Endereco;
import com.aprendendospring.cursospring.domain.enums.TipoCliente;
import com.aprendendospring.cursospring.dto.ClienteDTO;
import com.aprendendospring.cursospring.dto.ClienteNewDTO;
import com.aprendendospring.cursospring.repositories.ClienteRepository;
import com.aprendendospring.cursospring.repositories.EnderecoRepository;
import com.aprendendospring.cursospring.services.exceptions.DataIntegrityException;
import com.aprendendospring.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public ClienteService() {
    }

    public Cliente find(Integer id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo:" + Cliente.class.getName()));
    }
    public Cliente update(Cliente obj){
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }
    @Transactional
    public Cliente insert(Cliente obj){
        obj.setId(null);
        obj = repo.save(obj);
       enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }
    public void delete (Integer id){
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");

        }
    }
    public List<Cliente> findAll(){
        return repo.findAll();
    }
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }
    public Cliente fromDTO(@Valid ClienteDTO objDto) {
       throw new UnsupportedOperationException();
    }
    private void updateData( Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public Cliente fromDTO(ClienteNewDTO objDto){
        Cliente cli = new Cliente(null, objDto.getEmail(),objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null,objDto.getLogradouro(), objDto.getNumero(), objDto.getBairro(), objDto.getCep(), cli,cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2()!= null){
            cli.getTelefones().add(objDto.getTelefone2());

        }
        if (objDto.getTelefone3()!= null){
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }


}

