package com.diego.pontointeligente.api.services;

import com.diego.pontointeligente.api.entities.Empresa;
import com.diego.pontointeligente.api.entities.Funcionario;
import com.diego.pontointeligente.api.repositories.FuncionarioRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioServiceTest {

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Before
    public void setUp()throws Exception{
        BDDMockito.given(funcionarioRepository.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());
        BDDMockito.given(funcionarioRepository.findByCpf(Mockito.anyString())).willReturn(new Funcionario());
        BDDMockito.given(funcionarioRepository.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
        BDDMockito.given(funcionarioRepository.findOne(Mockito.anyLong())).willReturn(new Funcionario());
    }

    @Test
    public void testPersistirFuncionario(){

        Funcionario funcionario = this.funcionarioService.persistir(new Funcionario());

        Assert.assertNotNull(funcionario);
    }

    @Test
    public void testBuscarFuncionarioPorId(){

        Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(1L);

        Assert.assertTrue(funcionario.isPresent());
    }

    @Test
    public void testBuscarFuncionarioPorEmail(){

        Optional<Funcionario> funcionario = this.funcionarioService.buscarPorEmail("email@email.com");

        Assert.assertTrue(funcionario.isPresent());
    }

    @Test
    public void testBuscarFuncionarioPorCpf(){

        Optional<Funcionario> funcionario = this.funcionarioService.buscarPorCpf("63162237734");

        Assert.assertTrue(funcionario.isPresent());
    }

}
