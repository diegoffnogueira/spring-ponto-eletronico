package com.diego.pontointeligente.api.repositories;

import com.diego.pontointeligente.api.entities.Empresa;
import com.diego.pontointeligente.api.entities.Funcionario;
import com.diego.pontointeligente.api.entities.Lancamento;
import com.diego.pontointeligente.api.enums.PerfilEnum;
import com.diego.pontointeligente.api.enums.TipoEnum;
import com.diego.pontointeligente.api.utils.PasswordUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    private Long funcionarioId;

    @Before
    public void setUp()throws Exception{
        Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());

        Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));

        funcionarioId = funcionario.getId();

        this.lancamentoRepository.save(obterDadosLancamento(funcionario));
        this.lancamentoRepository.save(obterDadosLancamento(funcionario));
    }

    @After
    public void tearDown(){
        this.empresaRepository.deleteAll();
    }

    @Test
    public void testBuscarLancamentosPorFuncionarioId(){
        List<Lancamento> lancamentoList = this.lancamentoRepository.findByFuncionarioId(funcionarioId);
        assertEquals(2, lancamentoList.size());
    }

    @Test
    public void testBuscarLancamentosPorFuncionarioIdPaginado(){
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<Lancamento> lancamentoPage = this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
        assertEquals(2, lancamentoPage.getTotalElements());
    }

    private Lancamento obterDadosLancamento(Funcionario funcionario){
        Lancamento lancamento = new Lancamento();
        lancamento.setData(new Date());
        lancamento.setTipo(TipoEnum.INICIO_ALMOCO);
        lancamento.setFuncionario(funcionario);
        return lancamento;
    }

    private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Diego Felipe");
        funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
        funcionario.setSenha(PasswordUtils.gerarBcrypt("123456"));
        funcionario.setCpf("10337854742");
        funcionario.setEmail("email@email.com");
        funcionario.setEmpresa(empresa);
        return funcionario;
    }

    private Empresa obterDadosEmpresa(){
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("Empresa do Diego");
        empresa.setCnpj("51463645000100");
        return empresa;
    }

}
