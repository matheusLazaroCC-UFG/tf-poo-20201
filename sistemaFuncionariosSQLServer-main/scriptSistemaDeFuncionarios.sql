USE [master]
GO
/****** Object:  Database [sistema_de_funcionarios]    Script Date: 03/12/2020 12:58:02 ******/
CREATE DATABASE [sistema_de_funcionarios]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'sistema_de_funcionarios', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\sistema_de_funcionarios.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'sistema_de_funcionarios_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\sistema_de_funcionarios_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [sistema_de_funcionarios] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [sistema_de_funcionarios].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [sistema_de_funcionarios] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET ARITHABORT OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [sistema_de_funcionarios] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [sistema_de_funcionarios] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET  DISABLE_BROKER 
GO
ALTER DATABASE [sistema_de_funcionarios] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [sistema_de_funcionarios] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET RECOVERY FULL 
GO
ALTER DATABASE [sistema_de_funcionarios] SET  MULTI_USER 
GO
ALTER DATABASE [sistema_de_funcionarios] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [sistema_de_funcionarios] SET DB_CHAINING OFF 
GO
ALTER DATABASE [sistema_de_funcionarios] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [sistema_de_funcionarios] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [sistema_de_funcionarios] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [sistema_de_funcionarios] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'sistema_de_funcionarios', N'ON'
GO
ALTER DATABASE [sistema_de_funcionarios] SET QUERY_STORE = OFF
GO
USE [sistema_de_funcionarios]
GO
/****** Object:  Table [dbo].[cargos]    Script Date: 03/12/2020 12:58:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cargos](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](50) NULL,
 CONSTRAINT [PK_cargos] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[funcionarios]    Script Date: 03/12/2020 12:58:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[funcionarios](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](50) NULL,
	[sobrenome] [varchar](50) NULL,
	[dataNascimento] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[cargo] [int] NULL,
	[salario] [decimal](18, 2) NULL,
 CONSTRAINT [PK_funcionarios] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
USE [master]
GO
ALTER DATABASE [sistema_de_funcionarios] SET  READ_WRITE 
GO
