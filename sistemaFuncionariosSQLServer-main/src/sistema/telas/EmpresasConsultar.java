package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sistema.BancoDeDados;
import sistema.Navegador;
import sistema.entidades.Empresa;

public class EmpresasConsultar extends JPanel {
    Empresa empresaAtual;
    JLabel labelTitulo, labelEmpresa;
    JTextField campoEmpresa;
    JButton botaoPesquisar, botaoEditar, botaoExcluir;
    DefaultListModel<Empresa> listasEmpresasModelo = new DefaultListModel();
    JList<Empresa> listaEmpresas;

    public EmpresasConsultar(){
        criarComponentes();
        criarEventos();
        Navegador.habilitaMenu();
    }

    private void criarComponentes() {
        setLayout(null);

        labelTitulo = new JLabel("Consulta de empresas", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelEmpresa = new JLabel("Nome do empresa", JLabel.LEFT);
        campoEmpresa = new JTextField();
        botaoPesquisar = new JButton("Pesquisar empresa");
        botaoEditar = new JButton("Editar empresa");
        botaoEditar.setEnabled(false);
        botaoExcluir = new JButton("Excluir empresa");
        botaoExcluir.setEnabled(false);
        listasEmpresasModelo = new DefaultListModel();
        listaEmpresas = new JList();
        listaEmpresas.setModel(listasEmpresasModelo);
        listaEmpresas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);


        labelTitulo.setBounds(20, 20, 660, 40);
        labelEmpresa.setBounds(150, 120, 400, 20);
        campoEmpresa.setBounds(150, 140, 400, 40);
        botaoPesquisar.setBounds(560, 140, 130, 40);
        listaEmpresas.setBounds(150, 200, 400, 240);
        botaoEditar.setBounds(560, 360, 130, 40);
        botaoExcluir.setBounds(560, 400, 130, 40);

        add(labelTitulo);
        add(labelEmpresa);
        add(campoEmpresa);
        add(listaEmpresas);
        add(botaoPesquisar);
        add(botaoEditar);
        add(botaoExcluir);

        setVisible(true);
    }

    private void criarEventos() {
        sqlPesquisarempresas();
        botaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlPesquisarempresas(campoEmpresa.getText());
            }
        });
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navegador.EmpresasEditar(empresaAtual);
            }
        });
        botaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlDeletarempresa();
            }
        });
        listaEmpresas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                empresaAtual = listaEmpresas.getSelectedValue();
                if(empresaAtual == null){
                    botaoEditar.setEnabled(false);
                    botaoExcluir.setEnabled(false);
                }else{
                    botaoEditar.setEnabled(true);
                    botaoExcluir.setEnabled(true);
                }
            }
        });
    }

    private void sqlPesquisarempresas() {
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
            resultados = instrucaoSQL.executeQuery("SELECT * FROM empresas");

            listasEmpresasModelo.clear();

            while (resultados.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(resultados.getInt("id"));
                empresa.setNome(resultados.getString("nome"));
                empresa.setCnpj(resultados.getString("cnpj"));
                empresa.setCep(resultados.getString("cep"));
                empresa.setEmail(resultados.getString("email"));

                listasEmpresasModelo.addElement(empresa);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar os empresas.");
            Logger.getLogger(EmpresasInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sqlPesquisarempresas(String nome) {
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
            resultados = instrucaoSQL.executeQuery("SELECT * FROM empresas WHERE nome like '%"+nome+"%'");

            listasEmpresasModelo.clear();

            while (resultados.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(resultados.getInt("id"));
                empresa.setNome(resultados.getString("nome"));
                empresa.setCnpj(resultados.getString("cnpj"));
                empresa.setCep(resultados.getString("cep"));
                empresa.setEmail(resultados.getString("email"));

                listasEmpresasModelo.addElement(empresa);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar os empresas.");
            Logger.getLogger(EmpresasInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sqlDeletarempresa() {

        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o empresa "+empresaAtual.getNome()+"?", "Excluir", JOptionPane.YES_NO_OPTION);
        if(confirmacao == JOptionPane.YES_OPTION){

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
                instrucaoSQL.executeUpdate("DELETE empresas WHERE id="+empresaAtual.getId()+"");

                JOptionPane.showMessageDialog(null, "empresa deletado com sucesso!");
                Navegador.inicio();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o empresa.");
                Logger.getLogger(EmpresasInserir.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
