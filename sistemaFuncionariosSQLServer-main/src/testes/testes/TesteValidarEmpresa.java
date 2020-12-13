package testes.testes;

import org.junit.Test;
import sistema.telas.ValidarEmpresa;

import static org.junit.Assert.*;

public class TesteValidarEmpresa {

    @Test
    public void testeEmpresa() {
        String nome = "aaaaa";
        String cnpj = "12345678901234";
        String cep = "12345-678";
        String email = "teste@gmail.com";

        ValidarEmpresa e = new ValidarEmpresa();

        assertEquals(e.validar(nome, cnpj, cep, email), 1);
    }
}
