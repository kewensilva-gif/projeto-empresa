import java.util.Scanner;

public class Validacoes {
     public String validaGenero() {
        String sexo;
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Defina o sexo. Digite 'M' para masculino e 'F' para feminino: ");
            sexo = scan.next();
            if (sexo.equalsIgnoreCase("m") || sexo.equalsIgnoreCase("f")) {
                return sexo;
            } else {
                System.out.println("\nEste sexo não existe!\n");
            }
        }
    }

    public int validaIdade(){
        int idade;
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Digite a idade: ");
            idade = scan.nextInt();
            if (idade < 120 && idade > 18) {
                return idade;
            } else {
                System.out.println("\nIdade inválida! Digite uma idade entre 18 e 120 anos\n");
            }
        }
        
    }

    public String verificaCpfExistente(String cpf){
        
        return cpf;
    }
}
