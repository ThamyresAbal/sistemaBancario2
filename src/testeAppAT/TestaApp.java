package testeAppAT;

import java.util.Scanner;

import projetoFundamentos.Arquivo;
import projetoFundamentos.Conta;

public class TestaApp {

    private static Scanner ler = null;
    private static Conta conta = null;
    private static final int FLAG = 0;
    private static int menu = -1;
    private static int menuRelatorio = -1;

    public static void main(String[] args) {

        Arquivo.construtor("C:\\Users\\Milo\\Documents\\NetBeansProjects\\ProjetoAT\\contas.txt");

        ler = new Scanner(System.in);

        while (menu != FLAG) {
            menu = showMenu();
            if ((menu >= FLAG) && (menu <= 4)) {
                switch (menu) {
                    case 1:
                        conta = obterContaCorrente();
                        Arquivo.incluirConta(conta);
                        break;
                    case 2:
                        conta = obterContaCorrente();
                        Arquivo.alterarConta(conta);
                        break;
                    case 3:
                        System.out.println("Digite o número da conta para excluir:");
                        conta.setNumeroConta(ler.next());
                        Arquivo.excluirConta(conta);
                        break;
                    case 4:
                        menuRelatorio = showRelatorio();
                        if ((menuRelatorio >= FLAG) && (menuRelatorio <= 4)) {
                            switch (menuRelatorio) {
                                case 1:
                                    Arquivo.listaSaldoNegativo();
                                    break;
                                case 2:
                                    System.out.println("Digite o valor de pesquisa:");
                                    Arquivo.listarSaldosAcimaDoValor(ler.nextFloat());
                                    break;
                                case 3:
                                    Arquivo.listarTodasContas();
                                    break;
                                case 4:
                                    Arquivo.maioresSaldosContaCorrente();
                            }
                        } else {
                            System.out.println("Opção inválida!");
                        }
                }
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private static int showMenu() {

        System.out.println("Menu: ");
        System.out.println("1- Inclusão de cliente.");
        System.out.println("2- Alteração de cliente.");
        System.out.println("3- Exclusão de cliente.");
        System.out.println("4- Relatórios gerenciais.");
        System.out.println("0- Sair");
        System.out.printf("Informe a opção desejada: ");
        return ler.nextInt();
    }

    private static int showRelatorio() {
        System.out.println("1- Lista de clientes com saldo negativo.");
        System.out.println("2- lista de clientes com saldo acima de um valor.");
        System.out.println("3- Lista de todas as contas.");
        System.out.println("4- Três maiores saldos de conta corrente");
        return ler.nextInt();
    }

    private static Conta obterContaCorrente() {

        Conta conta = new Conta();

        System.out.println("Digite o número da conta:");
        conta.setNumeroConta(ler.next());

        System.out.println("Digite o nome do cliente:");
        conta.setNomeCliente(ler.next());

        System.out.println("Digite o saldo da conta corrente:");
        conta.setSaldoContaCorrente(ler.nextFloat());

        System.out.println("Digite1 o saldo da conta poupança:");
        conta.setSaldoContaPoupanca(ler.nextFloat());

        return conta;
    }

}
