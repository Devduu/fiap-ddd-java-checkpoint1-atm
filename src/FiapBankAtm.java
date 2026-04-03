import java.util.Scanner;

public class FiapBankAtm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nomeCompleto = "";
        while (nomeCompleto.isBlank()) {
            System.out.print("Digite seu nome completo: ");
            nomeCompleto = scanner.nextLine().trim();
            if (nomeCompleto.isBlank()) {
                System.out.println("Nome invalido! Digite pelo menos um nome.");
            }
        }
        String primeiroNome = nomeCompleto.split("\\s+")[0];
        System.out.println("Bem-vindo(a), " + primeiroNome + "!");

        String senhaForte = "";
        String regexSenhaForte = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-_+=?><]).{8,}$";

        while (!senhaForte.matches(regexSenhaForte)) {
            System.out.print("Cadastre uma senha forte: ");
            senhaForte = scanner.nextLine();
            if (!senhaForte.matches(regexSenhaForte)) {
                System.out.println("Senha fraca! Use no minimo 8 caracteres, uma maiuscula, um numero e um caracter especial.");
            }
        }
        System.out.println("Senha cadastrada com sucesso!");

        int tentativas = 0;
        boolean acessoLiberado = false;

        while (tentativas < 3) {
            System.out.print("Digite sua senha para acessar: ");
            String senhaDigitada = scanner.nextLine();

            if (senhaDigitada.equals(senhaForte)) {
                acessoLiberado = true;
                break;
            } else {
                tentativas++;
                int tentativasRestantes = 3 - tentativas;
                if (tentativasRestantes > 0) {
                    System.out.println("Senha incorreta! Tentativas restantes: " + tentativasRestantes);
                }
            }
        }

        if (!acessoLiberado) {
            System.out.println("ACESSO BLOQUEADO");
            scanner.close();
            return;
        }

        double saldo = 0.0;
        String opcaoDigitada = "";

        while (!opcaoDigitada.equals("4")) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("[ 1 ] Consultar Saldo");
            System.out.println("[ 2 ] Fazer Deposito");
            System.out.println("[ 3 ] Fazer Saque");
            System.out.println("[ 4 ] Sair");
            System.out.print("Escolha uma opcao: ");
            opcaoDigitada = scanner.nextLine().trim();

            if (opcaoDigitada.equals("1")) {
                System.out.printf("Saldo atual: R$ %.2f%n", saldo);

            } else if (opcaoDigitada.equals("2")) {
                System.out.print("Digite o valor do deposito: R$ ");
                String entradaDeposito = scanner.nextLine().trim().replace(",", ".");
                if (entradaDeposito.matches("\\d+(\\.\\d+)?")) {
                    double valorDeposito = Double.parseDouble(entradaDeposito);
                    if (valorDeposito <= 0) {
                        System.out.println("Valor invalido! O deposito deve ser maior que zero.");
                    } else {
                        saldo += valorDeposito;
                        System.out.printf("Deposito realizado! Novo saldo: R$ %.2f%n", saldo);
                    }
                } else {
                    System.out.println("Valor invalido! Digite um numero.");
                }

            } else if (opcaoDigitada.equals("3")) {
                System.out.print("Digite o valor do saque: R$ ");
                String entradaSaque = scanner.nextLine().trim().replace(",", ".");
                if (entradaSaque.matches("\\d+(\\.\\d+)?")) {
                    double valorSaque = Double.parseDouble(entradaSaque);
                    if (valorSaque <= 0) {
                        System.out.println("Valor invalido! O saque deve ser maior que zero.");
                    } else if (valorSaque > saldo) {
                        System.out.println("Saldo insuficiente!");
                    } else {
                        saldo -= valorSaque;
                        System.out.printf("Saque realizado! Novo saldo: R$ %.2f%n", saldo);
                    }
                } else {
                    System.out.println("Valor invalido! Digite um numero.");
                }

            } else if (opcaoDigitada.equals("4")) {
                System.out.println("O FIAP Bank agradece sua preferencia!");

            } else {
                System.out.println("Opcao invalida! Tente novamente.");
            }
        }

        scanner.close();
    }
}