package com.diego.pontointeligente.api.services.implementacao;

import com.diego.pontointeligente.api.entities.Empresa;
import com.diego.pontointeligente.api.repositories.EmpresaRepository;
import com.diego.pontointeligente.api.services.EmpresaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.Optional;

@Service
public class EmpresaServiceImplementacao implements EmpresaService{

    private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImplementacao.class);

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Optional<Empresa> buscarPorCnpj(String cnpj) {
        log.info("Buscando uma empresa para o cnpj {}", cnpj);
        return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
    }

    @Override
    public Empresa persistir(Empresa empresa) {
        log.info("Persistindo empresa {}", empresa);
        return this.empresaRepository.save(empresa);
    }
}
