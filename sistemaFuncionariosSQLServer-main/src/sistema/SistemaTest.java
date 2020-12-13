package sistema;

import org.junit.Test;
import sistema.entidades.Funcionario;
import sistema.telas.FuncionariosEditar;

import static org.junit.Assert.*;

public class SistemaTest {

    @Test
    public void teste (){
        FuncionariosEditar f = new FuncionariosEditar();
        Funcionario modelo = new Funcionario();
        modelo.setNome("modelo");
        modelo.setSobrenome("modelo");
        modelo.setSalario(1000.00);
        modelo.setEmail("abc@abc.com");
        modelo.setidCargo(1002);
        modelo.setIdEmpresa(1002);

        assertEquals(f.getFuncionario(), modelo);



    }

}