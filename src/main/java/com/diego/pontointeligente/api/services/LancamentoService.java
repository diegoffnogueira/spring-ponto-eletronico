package com.diego.pontointeligente.api.services;

import com.diego.pontointeligente.api.entities.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface LancamentoService {

    /**
     * Retorna uma lista paginada de lançamentos de um determinado funcionario
     *
     * @param funcionarioId
     * @param pageRequest
     * @return Page<Lancamento>
     */
    Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest);

    /**
     *Retorna um lançamento por id
     *
     * @param id
     * @return Optional<Lancamento>
     */
    Optional<Lancamento> buscarId(Long id);

    /**
     * Persiste um lançamento na base de dados
     *
     * @param lancamento
     * @return Lancamento
     */
    Lancamento persistir(Lancamento lancamento);

    /**
     * Remove um lançamento da base de dados
     *
     * @param id
     */
    void remover(Long id);

}