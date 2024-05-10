package com.dio.banco.entidades;

public class ContaPoupanca extends Conta{
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Polpança ===");
        super.imprimirInfosComuns();
    }
}
