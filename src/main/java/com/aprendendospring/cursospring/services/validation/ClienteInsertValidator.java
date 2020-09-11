package com.aprendendospring.cursospring.services.validation;

import com.aprendendospring.cursospring.controllers.exceptions.FieldMessage;
import com.aprendendospring.cursospring.domain.Cliente;
import com.aprendendospring.cursospring.domain.enums.TipoCliente;
import com.aprendendospring.cursospring.dto.ClienteNewDTO;
import com.aprendendospring.cursospring.repositories.ClienteRepository;
import com.aprendendospring.cursospring.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidatorContext;


    public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

        @Autowired
        private ClienteRepository repo;

        @Override
        public void initialize(ClienteInsert ann) {
        }
        @Override
        public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
            List<FieldMessage> list = new ArrayList<>();
            if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())&& !BR.isValidCPF(objDto.getCpfOuCnpj())) {
                list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
            }
            if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())&& !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
                list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
            }

            Cliente aux = repo.findByEmail(objDto.getEmail());
            if (aux != null){
                list.add(new FieldMessage("email", "Email já existente"));
            }
            for (FieldMessage e : list) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(e.getMessage())
                        .addPropertyNode(e.getFieldName()).addConstraintViolation();
            }
            return list.isEmpty();
        }
    }


