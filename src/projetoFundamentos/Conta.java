package projetoFundamentos;

public class Conta {

    private String nomeCliente;
    private String numeroConta;
    private float saldoContaCorrente;
    private float saldoContaPoupanca;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public float getSaldoContaCorrente() {
        return saldoContaCorrente;
    }

    public void setSaldoContaCorrente(float saldoContaCorrente) {
        this.saldoContaCorrente = saldoContaCorrente;
    }

    public float getSaldoContaPoupanca() {
        return saldoContaPoupanca;
    }

    public void setSaldoContaPoupanca(float saldoContaPoupanca) {
        this.saldoContaPoupanca = saldoContaPoupanca;
    }
}
