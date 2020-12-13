package testes.testes;
import org.junit.Test;
import sistema.telas.ValidarFuncionario;
import sistema.telas.ValidarFuncionarioException;

import static org.junit.Assert.*;

public class TesteValidarFuncionario {

    @Test
    public void testeFuncionario() throws ValidarFuncionarioException {
        String nome = "aaaa";
        String sobrenome = "bbbb";
        String data = "1/2/75";
        String salario = "1000";
        String email = "teste@gmail.com";

        ValidarFuncionario aux = new ValidarFuncionario();

        assertEquals(aux.validar(nome, sobrenome, data, email, salario), 1);
    }
}

