import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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


        empresa.lerArquivoEmpregados();
        empresa.lerArquivoDependentes();
        int op;
        do {
            imprimirMenu();
            op = scan.nextInt();

            switch (op) {
                case 1:
                    
                    String cpf = empresa.verificaCpfExistente();
                    
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
                    System.out.println("\n\nConsultar dados de um empregado\n\n");
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();

                    System.out.println(empresa.obterDadosEmpregado(cpf));
                    break;
                case 3:
                    System.out.println("\n\nAumentar salário do empregado\n\n");
                    System.out.println("Digite a porcentagem de aumento: ");
                    float porcentagem = scan.nextFloat();
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();
                    Empregado emp = empresa.obterDadosEmpregado(cpf);

                    emp.aumentarSalario(porcentagem);
                    break;
                case 4:
                    System.out.println("\n\nListar empregados\n\n");
                    if (empresa.qtdEmpregados() > 0) {
                        empresa.listarEmpregados();
                    } else {
                        System.out.println("Não existem empregados cadastrados!!!");
                    }
                    break;
                case 5:
                    System.out.println("\n\nCadastro de empregado por gênero\n\n");
                    sexo = validacoes.validaGenero();

                    empresa.listarEmpregados(sexo);
                    break;
                case 6:
                    System.out.println("\n\nExcluir empregado\n\n");
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();
                    empresa.excluirEmpregado(cpf);
                    break;
                case 7:
                    System.out.println("\n\nAlterar nome do empregado\n\n");
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();
                    System.out.println("Digite o novo nome: ");
                    nome = scan.next();

                    empresa.alterarNome(cpf, nome);
                    break;
                case 8:
                    System.out.println("\n\nAumentar os salários dos empregados pelo gênero\n\n");
                    sexo = validacoes.validaGenero();
                    System.out.println("Digite ao percentual: ");
                    porcentagem = scan.nextFloat();

                    empresa.aumentarSalario(porcentagem, sexo);
                    break;
                case 9:
                    System.out.println("\n\nCadastrar dependente\n\n");
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();
                    Empregado empregado = empresa.obterDadosEmpregado(cpf);
                    empresa.cadastrarDependente(empregado);
                    break;
                case 10:
                    System.out.println("\n\nExcluir dependente\n\n");
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();

                    empresa.excluirDependente(cpf);

                    break;
                case 11:
                    System.out.println("\n\nListar dependentes e quantidade\n\n");
                    System.out.println("Digite o cpf: ");
                    cpf = scan.next();

                    empresa.listarDependentes(cpf);

                    break;
                case 12:
                    System.out.println("\n\nListar empregados e seus dependentes\n\n");
                    empresa.listarEmpregadosDependentes();

                    break;
                case 0:
                    empresa.preencherArquivoEmpregados();
                    empresa.preencherArquivoDependentes();
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
        System.out.println("Digite o cpf do dependente: ");
        String cpf = scan.next();
        System.out.println("Digite o nome do dependente: ");
        String nome = scan.next();
        System.out.println("Digite o sexo do dependente: ");
        String sexo = scan.next();

        int idade = validacoes.validaIdade();

        Dependente dependente = new Dependente(cpf, nome, sexo, idade, emp1.getCpf());
        dependentes[qtdDependente] = dependente;
        qtdDependente++;
    }

    public void listarDependentes(String cpf) {
        
        for (int i = 0; i < qtdDependente; i++) {
            if (dependentes[i].getCpfEmpregado().equalsIgnoreCase(cpf)) {
                System.out.println("Nome: " + dependentes[i].getNome());
                System.out.println("CPF: " + dependentes[i].getCpf());
                System.out.println("Idade: " + dependentes[i].getIdade());
                System.out.println("Sexo: " + dependentes[i].getSexo());
                
            }
        }

        System.out.println("Quantidade de dependentes: " + qtdDependente);
    }


    public boolean obterDadosDependente(String cpf) {
        
        for (int i = 0; i < qtdDependente; i++) {
            if (dependentes[i].getCpfEmpregado().equalsIgnoreCase(cpf)) {
                return true;
            }
        }
        return false;
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

    public void lerArquivoEmpregados() {
        File empregadosArquivo = new File("D:/prog3/projetos-java/projeto-empresa/src/empregados.txt");
        try (BufferedReader lerArquivo = new BufferedReader(new FileReader(empregadosArquivo))) {
            String empregado = lerArquivo.readLine();

            //System.out.println(empregado);
            while (empregado != null) {
               String[] atributo = empregado.split(",");
                //System.out.println(atributo[1]);
                empregados[qtdEmpregados++] = new Empregado(atributo[0], atributo[1], Integer.parseInt(atributo[2]), atributo[3], atributo[4], Float.parseFloat(atributo[5]));

                empregado = lerArquivo.readLine();
           }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void preencherArquivoEmpregados() {
        File empregadosArquivo = new File("D:/prog3/projetos-java/projeto-empresa/src/empregados.txt");
        try (BufferedWriter escreveArquivo = new BufferedWriter(new FileWriter(empregadosArquivo))) {

            for (int i = 0; i < qtdEmpregados; i++) {
                escreveArquivo.write(empregados[i].getCpf() + "," + empregados[i].getNome() + ","
                        + empregados[i].getIdade() + "," + empregados[i].getSexo() + "," + empregados[i].getCargo()
                        + "," + String.format("%.2f", empregados[i].getSalario()) + "\n");
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void lerArquivoDependentes() {
        File dependentesArquivo = new File("D:/prog3/projetos-java/projeto-empresa/src/dependentes.txt");
        try (BufferedReader lerArquivo = new BufferedReader(new FileReader(dependentesArquivo))) {
            String dependente = lerArquivo.readLine();

            //System.out.println(empregado);
            while (dependente != null) {
               String[] atributo = dependente.split(",");
                //System.out.println(atributo[1]);
                dependentes[qtdDependente++] = new Dependente(atributo[0], atributo[1], atributo[2], Integer.parseInt(atributo[3]), atributo[4]);

                dependente = lerArquivo.readLine();
           }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void preencherArquivoDependentes() {
        File dependentesArquivo = new File("D:/prog3/projetos-java/projeto-empresa/src/dependentes.txt");
        try (BufferedWriter escreveArquivo = new BufferedWriter(new FileWriter(dependentesArquivo))) {

            for (int i = 0; i < qtdDependente; i++) {
                escreveArquivo.write(dependentes[i].getCpf() + "," + dependentes[i].getNome() + "," + dependentes[i].getIdade() + "," + dependentes[i].getSexo() + "," + dependentes[i].getCpfEmpregado() + "\n");
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public String verificaCpfExistente(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Digite o cpf: ");
            String cpf = scan.next();
            if(obterDadosEmpregado(cpf) == null && !obterDadosDependente(cpf)){
                return cpf;
            }
            else {
                System.out.println("CPF já cadastrado. Digite um novo CPF.");
            }
        }
    }
}
