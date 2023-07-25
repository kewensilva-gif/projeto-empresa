public class Empregado {
    private String cpf;
    private String nome;
    private int idade;
    private char sexo;
    private String cargo;
    private float salario;

    public Empregado(String cpf, String nome, int idade, char sexo, String cargo, float salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getCpf(){
        return cpf;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }

    public int getIdade(){
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public char getSexo(){
        return sexo;
    }

    public void setSexo(char sexo){
        this.sexo = sexo;
    }

    public String getCargo(){
        return cargo;
    }

    public void setCargo(String cargo){
        this.cargo = cargo;
    }

    public float getSalario(){
        return salario;
    }

    public void aumentarSalario(float percentual){
        salario += salario * percentual/100;
    }
}
