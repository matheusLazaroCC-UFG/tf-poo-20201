package sistema.telas;

import sistema.BancoDeDados;
import sistema.Navegador;
import sistema.entidades.Empresa;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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
import sistema.entidades.Empresa;

public class EmpresasEditar extends JPanel {
    Empresa empresaAtual;
    JLabel labelTitulo, labelNome, labelCnpj, labelCep, labelEmail;
    JTextField campoNome, campoCnpj, campoCep, campoEmail;
    JButton botaoGravar;

    public EmpresasEditar(){

    }

    public EmpresasEditar(Empresa empresa){
        empresaAtual = empresa;
        criarComponentes();
        criarEventos();
        Navegador.habilitaMenu();
    }

    private void criarComponentes() {
        setLayout(null);

        String textoLabel = "Editor Empresa " + empresaAtual.getNome();
        labelTitulo = new JLabel(textoLabel, JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelNome = new JLabel("Nome:", JLabel.LEFT);
        campoNome = new JTextField(empresaAtual.getNome());
        labelCnpj = new JLabel("CNPJ:", JLabel.LEFT);
        campoCnpj = new JTextField(empresaAtual.getCnpj());
        labelCep = new JLabel("CEP:", JLabel.LEFT);
        campoCep = new JTextField(empresaAtual.getCep());
        labelEmail = new JLabel("E-MAIL:", JLabel.LEFT);
        campoEmail = new JTextField(empresaAtual.getEmail());

        // POSICIONAR CORRETAMENTE
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

        botaoGravar.setBounds(560, 400, 130, 40);

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

        setVisible(true);
    }

    private void criarEventos(){
        botaoGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empresaAtual.setNome(campoNome.getText());
                empresaAtual.setCnpj(campoCnpj.getText());
                empresaAtual.setCep(campoCep.getText());
                empresaAtual.setEmail(campoEmail.getText());

                sqlAtualizarEmpresa();
            }
        });
    }


    private void sqlAtualizarEmpresa() {

        // conexÃ£o
        Connection conexao;
        // instrucao SQL
        PreparedStatement instrucaoSQL;
        // resultados
        ResultSet resultados;

        ValidarEmpresa v = new ValidarEmpresa();
        int flag = v.validar(campoNome.getText(), campoCnpj.getText(),
                campoCep.getText(),campoEmail.getText());
        if(flag == 0)
            return;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

            String template = "UPDATE empresas set nome=?, cnpj=?, cep=?, email=?";
            template = template+" WHERE id="+empresaAtual.getId();
            instrucaoSQL = conexao.prepareStatement(template);
            instrucaoSQL.setString(1, campoNome.getText());
            instrucaoSQL.setString(2, campoCnpj.getText());
            instrucaoSQL.setString(3, campoCep.getText());
            instrucaoSQL.setString(4, campoEmail.getText());
            instrucaoSQL.executeUpdate();

            JOptionPane.showMessageDialog(null, "Empresa atualizada com sucesso!");
            Navegador.inicio();

            conexao.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar a Empresa.");
            Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

