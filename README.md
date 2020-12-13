# tf-poo-20201
Instituto de Informática - Universidade Federal de Goiás<br>
Desenvolvedores:<br>
<strong>Matheus Lázaro Honório da Silva</strong><br>
<strong>Vinicius Teles de Oliveira</strong><br>
<strong>Gabriel de Freitas Mendes</strong><br>
<strong>Geovanny Magalhães Novais</strong>
<br><br>

<strong>Sistema de Gerenciamento de Empresas-Funcionários com Java e SQL Server</strong><br>
IDE utilizada: IntelliJ IDEA 2020.2.3 (Ultimate Edition)<br>
Sistema Operacional: Windows 10 PRO / Ubuntu 18.4 (Instalação via terminal)<br>
Banco de Dados utilizado: SQL Server - SQL Server Management Studio 15.0.18358.0<br>

Bibliotecas externas: 
* mssql-jdbc-8.2.2.jre8 (Conexão com o Banco de Dados)
* JUnit

Tutorial de instação do sistema:

1 - Download do SQL Server
* Faça o download do SQL Server + SQL Server Management Studio (MSSMS), para configuração do banco de dados.<br><br>
2 - Configurações do SQL Server
* No MSSMS, entre com suas credenciais do Windows
* clique com o botão direito no servidor
* Propriedades
* Nas Propriedades do Servidor, clique em Segurança.
* Em Autenticação do servidor, selecione "Modo de Autenticação do SQL Server e do Windows" e em OK.
* Expanda o servidor, expanda "Segurança", expanda "Logons", e selecione "sa"
* Em "Geral", mude a senha para admin, conforme estabelecido neste projeto.
* Clique me Status, na lista esquerda das propriedades do Logon - sa
* Torne o Logon Habilitado, e clique em OK.
* Para criar as tabelas, utilize o script SQL, dentro da pasta docs, deste projeto.
* Para rodar o sistema, é necessário habilitar as portas TCP-IP.
* Para isso, abra "SQL Server 2019 Configuration Manager", instalado junto com o MSSMS.
* Expanda "Configuração de Rede do SQL Server", e clique em "Protocolos para MSSQLSERVER.
* Habilite TCP/IP
* Abra TCP/IP e clique em "Endereços IP"
* Torne todas as portas IP ativas, e digite a porta 1433 em todas as portas TCP.
* Clique em Aplicar e OK.
* Reinicie o Servidor: "SQL Server Configuration Manager -> Serviços do SQL Server -> SQL Server (MSSQLSERVER)
