public class Dependente {
    private String cpf;
    private String nome;
    private String sexo;
    private int idade;
    private String cpfEmpregado;

    public String getCpfEmpregado() {
        return cpfEmpregado;
    }

    public void setCpfEmpregado(String cpfEmpregado) {
        this.cpfEmpregado = cpfEmpregado;
    }

    public Dependente(String cpf, String nome, String sexo, int idade, String cpfEmpregado) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.cpfEmpregado = cpfEmpregado;
    }

    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getSexo() {
        return sexo;
    }


    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public int getIdade() {
        return idade;
    }


    public void setIdade(int idade) {
        this.idade = idade;
    }

}