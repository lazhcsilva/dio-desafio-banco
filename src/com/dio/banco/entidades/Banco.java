package com.dio.banco.entidades;

import com.dio.banco.interfaces.IConta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Banco {

    private final String nome = "Banco Jedi";
    private final List<IConta> contas;
    private final int agencia = 1;
    private int proximoNumeroConta;

    public Banco() {
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Cliente cliente) {
        proximoNumeroConta++;
        ContaCorrente contaCorrente = new ContaCorrente(agencia, proximoNumeroConta, cliente, 100);
        contas.add(contaCorrente);
        System.out.println("Conta corrente criada com sucesso!");
        contaCorrente.imprimirExtrato();
    }

    public void adicionarContaPoupanca(Cliente cliente) {
        proximoNumeroConta++;
        ContaPoupanca contaPoupanca = new ContaPoupanca(agencia, proximoNumeroConta, cliente);
        contas.add(contaPoupanca);
        System.out.println("Conta pupança criada com sucesso!");
        contaPoupanca.imprimirExtrato();
    }

    public void imprimirExtrato(int conta) {
        Optional<IConta> numeroConta = pesquisarConta(conta);
        numeroConta.ifPresent(IConta::imprimirExtrato);
    }

    public void transferir(int numeroContaOrigem, int numeroContaDestino, double valor) {
        Optional<IConta> contaOrigem = pesquisarConta(numeroContaOrigem);
        Optional<IConta> contaDestino = pesquisarConta(numeroContaDestino);
        if (contaOrigem.isPresent() && contaDestino.isPresent()) {
            contaOrigem.get().transferir(valor, contaDestino.get());
        }
    }

    public Optional<IConta> pesquisarConta(int numeroConta) {
        Optional<IConta> conta = contas.stream()
                .filter(n -> n.getNumero() == numeroConta)
                .findFirst();

        if (conta.isEmpty()) {
            System.out.printf("Conta %d não encontrada%n", numeroConta);
        }
        return conta;
    }

    public boolean validarConta(int numeroConta) {
        Optional<IConta> conta = pesquisarConta(numeroConta);
        return conta.isPresent();
    }

    public String getNome() {
        return nome;
    }

    public int getAgencia() {
        return agencia;
    }

    public List<IConta> listarContas() {
        return contas;
    }

    public double getSaldo(int conta) {
        Optional<IConta> contaPesquisada = pesquisarConta(conta);
        return contaPesquisada.map(IConta::getSaldo).orElse(0.0);
    }

    public void depositarValor(int conta, double valor) {
        Optional<IConta> contaInformada = pesquisarConta(conta);
        if (contaInformada.isPresent()) {
            contaInformada.get().depositar(valor);
            System.out.printf("Valor depositado: R$%.2f%n", valor);
        } else {
            System.out.println("Valor não depositado.");
        }
    }

    public void sacarValor(int conta, double valor) {
        Optional<IConta> contaInformada = pesquisarConta(conta);
        if (contaInformada.isPresent()) {
            contaInformada.get().sacar(valor);
        } else {
            System.out.println("Valor não pode ser sacado.");
        }
    }

    @Override
    public String toString() {
        return "Banco{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
