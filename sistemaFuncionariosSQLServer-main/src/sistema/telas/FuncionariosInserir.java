package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import sistema.BancoDeDados;
import sistema.Navegador;
import sistema.entidades.Cargo;
import sistema.entidades.Empresa;
import sistema.entidades.Funcionario;

public class FuncionariosInserir extends JPanel {

    JLabel labelTitulo, labelNome, labelSobrenome, labelDataNascimento, labelEmail, labelCargo, labelEmpresa, labelSalario;
    JTextField campoNome, campoSobrenome, campoEmail;
    JFormattedTextField campoDataNascimento, campoSalario;
    JComboBox<Cargo> comboboxCargo;
    JComboBox<Empresa> comboboxEmpresa;
    JButton botaoGravar;
    MaskFormatter mkSalario;

    public FuncionariosInserir(){
        criarComponentes();
        criarEventos();
        Navegador.habilitaMenu();
    }


    private void criarComponentes() {
        setLayout(null);

        labelTitulo = new JLabel("Cadastro de Funcionario", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelNome = new JLabel("Nome:", JLabel.LEFT);
        campoNome = new JTextField();
        labelSobrenome = new JLabel("Sobrenome:", JLabel.LEFT);
        campoSobrenome = new JTextField();
        labelDataNascimento = new JLabel("Data de Nascimento:", JLabel.LEFT);
        campoDataNascimento = new JFormattedTextField();
        try {
            MaskFormatter dateMask= new MaskFormatter("##/##/####");
            dateMask.install(campoDataNascimento);
        } catch (ParseException ex) {
            Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelEmail = new JLabel("E-mail:", JLabel.LEFT);
        campoEmail = new JTextField();
        labelCargo = new JLabel("Cargo:", JLabel.LEFT);
        comboboxCargo = new JComboBox();
        labelEmpresa = new JLabel("Empresa:", JLabel.LEFT);
        comboboxEmpresa = new JComboBox();
        labelSalario = new JLabel("Salário:", JLabel.LEFT);
        DecimalFormat formatter = new DecimalFormat("###0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        campoSalario = new JFormattedTextField(formatter);
        campoSalario.setValue(0.00);
        botaoGravar = new JButton("Adicionar");

        labelTitulo.setBounds(20, 20, 660, 40);
        labelNome.setBounds(150, 80, 400, 20);
        campoNome.setBounds(150, 100, 400, 40);
        labelSobrenome.setBounds(150, 140, 400, 20);
        campoSobrenome.setBounds(150, 160, 400, 40);
        labelDataNascimento.setBounds(150, 200, 400, 20);
        campoDataNascimento.setBounds(150, 220, 400, 40);
        labelEmail.setBounds(150, 260, 400, 20);
        campoEmail.setBounds(150, 280, 400, 40);
        labelCargo.setBounds(150, 320, 400, 20);
        comboboxCargo.setBounds(150, 340, 400, 40);
        labelEmpresa.setBounds(150, 380, 400, 20);
        comboboxEmpresa.setBounds(150, 400, 400, 40);
        labelSalario.setBounds(150, 440, 400, 20);
        campoSalario.setBounds(150, 460, 400, 40);
        botaoGravar.setBounds(420, 520, 130, 40);

        add(labelTitulo);
        add(labelNome);
        add(campoNome);
        add(labelSobrenome);
        add(campoSobrenome);
        add(labelDataNascimento);
        add(campoDataNascimento);
        add(labelEmail);
        add(campoEmail);
        add(labelCargo);
        add(comboboxCargo);
        add(labelEmpresa);
        add(comboboxEmpresa);
        add(labelSalario);
        add(campoSalario);
        add(botaoGravar);

        sqlCarregarCargos();
        sqlCarregarEmpresas();

        setVisible(true);
    }



    private void criarEventos() {
        botaoGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Funcionario novoFuncionario = new Funcionario();
                novoFuncionario.setNome(campoNome.getText());
                novoFuncionario.setSobrenome(campoSobrenome.getText());
                novoFuncionario.setDataNascimento(campoDataNascimento.getText());
                novoFuncionario.setEmail(campoEmail.getText());

                Cargo cargoSelecionado = (Cargo) comboboxCargo.getSelectedItem();
                if(cargoSelecionado != null) novoFuncionario.setidCargo(cargoSelecionado.getId());

                Empresa empresaSelecionada = (Empresa) comboboxEmpresa.getSelectedItem();
                if(empresaSelecionada != null) novoFuncionario.setIdEmpresa(empresaSelecionada.getId());

            // -----------> SALARIO ???
                novoFuncionario.setSalario(Double.valueOf(campoSalario.getText().replace(",", ".")));

                sqlInserirFuncionario(novoFuncionario);
            }
        });
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

    private void sqlInserirFuncionario(Funcionario novoFuncionario) {
        // conexão
        Connection conexao;
        // instrucao SQL
        PreparedStatement instrucaoSQL;

        ValidarFuncionario v = new ValidarFuncionario();
        int flag = v.validar(campoNome.getText(), campoSobrenome.getText(),
                campoDataNascimento.getText(),
                campoEmail.getText(), campoSalario.getText());
        if(flag == 0)
            return;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

            String template = "INSERT INTO funcionarios (nome,sobrenome,dataNascimento,email,idCargo,salario,idEmpresa)";
            template = template+" VALUES (?,?,?,?,?,?,?)";
            instrucaoSQL = conexao.prepareStatement(template);
            instrucaoSQL.setString(1, novoFuncionario.getNome());
            instrucaoSQL.setString(2, novoFuncionario.getSobrenome());
            instrucaoSQL.setString(3, novoFuncionario.getDataNascimento());
            instrucaoSQL.setString(4, novoFuncionario.getEmail());
            if(novoFuncionario.getidCargo() > 0){
                instrucaoSQL.setInt(5, novoFuncionario.getidCargo());
            }else{
                instrucaoSQL.setNull(5, java.sql.Types.INTEGER);
            }
            instrucaoSQL.setString(6, Double.toString(novoFuncionario.getSalario()));
            if(novoFuncionario.getIdEmpresa() > 0){
                instrucaoSQL.setInt(7, novoFuncionario.getIdEmpresa());
            }else{
                instrucaoSQL.setNull(7, java.sql.Types.INTEGER);
            }
            instrucaoSQL.executeUpdate();

            JOptionPane.showMessageDialog(null, "Funcionário adicionado com sucesso!");
            Navegador.inicio();

            conexao.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar o Funcionário.");
            Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
