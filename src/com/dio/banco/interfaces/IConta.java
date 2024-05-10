package com.dio.banco.interfaces;

import com.dio.banco.entidades.Conta;

public interface IConta {

    void sacar(double valor);
    void depositar(double valor);
    void transferir(double valor, IConta contaDestino);
    void imprimirExtrato();

}
