package com.dio.banco.interfaces;

public interface IConta {

    void sacar(double valor);
    void depositar(double valor);
    void transferir(double valor, IConta contaDestino);
    void imprimirExtrato();
    int getNumero();
    int getAgencia();
    double getSaldo();

}
