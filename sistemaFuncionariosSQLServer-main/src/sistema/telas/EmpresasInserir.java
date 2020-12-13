package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import sistema.BancoDeDados;
import sistema.Navegador;
import sistema.entidades.Cargo;
import sistema.entidades.Empresa;
import sistema.entidades.Funcionario;

public class EmpresasInserir extends JPanel {

    JLabel labelTitulo,labelNome, labelCnpj, labelEmail, labelCep;
    JTextField campoNome, campoEmail, campoCnpj;
    JFormattedTextField campoCep;
    JButton botaoGravar;

    public EmpresasInserir(){
        criarComponentes();
        criarEventos();
        Navegador.habilitaMenu();
    }

    private void criarComponentes() {
        setLayout(null);

        labelTitulo = new JLabel("Cadastro de Empresa", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));

        labelNome = new JLabel("Nome:", JLabel.LEFT);
        campoNome = new JTextField();

        labelCnpj = new JLabel("Cnpj:", JLabel.LEFT);
        campoCnpj = new JTextField();

        labelCep = new JLabel("cep:", JLabel.LEFT);
        campoCep = new JFormattedTextField();

        labelEmail = new JLabel("Email:", JLabel.LEFT);
        campoEmail = new JTextField();

        try {
            MaskFormatter dateMask= new MaskFormatter("#####-###");
            dateMask.install(campoCep);
        } catch (ParseException ex) {
            Logger.getLogger(EmpresasInserir.class.getName()).log(Level.SEVERE, null, ex);
        }

        botaoGravar = new JButton("Adicionar");

        labelTitulo.setBounds(20, 20, 660, 40);

        labelNome.setBounds(150, 80, 400, 20);
        campoNome.setBounds(150, 100, 400, 40);

        labelCnpj.setBounds(150, 140, 400, 20);
        campoCnpj.setBounds(150, 160, 400, 40);

        labelCep.setBounds(150, 200, 400, 20);
        campoCep.setBounds(150, 220, 400, 40);

        labelEmail.setBounds(150, 260, 400, 20);
        campoEmail.setBounds(150, 280, 400, 40);



        botaoGravar.setBounds(420, 360, 130, 40);

        add(labelTitulo);

        add(labelNome);
        add(campoNome);

        add(labelCnpj);
        add(campoCnpj);

        add(labelCep);
        add(campoCep);

        add(labelEmail);
        add(campoEmail);

        add(botaoGravar);

        sqlCarregarEmpresas();

        setVisible(true);
    }

    private void criarEventos() {
        botaoGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empresa novaEmpresa = new Empresa();
                novaEmpresa.setNome(campoNome.getText());
                novaEmpresa.setCnpj(campoCnpj.getText());
                novaEmpresa.setCep(campoCep.getText());
                novaEmpresa.setEmail(campoEmail.getText());

                sqlInserirEmpresa(novaEmpresa);
            }
        });
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
            resultados = instrucaoSQL.executeQuery("SELECT * from Empresas order by nome asc");

            while (resultados.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(resultados.getInt("id"));
                empresa.setNome(resultados.getString("nome"));
                empresa.setCnpj(resultados.getString("cnpj"));
                empresa.setCep(resultados.getString("cep"));
                empresa.setEmail(resultados.getString("email"));
            }

            conexao.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar as Empresas.");
            Logger.getLogger(EmpresasInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void sqlInserirEmpresa(Empresa novaEmpresa) {

        // conexão
        Connection conexao;
        // instrucao SQL
        PreparedStatement instrucaoSQL;

        ValidarEmpresa v = new ValidarEmpresa();
        int flag = v.validar(campoNome.getText(), campoCnpj.getText(),
                campoCep.getText(),campoEmail.getText());
        if(flag == 0)
            return;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

            String template = "INSERT INTO empresas (nome,cnpj,cep,email)";
            template = template+" VALUES (?,?,?,?)";
            instrucaoSQL = conexao.prepareStatement(template);
            instrucaoSQL.setString(1, novaEmpresa.getNome());
            instrucaoSQL.setString(2, novaEmpresa.getCnpj());
            instrucaoSQL.setString(3, novaEmpresa.getCep());
            instrucaoSQL.setString(4, novaEmpresa.getEmail());

            instrucaoSQL.executeUpdate();

            JOptionPane.showMessageDialog(null, "Empresa adicionada com sucesso!");
            Navegador.inicio();

            conexao.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar a Empresa.");
            Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}