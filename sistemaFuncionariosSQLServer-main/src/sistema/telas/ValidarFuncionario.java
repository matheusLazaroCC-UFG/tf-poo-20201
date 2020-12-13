package sistema.telas;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarFuncionario {

    public ValidarFuncionario(){}

    public void validarNome(String fnome) throws ValidarFuncionarioException {
        if(fnome.length() <= 3){
            throw new ValidarFuncionarioException();
        }
    }
    public void validarSobrenome(String fsobrenome) throws ValidarFuncionarioException {
        if (fsobrenome.length() <= 3) {
            throw new ValidarFuncionarioException();
        }
    }

    public void validarData(String fdata) throws ValidarFuncionarioException{
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            sdf.setLenient(false);

            sdf.parse(fdata);

        } catch (ParseException ex) {
            throw new ValidarFuncionarioException();
        }
    }

    public void validarEmail(String femail) throws ValidarFuncionarioException {
        Boolean emailValidado = false;
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(femail);
        emailValidado = m.matches();

        if(!emailValidado){
            throw new ValidarFuncionarioException();
        }
    }

    public void validarSalario(String fsalario) throws ValidarFuncionarioException {
        if(Double.parseDouble(fsalario.replace(",", ".")) <= 100){
            throw new ValidarFuncionarioException();
        }
    }


    public int validar(String fnome, String fsobrenome, String fData, String femail, String fsalario){
        // validando nome
        try {
            validarNome(fnome);
        }catch(ValidarFuncionarioException e){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o nome corretamente.");
            return 0;
        }

        // validando sobrenome
        try{
            validarSobrenome(fsobrenome);
        }catch(ValidarFuncionarioException e){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o sobrenome corretamente.");
            return 0;
        }

        try{
            validarData(fData);
        }catch(ValidarFuncionarioException e){
            JOptionPane.showMessageDialog(null, "Data inválida. Por favor, preencha a data corretamente.");
            return 0;
        }

        // validando email
        try{
            validarEmail(femail);
        }catch(ValidarFuncionarioException e) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha o email "+ femail +" corretamente.");
            return 0;
        }

        // validando salario
        try{
            validarSalario(fsalario);
        }catch(ValidarFuncionarioException e){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o salário corretamente.");
            return 0;
        }

        return 1;
    }
}
