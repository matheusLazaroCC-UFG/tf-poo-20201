create table cargos
(
    id   int identity
        constraint PK_cargos
            primary key,
    nome varchar(50)
)
go

create table empresas
(
    id    int identity
        constraint PK_empresas
            primary key,
    nome  varchar(50),
    cnpj  varchar(50),
    cep   varchar(50),
    email varchar(50)
)
go

create table funcionarios
(
    id             int identity
        constraint PK_funcionarios
            primary key,
    nome           varchar(50),
    sobrenome      varchar(50),
    dataNascimento varchar(8),
    email          varchar(50),
    idCargo        int not null,
    salario        decimal(18, 2),
    idEmpresa      int not null
)
go


