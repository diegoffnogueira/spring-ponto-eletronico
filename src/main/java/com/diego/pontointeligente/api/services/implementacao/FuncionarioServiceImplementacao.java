package com.diego.pontointeligente.api.services.implementacao;

import com.diego.pontointeligente.api.entities.Funcionario;
import com.diego.pontointeligente.api.repositories.FuncionarioRepository;
import com.diego.pontointeligente.api.services.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioServiceImplementacao implements FuncionarioService{

    private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImplementacao.class);

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public Optional<Funcionario> buscarPorCpf(String cpf) {
        log.info("Buscando um funcionario pelo cpf {}", cpf);
        return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
    }

    @Override
    public Funcionario persistir(Funcionario funcionario) {
        log.info("Persistindo o funcionario: {}", funcionario);
        return this.funcionarioRepository.save(funcionario);
    }

    @Override
    public Optional<Funcionario> buscarPorEmail(String email) {
        log.info("Buscando um funcionario pelo email {}", email);
        return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
    }

    @Override
    public Optional<Funcionario> buscarPorId(Long id) {
        log.info("Buscando um funcionario pelo id {}", id);
        return Optional.ofNullable(this.funcionarioRepository.findOne(id));
    }
}
