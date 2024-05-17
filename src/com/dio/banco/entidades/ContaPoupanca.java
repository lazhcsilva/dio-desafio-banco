package com.dio.banco.entidades;

public class ContaPoupanca extends Conta{
    public ContaPoupanca(int agencia, int conta, Cliente cliente) {
        super(agencia, conta, cliente);
    }

    @Override
    protected boolean verificarSaldo(double valor) {
        return valor <= saldo;
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Poupança ===");
        super.imprimirInfosComuns();
    }

    @Override
    public String toString() {
        return "Conta Poupança:" +
                " agencia = " + agencia +
                ", numero = " + numero +
                ", cliente = " + cliente +
                String.format(", Saldo = R$%.2f", saldo);
    }
}
