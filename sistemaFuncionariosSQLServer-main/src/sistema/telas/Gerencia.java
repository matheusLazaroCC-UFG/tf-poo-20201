package sistema.telas;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import sistema.BancoDeDados;
import sistema.Navegador;
import sistema.entidades.Cargo;
import sistema.entidades.Empresa;
import sistema.entidades.Funcionario;

public class Gerencia extends JPanel {

    Cargo cargoAtual;
    Empresa empresaAtual;
    Funcionario funcionarioAtual;
    JLabel labelTitulo, labelFuncionario, labelCargo, labelEmpresa;
    JComboBox<Cargo> comboboxCargo;
    JComboBox<Funcionario> comboboxFuncionario;
    JComboBox<Empresa> comboboxEmpresa;
    DefaultListModel<Funcionario> listaFuncionariomodel = new DefaultListModel();
    DefaultListModel<Cargo> listaCargomodel = new DefaultListModel();
    DefaultListModel<Empresa> listaEmpresamodel = new DefaultListModel();
    JList<Funcionario> listaFuncionario;
    JList<Cargo> listaCargo;
    JList<Empresa> listaEmpresa;
    JButton botaoGravar;
    String selecionar = "Selecionar";


    public Gerencia(){
        criarComponentes();
        criarEventos();
        Navegador.habilitaMenu();
    }

    private void criarComponentes() {
        setLayout(null);

        labelTitulo = new JLabel("Gerenciamento", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelCargo = new JLabel("Cargo:", JLabel.LEFT);
        comboboxCargo = new JComboBox();
        labelEmpresa = new JLabel("Empresa:", JLabel.LEFT);
        comboboxEmpresa = new JComboBox();
        labelFuncionario = new JLabel("Funcionario:", JLabel.LEFT);
        comboboxFuncionario = new JComboBox();
        botaoGravar = new JButton("Buscar");

        listaFuncionariomodel = new DefaultListModel();
        listaFuncionario = new JList();
        listaFuncionario.setModel(listaFuncionariomodel);
        listaFuncionario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaCargomodel = new DefaultListModel();
        listaCargo = new JList();
        listaCargo.setModel(listaCargomodel);
        listaCargo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaEmpresamodel = new DefaultListModel();
        listaEmpresa = new JList();
        listaEmpresa.setModel(listaEmpresamodel);
        listaEmpresa.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        labelTitulo.setBounds(20, 20, 660, 40);
        labelCargo.setBounds(150, 80, 400, 20);
        comboboxCargo.setBounds(150, 100, 400, 40);
        labelEmpresa.setBounds(150, 140, 400, 20);
        comboboxEmpresa.setBounds(150, 160, 400, 40);
        labelFuncionario.setBounds(150, 200, 400, 20);
        comboboxFuncionario.setBounds(150, 220, 400, 40);
        listaEmpresa.setBounds    (414,340,132,240);
        listaCargo.setBounds      (282,340,132,240);
        listaFuncionario.setBounds(150,340,132,240);
        botaoGravar.setBounds(150, 280, 400, 40);

        add(labelTitulo);
        add(labelCargo);
        add(comboboxCargo);
        add(labelEmpresa);
        add(comboboxEmpresa);
        add(labelFuncionario);
        add(comboboxFuncionario);
        add(listaEmpresa);
        add(listaCargo);
        add(listaFuncionario);
        add(botaoGravar);


        setVisible(true);
    }


    private void criarEventos() {
        sqlCarregarCargos();
        sqlCarregarEmpresas();
        sqlCarregarFuncionarios();

        botaoGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cargo cargoSelecionado = (Cargo) comboboxCargo.getSelectedItem();

                Empresa empresaSelecionada = (Empresa) comboboxEmpresa.getSelectedItem();

                Funcionario funcionarioSelecionado = (Funcionario) comboboxFuncionario.getSelectedItem();
                listaEmpresamodel.clear();
                listaCargomodel.clear();
                listaFuncionariomodel.clear();
                sqlPesquisarFuncionarios(funcionarioSelecionado.getNome(),cargoSelecionado.getId(),empresaSelecionada.getId());
            }
        });
    }


    private void sqlCarregarFuncionarios() {
        // conexão
        Connection conexao;
        // instrucao SQL
        Statement instrucaoSQL;
        // resultados
        ResultSet resultados;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

            // criando a instrução SQL
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * FROM funcionarios");

            comboboxFuncionario.removeAll();
            Funcionario vazioFuncionario = new Funcionario();
            vazioFuncionario.setNome("Selecionar");
            vazioFuncionario.setSobrenome("");
            comboboxFuncionario.addItem(vazioFuncionario);

            while (resultados.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultados.getInt("id"));
                funcionario.setNome(resultados.getString("nome"));
                funcionario.setSobrenome(resultados.getString("sobrenome"));
                funcionario.setDataNascimento(resultados.getString("dataNascimento"));
                funcionario.setEmail(resultados.getString("email"));
                if(resultados.getString("idCargo") != null) funcionario.setidCargo(Integer.parseInt(resultados.getString("idCargo")));
                if(resultados.getString("idEmpresa") != null) funcionario.setidCargo(Integer.parseInt(resultados.getString("idEmpresa")));
                funcionario.setSalario(Double.parseDouble(resultados.getString("salario")));

                comboboxFuncionario.addItem(funcionario);
            }
            comboboxFuncionario.updateUI();

            conexao.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar funcionários.");
            Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void sqlPesquisarFuncionarios(String nome,int idCargo, int idEmpresa) {
        // conexão
        Connection conexao;
        // instrucao SQL
        Statement instrucaoSQL;
        // resultados
        ResultSet resultados;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

            // criando a instrução SQL
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if(nome == "Selecionar" && idCargo == -1 && idEmpresa != -1){
                resultados = instrucaoSQL.executeQuery("SELECT * FROM funcionarios WHERE idEmpresa = "+idEmpresa);
            }else if(nome == "Selecionar" && idCargo != -1 && idEmpresa == -1){
                resultados = instrucaoSQL.executeQuery("SELECT * FROM funcionarios WHERE idCargo ="+idCargo);
            }else if(nome != "Selecionar" && idCargo == -1 && idEmpresa == -1){
                resultados = instrucaoSQL.executeQuery("SELECT * FROM funcionarios WHERE nome like '%"+nome+"%'");
            }else if(nome == "Selecionar" && idCargo != -1 && idEmpresa != -1){
                resultados = instrucaoSQL.executeQuery("SELECT * FROM funcionarios WHERE idCargo ="+idCargo+"and idEmpresa = "+idEmpresa);
            }else if(nome != "Selecionar" && idCargo != -1 && idEmpresa == -1){
                resultados = instrucaoSQL.executeQuery("SELECT * FROM funcionarios WHERE nome like '%"+nome+"%' and idCargo ="+idCargo);
            }else if(nome != "Selecionar" && idCargo == -1 && idEmpresa != -1){
                resultados = instrucaoSQL.executeQuery("SELECT * FROM funcionarios WHERE nome like '%"+nome+"%' and idEmpresa = "+idEmpresa);
            }else if(nome != "Selecionar" && idCargo != -1 && idEmpresa != -1){
                resultados = instrucaoSQL.executeQuery("SELECT * FROM funcionarios WHERE nome like '%"+nome+"%' and idCargo ="+idCargo+"and idEmpresa = "+idEmpresa);
            }else{
                resultados = instrucaoSQL.executeQuery("SELECT * FROM funcionarios");
            }

            while (resultados.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultados.getInt("id"));
                funcionario.setNome(resultados.getString("nome"));
                funcionario.setSobrenome(resultados.getString("sobrenome"));
                funcionario.setDataNascimento(resultados.getString("dataNascimento"));
                funcionario.setEmail(resultados.getString("email"));
                if(resultados.getString("idCargo") != null) funcionario.setidCargo(Integer.parseInt(resultados.getString("idCargo")));
                if(resultados.getString("idEmpresa") != null) funcionario.setIdEmpresa(Integer.parseInt(resultados.getString("idEmpresa")));
                funcionario.setSalario(Double.parseDouble(resultados.getString("salario")));

                listaFuncionariomodel.addElement(funcionario);
                sqlPesquisarCargos(funcionario.getidCargo());
                sqlPesquisarempresas(funcionario.getIdEmpresa());
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar funcionários.");
            Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sqlCarregarCargos() {
        // conexão
        Connection conexao;
        // instrucao SQL
        Statement instrucaoSQL;
        // resultados
        ResultSet resultados;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * from cargos order by nome asc");
            comboboxCargo.removeAll();
            Cargo vazioCargo = new Cargo();
            vazioCargo.setNome("Selecionar");
            vazioCargo.setId(-1);
            comboboxCargo.addItem(vazioCargo);

            while (resultados.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(resultados.getInt("id"));
                cargo.setNome(resultados.getString("nome"));
                comboboxCargo.addItem(cargo);
            }
            comboboxCargo.updateUI();

            conexao.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar os cargos.");
            Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void sqlPesquisarCargos(int idCargo) {
        // conexão
        Connection conexao;
        // instrucao SQL
        Statement instrucaoSQL;
        // resultados
        ResultSet resultados;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

            // criando a instrução SQL
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * FROM cargos WHERE id = "+idCargo);



            while (resultados.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(resultados.getInt("id"));
                cargo.setNome(resultados.getString("nome"));

                listaCargomodel.addElement(cargo);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar os Cargos.");
            Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sqlCarregarEmpresas() {
        // conexão
        Connection conexao;
        // instrucao SQL
        Statement instrucaoSQL;
        // resultados
        ResultSet resultados;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * from empresas ");
            comboboxEmpresa.removeAll();
            Empresa vaziaEmpresa = new Empresa();
            vaziaEmpresa.setNome("Selecionar");
            vaziaEmpresa.setCnpj("");
            vaziaEmpresa.setId(-1);
            comboboxEmpresa.addItem(vaziaEmpresa);

            while (resultados.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(resultados.getInt("id"));
                empresa.setNome(resultados.getString("nome"));
                empresa.setCnpj(resultados.getString("cnpj"));
                empresa.setCep(resultados.getString("cep"));
                empresa.setEmail(resultados.getString("email"));
                comboboxEmpresa.addItem(empresa);
            }
            comboboxEmpresa.updateUI();

            conexao.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar as empresas.");
            Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void sqlPesquisarempresas(int idEmpresa) {
        // conexão
        Connection conexao;
        // instrucao SQL
        Statement instrucaoSQL;
        // resultados
        ResultSet resultados;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

            // criando a instrução SQL
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * FROM empresas WHERE id = "+idEmpresa);


            while (resultados.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(resultados.getInt("id"));
                empresa.setNome(resultados.getString("nome"));
                empresa.setCnpj(resultados.getString("cnpj"));
                empresa.setCep(resultados.getString("cep"));
                empresa.setEmail(resultados.getString("email"));

                listaEmpresamodel.addElement(empresa);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar os empresas.");
            Logger.getLogger(EmpresasInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
