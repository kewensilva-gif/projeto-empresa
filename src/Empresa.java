import java.util.Scanner;

public class Empresa {
    Empregado empregados[] = new Empregado[1000];
    Dependente dependentes[] = new Dependente[1000];
    int qtdEmpregados = 0;
    int qtdDependente = 0;

    public static void main(String[] args) throws Exception {
        Empresa empresa = new Empresa();
        Scanner scan = new Scanner(System.in);
        Validacoes validacoes = new Validacoes();

        
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

                    String sexo = validacoes.validaGenero();
                
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
                    System.out.println("Digite a porcentagem de aumento: ");
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
                    sexo = validacoes.validaGenero();

                    empresa.listarEmpregados(sexo);
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
                    sexo = validacoes.validaGenero();
                    System.out.println("Digite ao percentual: ");
                    porcentagem = scan.nextFloat();

                    empresa.aumentarSalario(porcentagem, sexo);
                    break;
                case 9:
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();
                    Empregado empregado = empresa.obterDadosEmpregado(cpf);
                    empresa.cadastrarDependente(empregado);
                    break;
                case 10:
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();

                    empresa.excluirDependente(cpf);

                    break;
                case 11:
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();

                    empresa.listarDependentes(cpf);

                    break;
                case 12:

                    empresa.listarEmpregadosDependentes();

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
        System.out.println("2 - Consultar dados de um empregado");
        System.out.println("3 - Aumentar o salário do empregado");
        System.out.println("4 - Listar Empregados");
        System.out.println("5 - Listar Empregados por genero");
        System.out.println("6 - Excluir empregado");
        System.out.println("7 - Alterar o nome do empregado");
        System.out.println("8 - Aumentar os salários dos empregados pelo gênero");
        System.out.println("9 - Cadastrar dependente");
        System.out.println("10 - Excluir dependente");
        System.out.println("11 - Listar dependentes e quantidade");
        System.out.println("12 - Listar empregados e seus dependentes");
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
            System.out.println("Cpf: " + empregados[i].getCpf() + " nome: " + empregados[i].getNome() + " Salario: "
                    + empregados[i].getSalario());
        }
    }

    public void listarEmpregados(String genero) {
        boolean checkEmpregados = false;
        for (int i = 0; i < qtdEmpregados; i++) {
            if (empregados[i].getSexo().equals(genero)) {
                System.out.println("Nome: " + empregados[i].getNome() + "CPF: " + empregados[i].getCpf());
                checkEmpregados = true;
            }
            if (!checkEmpregados) {
                System.out.println("Não existem empregados com esse gênero");
            }
        }
    }

    public int qtdEmpregados() {
        return qtdEmpregados;
    }

    public void excluirEmpregado(String cpf) {
        for (int i = 0; i < qtdEmpregados; i++) {
            if (cpf.equalsIgnoreCase(empregados[i].getCpf())) {
                for (int j = i; j < qtdEmpregados - 1; j++) {
                    empregados[j] = empregados[j + 1];
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

    public void aumentarSalario(float porcentagem, String genero) {

        for (int i = 0; i < qtdEmpregados; i++) {
            if (empregados[i].getSexo().equalsIgnoreCase(genero)) {
                empregados[i].aumentarSalario(porcentagem);
            }
        }

    }

    public void cadastrarDependente(Empregado emp1) {
        Validacoes validacoes = new Validacoes();
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o cpf: ");
        String cpf = scan.next();
        System.out.println("Digite o nome: ");
        String nome = scan.next();
        System.out.println("Digite o sexo: ");
        String sexo = scan.next();
        
        int idade = validacoes.validaIdade();

        Dependente dependente = new Dependente(cpf, nome, sexo, idade, emp1.getCpf());
        dependentes[qtdDependente] = dependente;
        qtdDependente++;
    }

    public void listarDependentes(String cpf) {
        int cont = 0;
        for (int i = 0; i < qtdDependente; i++) {
            if (dependentes[i].getCpfEmpregado().equalsIgnoreCase(cpf)) {
                System.out.println("Nome: " + dependentes[i].getNome());
                System.out.println("CPF: " + dependentes[i].getCpf());
                System.out.println("Idade: " + dependentes[i].getIdade());
                System.out.println("Sexo: " + dependentes[i].getSexo());
                cont++;
            }
        }

        System.out.println("Quantidade de dependentes: " + cont);
    }

    public void listarEmpregadosDependentes() {
        for (int i = 0; i < qtdEmpregados; i++) {
            System.out.println("Empregado: " + empregados[i].getNome());
            for (int j = 0; j < qtdDependente; j++) {
                if (empregados[i].getCpf().equalsIgnoreCase(dependentes[j].getCpfEmpregado())) {
                    System.out.println("Dependente: " + dependentes[j].getNome());
                }
            }
            System.out.println("\n\n");
        }
    }

    public void excluirDependente(String cpf) {
        for (int i = 0; i < qtdDependente; i++) {
            if (cpf.equalsIgnoreCase(dependentes[i].getCpf())) {
                for (int j = i; j < qtdDependente - 1; j++) {
                    dependentes[j] = dependentes[j + 1];
                }

                dependentes[qtdDependente - 1] = null;

                qtdDependente--;
            }
        }

    }
}
