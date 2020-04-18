package projetoFundamentos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Arquivo {

    private static String[] linha;
    private static Scanner entrada;
    private static String arquivoContas;

    public static void construtor(String arquivo) {
        arquivoContas = arquivo;
    }

    public static void incluirConta(Conta conta) {
        entrada = abreArquivoContas(arquivoContas);
        String temporaria = "";
        if ((conta.getSaldoContaCorrente() > 0) && (conta.getSaldoContaPoupanca() > 0)) {
            try {
                while (entrada.hasNextLine()) {
                    temporaria += entrada.nextLine() + System.getProperty("line.separator");
                    if (temporaria.contains(conta.getNumeroConta())) {
                        System.out.println("Conta já existe!");
                        break;
                    }
                }
                if (!entrada.hasNextLine()) {
                    temporaria += conta.getNumeroConta() + " " + conta.getNomeCliente() + " " + conta.getSaldoContaCorrente() + " " + conta.getSaldoContaPoupanca();
                    escreverArquivo(entrada.reset(), arquivoContas, temporaria);
                    System.out.println("Conta incluída com sucesso!");
                }
            } catch (IllegalStateException e) {
                System.out.println("Erro na leitura do arquivo:" + e.getMessage());
            }
        } else {
            System.out.println("Digite saldos diferentes de 0.");
        }
    }

    public static void alterarConta(Conta conta) {
        entrada = abreArquivoContas(arquivoContas);
        String temporaria = "";
        String tempLine;
        boolean naoExisteConta = true;
        try {
            while (entrada.hasNextLine()) {
                tempLine = entrada.nextLine();
                if (tempLine.contains(conta.getNumeroConta())) {
                    temporaria += conta.getNumeroConta() + " " + conta.getNomeCliente() + " " + conta.getSaldoContaCorrente() + " " + conta.getSaldoContaPoupanca() + System.getProperty("line.separator");
                    System.out.println("Conta encontrada! Conta alterada com sucesso!");
                    naoExisteConta = false;
                } else {
                    temporaria += tempLine + System.getProperty("line.separator");
                }
            }
            if (naoExisteConta == true) {
                System.out.println("Conta não existe! Confira seus dados!");
            }
        } catch (IllegalStateException e) {
            System.out.println("Erro na leitura do arquivo:" + e.getMessage());
        }
        escreverArquivo(entrada.reset(), arquivoContas, temporaria);
    }

    public static void excluirConta(Conta conta) {
        entrada = abreArquivoContas(arquivoContas);
        String temporaria = "";
        String tempLine;
        try {
            while (entrada.hasNextLine()) {
                tempLine = entrada.nextLine();
                if (tempLine.contains(conta.getNumeroConta())) {
                    linha = tempLine.split(" ");
                    conta.setSaldoContaCorrente(Float.parseFloat(linha[2]));
                    conta.setSaldoContaPoupanca(Float.parseFloat(linha[3]));
                    if ((conta.getSaldoContaCorrente() == 0) && (conta.getSaldoContaPoupanca() == 0)) {
                        System.out.println("Conta encontrada e excluída com sucesso!");
                    } else {
                        System.out.println("Você ainda possui saldo na conta!");
                        temporaria += tempLine + System.getProperty("line.separator");
                    }
                } else {
                    temporaria += tempLine + System.getProperty("line.separator");
                }
            }
        } catch (IllegalStateException e) {
            System.out.println("Erro na leitura do arquivo:" + e.getMessage());
        }
        escreverArquivo(entrada.reset(), arquivoContas, temporaria);
    }

    public static void listaSaldoNegativo() {
        entrada = abreArquivoContas(arquivoContas);
        String tempLine;
        boolean contasPositvo = true;
        try {
            while (entrada.hasNextLine()) {
                tempLine = entrada.nextLine();
                linha = tempLine.split(" ");

                if (Float.parseFloat(linha[2]) < 0) {
                    System.out.println("Contas com saldo negativo: " + tempLine);
                    contasPositvo = false;
                }
            }
            if (contasPositvo == true) {
                System.out.println("Não existe conta com saldo negativo!");
            }
        } catch (IllegalStateException e) {
            System.out.println("Erro na leitura do arquivo:" + e.getMessage());
        }
    }

    private static Scanner abreArquivoContas(String arquivoContas) {
        Scanner arquivoEntrada = null;
        try {
            arquivoEntrada = new Scanner(new File(arquivoContas));
        } catch (FileNotFoundException e) {
            System.err.printf("Erro na abertura do arquivo: %s\n",
                    e.getMessage());
        }
        return arquivoEntrada;
    }

    private static void escreverArquivo(Scanner arquivoEntrada, String path, String conteudo) {
        BufferedWriter buffEscrita = null;
        try {
            buffEscrita = new BufferedWriter(new FileWriter(path));
            buffEscrita.append(conteudo);
            buffEscrita.close();
        } catch (IOException e) {
            System.out.println("Erro de escrita:" + e.getMessage());
        }
    }

    public static void listarSaldosAcimaDoValor(float busca) {
        entrada = abreArquivoContas(arquivoContas);
        String tempLine;
        boolean saldoMenor = true;
        try {
            while (entrada.hasNextLine()) {
                tempLine = entrada.nextLine();
                linha = tempLine.split(" ");

                if (Float.parseFloat(linha[2]) > busca) {
                    System.out.println("Contas com saldo maior que:" + busca + tempLine);
                    saldoMenor = false;
                }
            }
            if (saldoMenor == true) {
                System.out.println("Não existe conta com o valor acima do digitado.");
            }
        } catch (IllegalStateException e) {
            System.out.println("Erro na leitura do arquivo:" + e.getMessage());
        }
    }

    public static void listarTodasContas() {
        entrada = abreArquivoContas(arquivoContas);
        try {
            while (entrada.hasNextLine()) {
                System.out.println(entrada.nextLine());
            }
        } catch (IllegalStateException e) {
            System.out.println("Erro na leitura do arquivo:" + e.getMessage());
        }
    }

    public static void maioresSaldosContaCorrente() {
        entrada = abreArquivoContas(arquivoContas);
        String tempLine;
        String conta1 = "";
        String conta2 = "";
        String conta3 = "";
        String contaTemp = "";
        float saldo1 = 0;
        float saldo2 = 0;
        float saldo3 = 0;
        float temp = 0;

        try {
            while (entrada.hasNextLine()) {
                tempLine = entrada.nextLine();
                linha = tempLine.split(" ");
                if (Float.parseFloat(linha[2]) > saldo1) {
                    contaTemp = conta1;
                    conta1 = tempLine;
                    temp = saldo1;
                    saldo1 = Float.parseFloat(linha[2]);
                    tempLine = contaTemp;
                    if (temp > saldo2) {
                        contaTemp = conta2;
                        conta2 = tempLine;
                        temp = saldo2;
                        saldo2 = Float.parseFloat(linha[2]);
                        tempLine = contaTemp;
                    } else if (temp > saldo3) {
                        contaTemp = conta3;
                        conta3 = tempLine;
                        temp = saldo3;
                        saldo3 = Float.parseFloat(linha[2]);
                        tempLine = contaTemp;
                    }
                } else if (Float.parseFloat(linha[2]) > saldo2) {
                    conta2 = tempLine;
                    temp = saldo2;
                    saldo2 = Float.parseFloat(linha[2]);
                    if (temp > saldo3) {
                        contaTemp = conta3;
                        conta3 = tempLine;
                        temp = saldo3;
                        saldo3 = Float.parseFloat(linha[2]);
                        tempLine = contaTemp;
                } else if (Float.parseFloat(linha[2]) > saldo3) {
                    conta3 = tempLine;
                    temp = saldo3;
                    saldo3 = Float.parseFloat(linha[2]);
                    }
                    }
        }
            System.out.printf("Os três maiores saldos: \n  %s \n %s \n %s", conta1, conta2, conta3);
        } catch (IllegalStateException e) {
            System.out.println("Erro na leitura do arquivo:" + e.getMessage());
        }
    }
}
//    public void lerArquivo(Scanner arquivoEntrada) {
//    try {
//        while (arquivoEntrada.hasNext()) {
//            System.out.println(arquivoEntrada.next());
//        }
//    } catch (IllegalStateException e) {
//        System.out.println("Erro na leitura do arquivo:" + e.getMessage());
//    }
//}
//    public void fecharAquivo(Scanner arquivoEntrada) {
//	    if (arquivoEntrada != null) {
//	        arquivoEntrada.close();
//	    }
//    }
//    public void sairPrograma(Scanner entrada) {
//    	fecharAquivo(entrada);
//    }
//

