package com.diego.pontointeligente.api.utils;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertNull;

public class PasswordUtilsTest {

    private static final String SENHA = "123456";
    private final BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();

    @Test
    public void testSenhaNula(){
        assertNull(PasswordUtils.gerarBcrypt(null));
    }

    @Test
    public void testGerarHashSenha(){
        String hash = PasswordUtils.gerarBcrypt(SENHA);
        Assert.assertTrue(bCryptPasswordEncoder.matches(SENHA, hash));
    }

}
