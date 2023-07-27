import java.util.Scanner;

public class Empresa {
    Empregado empregados[] = new Empregado[1000];
    int qtdEmpregados = 0;

    public static void main(String[] args) throws Exception {
        Empresa empresa = new Empresa();
        Scanner scan = new Scanner(System.in);
        // Empregado empregado = new Empregado(null, null, 0, 0, null, 0);
        int op;
        do {
            imprimirMenu();
            op = scan.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Digite o cpf: ");
                    String cpf = scan.next();
                    System.out.println("Digite o Nome: ");
                    String nome = scan.next();
                    String sexo;
                    while(true){
                        System.out.println("Defina o sexo. Digite 'M' para masculino e 'F' para feminino: ");
                        sexo = scan.next();
                        if(sexo.equalsIgnoreCase("m") || sexo.equalsIgnoreCase("f")){
                            break;
                        } else {
                            System.out.println("\nEste sexo não existe!\n");
                        }
                    }
                    System.out.println("Digite o Cargo: ");
                    String cargo = scan.next();
                    System.out.println("Digite o Salario: ");
                    float salario = scan.nextFloat();
                    System.out.println("Digite a idade: ");
                    int idade = scan.nextInt();

                    empresa.cadastrarEmpregado(cpf, nome, idade, sexo, cargo, salario);
                    break;

                case 2:
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();

                    System.out.println(empresa.obterDadosEmpregado(cpf));
                    break;
                case 3:
                    System.out.println("Digite o a porcentagem de aumento: ");
                    float porcentagem = scan.nextFloat();
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();
                    Empregado emp = empresa.obterDadosEmpregado(cpf);

                    emp.aumentarSalario(porcentagem);
                    break;
                case 4:
                    if (empresa.qtdEmpregados() > 0) {
                        empresa.listarEmpregados();
                    } else {
                        System.out.println("Não existem empregados cadastrados!!!");
                    }
                    break;
                case 5:
                    System.out.println("Digite o genero: ");
                    String genero = scan.next();

                    empresa.listarEmpregados(genero);
                    break;
                case 6:
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();
                    empresa.excluirEmpregado(cpf);
                    break;
                case 7:
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();
                    System.out.println("Digite o novo nome: ");
                    nome = scan.next();
                    
                    empresa.alterarNome(cpf, nome);
                    break;
                case 8:
                    System.out.println("Digite o gênero: ");
                    sexo = scan.next();
                    System.out.println("Digite ao percentual: ");
                    porcentagem = scan.nextFloat();

                    empresa.aumentarSalario(porcentagem, sexo);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                System.out.println("Opção inválida! Tente novamente.");
            }
        } while (op != 0);
    }

    public static void imprimirMenu() {
        System.out.println("\n\n1 - Cadastrar empregado");
        System.out.println("2 - Consultar dados de um empregado pelo cpf");
        System.out.println("3 - Aumentar o salário do empregado");
        System.out.println("4 - Listar Empregados");
        System.out.println("5 - Listar Empregados por genero");
        System.out.println("6 - Excluir empregado pelo cpf");
        System.out.println("7 - Alterar o nome do empregado pelo cpf");
        System.out.println("8 - Aumentar os salários dos empregados pelo gênero");
        System.out.println("0 - Sair do sistema");
        System.out.println("Digite a opção Desejada:");
    }

    public boolean cadastrarEmpregado(Empregado empregado) {
        if (qtdEmpregados < 1000) {
            empregados[qtdEmpregados++] = empregado;
            return true;
        } else {
            return false;
        }
    }

    public boolean cadastrarEmpregado(String cpf, String nome, int idade, String sexo, String cargo, float salario) {
        return cadastrarEmpregado(new Empregado(cpf, nome, idade, sexo, cargo, salario));
    }

    public Empregado obterDadosEmpregado(String cpf) {
        for (int i = 0; i < qtdEmpregados; i++) {
            if (empregados[i].getCpf().equalsIgnoreCase(cpf)) {
                return empregados[i];
            }
        }
        return null;
    }

    public Empregado obterDadosEmpregado(String cpf, int qtd) {
        for (int i = 0; i < qtdEmpregados; i++) {
            if (empregados[i].getCpf().equalsIgnoreCase(cpf)) {
                qtd = i;
                return empregados[i];
            }
        }
        return null;
    }

    public void listarEmpregados() {
        for (int i = 0; i < qtdEmpregados; i++) {
            System.out.println("Cpf: " + empregados[i].getCpf() + " nome: " + empregados[i].getNome() + " Salario: " + empregados[i].getSalario());
        }
    }

    public void listarEmpregados(String genero) {
        boolean checkEmpregados = false;
        for (int i = 0; i < qtdEmpregados; i++) {
            if (empregados[i].getSexo().equals(genero)) {
                System.out.println("Nome: " + empregados[i].getNome() + "CPF: " + empregados[i].getCpf());
                checkEmpregados = true;
            }
            if(!checkEmpregados){
                System.out.println("Não existem empregados com esse gênero");
            }
        }
    }

    public int qtdEmpregados() {
        return qtdEmpregados;
    }

    public void excluirEmpregado(String cpf){
        for(int i = 0; i < qtdEmpregados; i++){
            if(cpf.equalsIgnoreCase(empregados[i].getCpf())){
                for(int j = i; j < qtdEmpregados-1; j++){
                    empregados[j] = empregados[j+1];
                }

                empregados[qtdEmpregados - 1] = null;

                qtdEmpregados--;
            }
        }

    }

    public void alterarNome(String cpf, String nome) {
        Empregado empregado = obterDadosEmpregado(cpf);

        empregado.setNome(nome);
    }

    public void aumentarSalario(float porcentagem, String genero){

        for(int i = 0; i < qtdEmpregados; i++){
            if(empregados[i].getSexo().equalsIgnoreCase(genero)){
                empregados[i].aumentarSalario(porcentagem);
            }
        }

    }
}
