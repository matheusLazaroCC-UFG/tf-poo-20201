package sistema.telas;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarEmpresa {

    public void validarNome(String nome) throws ValidarEmpresaException{
        if(nome.length() <= 3){
            throw new ValidarEmpresaException();
        }
    }

    public void validarCnpj(String cnpj) throws ValidarEmpresaException {
        boolean cnpjValido = false;
        String ePattern = "[0-9]{14}";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(cnpj);
        cnpjValido = m.matches();
        if (!cnpjValido){
            throw new ValidarEmpresaException();
        }
    }

    public void validarCep(String cep) throws ValidarEmpresaException{
        boolean cepValido = false;
        String ePattern = "[0-9]{5}-[0-9]{3}";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(cep);
        cepValido = m.matches();
        if(!cepValido){
            throw new ValidarEmpresaException();
        }
    }

    public void validarEmail(String email) throws ValidarEmpresaException {
        boolean emailValidado = false;
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        emailValidado = m.matches();

        if (!emailValidado) {
            throw new ValidarEmpresaException();
        }
    }

    public int validar(String nome, String cnpj, String cep, String email){
        try{
            validarNome(nome);
        }catch(ValidarEmpresaException v){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o nome corretamente.");
            return 0;
        }

        try{
            validarCnpj(cnpj);
        }catch(ValidarEmpresaException v){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o cnpj corretamente.(14 caracteres numéricos)");
            return 0;
        }

        try{
            validarCep(cep);
        }catch(ValidarEmpresaException v){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o cep corretamente.");
            return 0;
        }

        try{
            validarEmail(email);
        }catch(ValidarEmpresaException v){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o email corretamente.");
            return 0;
        }

        return 1;
    }

}
