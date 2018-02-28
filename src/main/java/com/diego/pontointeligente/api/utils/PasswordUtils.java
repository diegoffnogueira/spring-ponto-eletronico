package com.diego.pontointeligente.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);

    public PasswordUtils() {
    }

    /**
     * Gera um hash utilizando o Bcrypt.
     *
     * @param senha
     * @return
     */
    public static String gerarBcrypt(String senha){
        log.info("Gerando hash com o BCrypt...");
        if (senha == null){
            return senha;
        }
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.encode(senha);
    }

}
