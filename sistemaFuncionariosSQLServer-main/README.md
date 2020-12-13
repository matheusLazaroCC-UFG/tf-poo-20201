# sistemaFuncionariosSQLServer

IDE utilizada:
        IntelliJ IDEA 2020.2.3 (Ultimate Edition)
        
Banco de Dados utilizado:
        SQL Server - SQL Server Management Studio 15.0.18358.0
        
Bibliotecas externas:
        mssql-jdbc-8.2.2.jre8 (Conexão com o Banco de Dados)
        
Estrutura do Banco de dados
        bd: Sistema_de_funcionarios
            tabela:     cargos
                                id      int     chave primária auto incrementável não nulo
                                nome    varchar(50)     nulo
                        funcionarios
                                id              int     chave primária  auto incrementável
                                nome            varchar(50) nulo
                                sobrenome       varchar(50) nulo
                                dataNascimento  varchar(50) nulo
                                email           varchar(50) nulo
                                cargo           int         nulo
                                salario         decimal(18, 2) nulo
                   
