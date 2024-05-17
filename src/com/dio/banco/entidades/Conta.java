package com.dio.banco.entidades;

import com.dio.banco.interfaces.IConta;

public abstract class Conta implements IConta {

    protected int agencia;
    protected int numero;
    protected Cliente cliente;
    protected double saldo;

    public Conta(int agencia, int conta, Cliente cliente) {
        this.agencia = agencia;
        this.numero = conta;
        this.cliente = cliente;
    }

    @Override
    public void sacar(double valor) {
        if (verificarSaldo(valor)) {
            System.out.println("Saldo insuficiente");
        } else {
            saldo -= valor;
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public int getAgencia() {
        return agencia;
    }

    protected abstract boolean verificarSaldo(double valor);

    @Override
    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.printf("Titular: %s%n", cliente.getNome());
        System.out.printf("Agencia: %d%n", agencia);
        System.out.printf("Conta: %d%n", numero);
        System.out.printf("Saldo: R$%.2f%n", saldo);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "agencia=" + agencia +
                ", conta=" + numero +
                ", saldo=" + saldo +
                ", cliente=" + cliente +
                '}';
    }
}
