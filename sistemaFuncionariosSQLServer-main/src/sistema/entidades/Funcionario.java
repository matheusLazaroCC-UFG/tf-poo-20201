package sistema.entidades;

public class Funcionario extends Pessoa {
    // variável destinado ao id do funcionário
    private int id;
    // variável destinado ao idCargo do funcionário
    private int idCargo;
    // variavel destinada ao idEmpresa do funcionario
    private int idEmpresa;
    // variável destinado ao salário atual do funcionário
    private double salario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return super.getNome();
    }

    public void setNome(String nome) {
        super.setNome(nome);
    }

    public String getSobrenome() {
        return super.getSobrenome();
    }

    public void setSobrenome(String sobrenome) {
        super.setSobrenome(sobrenome);
    }

    public String getDataNascimento() {
        return super.getDataNascimento();
    }

    public void setDataNascimento(String dataNascimento) {
        super.setDataNascimento(dataNascimento);
    }

    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public int getidCargo() {
        return idCargo;
    }

    public void setidCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return super.getNome() + " " + this.getSobrenome();
    }
}
