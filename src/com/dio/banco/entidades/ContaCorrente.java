package com.dio.banco.entidades;

public class ContaCorrente extends Conta {

    private double chequeEspecial;

    public ContaCorrente(int agencia, int conta, Cliente cliente, double chequeEspecial) {
        super(agencia, conta, cliente);
        this.saldo = 0;
        this.chequeEspecial = chequeEspecial;
    }

    @Override
    public void sacar(double valor) {
        if (verificarSaldo(valor)) {
            if (valor <= saldo) {
                saldo -= valor;
            } else {
                double restante = valor - saldo;
                saldo = 0;
                chequeEspecial -= restante;
            }
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    @Override
    public void depositar(double valor) {
        double faltaRestituir = 100 - chequeEspecial;
        if (valor <= faltaRestituir) {
            chequeEspecial += valor;
        } else {
            chequeEspecial = 100;
            saldo += (valor - faltaRestituir);
        }
    }

    @Override
    public double getSaldo() {
        System.out.printf("Cheque especial: R$%.2f%n", chequeEspecial);
        return super.getSaldo();
    }

    @Override
    protected boolean verificarSaldo(double valor) {
        return valor <= saldo + chequeEspecial;
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        super.imprimirInfosComuns();
        System.out.printf("Cheque especial: R$%.2f%n", chequeEspecial);
    }

    @Override
    public String toString() {
        return "Conta corrente:" +
                " agencia = " + agencia +
                ", numero = " + numero +
                ", cliente = " + cliente +
                String.format(", Saldo = R$%.2f", saldo) +
                String.format(", Cheque especial = R$%.2f%n", chequeEspecial);
    }
}
