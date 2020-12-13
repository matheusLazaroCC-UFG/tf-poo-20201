package sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import sistema.entidades.Cargo;
import sistema.entidades.Empresa;
import sistema.entidades.Funcionario;
import sistema.telas.*;
import sistema.telas.EmpresasEditar;

public class Navegador {

	// Menu
	private static boolean menuConstruido;
	private static boolean menuHabilitado;
	private static JMenuBar menuBar;
	private static JMenu menuArquivo, menuFuncionarios, menuCargos, menuEmpresas;
	private static JMenu menuSair, menuInserir, menuEditar, menuGerenciar;
	private static JMenuItem miSair, miFuncionariosConsultar, miFuncionariosCadastrar, miCargosConsultar, miEmpresasConsultar, miGerencia;
	private static JMenuItem miCargosCadastrar, miEmpresasCadastrar;

	public static void login(){
		Sistema.tela = new Login();
		Sistema.frame.setTitle("Sistema de Funcionarios");
		Navegador.atualizarTela();
	}

	public static void inicio(){
		Sistema.tela = new Inicio();
		Sistema.frame.setTitle("Sistema de Funcionarios");
		Navegador.atualizarTela();
	}

	public static void funcionariosCadastrar(){
		Sistema.tela = new FuncionariosInserir();
		Sistema.frame.setTitle("Sistema de Funcionarios - Cadastrar funcionários");
		Navegador.atualizarTela();
	}

	public static void funcionariosConsultar(){
		Sistema.tela = new FuncionariosConsultar();
		Sistema.frame.setTitle("Sistema de Funcionarios - Consultar funcionários");
		Navegador.atualizarTela();
	}

	public static void funcionariosEditar(Funcionario funcionario){
		Sistema.tela = new FuncionariosEditar(funcionario);
		Sistema.frame.setTitle("Sistema de Funcionarios - Editar funcionários");
		Navegador.atualizarTela();
	}

	public static void cargosCadastrar(){
		Sistema.tela = new CargosInserir();
		Sistema.frame.setTitle("Sistema de Funcionarios - Cadastrar cargos");
		Navegador.atualizarTela();
	}

	public static void cargosConsultar(){
		Sistema.tela = new CargosConsultar();
		Sistema.frame.setTitle("Sistema de Funcionarios - Consultar cargos");
		Navegador.atualizarTela();
	}

	public static void cargosEditar(Cargo cargo){
		Sistema.tela = new CargosEditar(cargo);
		Sistema.frame.setTitle("Sistema de Funcionarios - Editar cargos");
		Navegador.atualizarTela();
	}

	public static void empresasCadastrar(){
		Sistema.tela = new EmpresasInserir();
		Sistema.frame.setTitle("Sistema de Funcionarios - Cadastrar empresas");
		Navegador.atualizarTela();
	}

	public static void empresasConsultar(){
		Sistema.tela = new EmpresasConsultar();
		Sistema.frame.setTitle("Sistema de Funcionarios - Consultar empresas");
		Navegador.atualizarTela();
	}

	public static void EmpresasEditar(Empresa empresa){
		Sistema.tela = new EmpresasEditar(empresa);
		Sistema.frame.setTitle("Sistema de Funcionarios - Editar empresas");
		Navegador.atualizarTela();
	}

	public static void Gerencia(){
		Sistema.tela = new Gerencia();
		Sistema.frame.setTitle("Sistema de Gerenciamento");
		Navegador.atualizarTela();
	}

	// Método que remove a tela atual e adiciona a próxima tela
	private static void atualizarTela(){
		Sistema.frame.getContentPane().removeAll();
		Sistema.tela.setVisible(true);
		Sistema.frame.add(Sistema.tela);

		Sistema.frame.setVisible(true);
	}



	private static void construirMenu(){
		if(!menuConstruido){
			menuConstruido = true;

			menuBar = new JMenuBar();

			// menu Arquivo
			menuArquivo = new JMenu("Arquivo");
			menuBar.add(menuArquivo);
			miSair = new JMenuItem("Sair");
			menuArquivo.add(miSair);

			// menu Inserir
			menuInserir = new JMenu("Inserir");
			menuBar.add(menuInserir);
			miFuncionariosCadastrar = new JMenuItem("Cadastrar Funcionario");
			menuInserir.add(miFuncionariosCadastrar);
			miCargosCadastrar = new JMenuItem("Cadastrar Cargo");
			menuInserir.add(miCargosCadastrar);
			miEmpresasCadastrar = new JMenuItem("Cadastrar Empresa");
			menuInserir.add(miEmpresasCadastrar);

			// menu EDITAR
			menuEditar = new JMenu("Editar");
			menuBar.add(menuEditar);
			miFuncionariosConsultar = new JMenuItem("Editar Funcionario");
			menuEditar.add(miFuncionariosConsultar);
			miCargosConsultar = new JMenuItem("Editar Cargo");
			menuEditar.add(miCargosConsultar);
			miEmpresasConsultar = new JMenuItem("Editar Empresa");
			menuEditar.add(miEmpresasConsultar);

			// Menu Gerenciar
			miGerencia = new JMenuItem("Gerenciar");
			menuBar.add(miGerencia);

			criarEventosMenu();

		}
	}

	public static void habilitaMenu(){
		if(!menuConstruido) construirMenu();
		if(!menuHabilitado){
			menuHabilitado = true;
			Sistema.frame.setJMenuBar(menuBar);
		}
	}

	public static void desabilitaMenu(){
		if(menuHabilitado){
			menuHabilitado = false;
			Sistema.frame.setJMenuBar(null);
		}
	}

	private static void criarEventosMenu() {
		miSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Funcionario
		miFuncionariosCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				funcionariosCadastrar();
			}
		});
		miFuncionariosConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				funcionariosConsultar();
			}
		});

		// Cargos
		miCargosCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargosCadastrar();
			}
		});
		miCargosConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargosConsultar();
			}
		});

		// Empresas
		miEmpresasCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				empresasCadastrar();
			}
		});
		miEmpresasConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				empresasConsultar();
			}
		});

		//GERENCIA
		miGerencia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gerencia();
			}
		});
	}
}