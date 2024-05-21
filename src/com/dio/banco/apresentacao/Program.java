package com.dio.banco.apresentacao;

import com.dio.banco.entidades.*;
import com.dio.banco.exceptions.BancoException;

import java.util.Scanner;

public class Program {

    private static final Scanner SC = new Scanner(System.in);
    private static final Banco BANCO = new Banco();

    public static void main(String[] args) {
        try {
            programa();
        } catch (BancoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void programa() throws BancoException {
        boolean mostrarMenu = true;
        System.out.println("===== Bem vindo ao " + BANCO.getNome() + " ===== ");
        do {
            exibirMenu();
            int opcao = SC.nextInt();
            System.out.println();
            if (opcao != 7) {
                executarMenu(opcao);
            } else {
                mostrarMenu = false;
            }
        } while (mostrarMenu);
    }

    private static void executarMenu(int opcao) {
        switch (opcao) {
            case 1 -> verificarCliente();
            case 2 -> verificarSaldo();
            case 3 -> depositarValor();
            case 4 -> sacarValor();
            case 5 -> transferirValor();
            case 6 -> extrato();
            default -> System.out.println("Opção inválida, tente novamente.");
        }
    }

    private static void exibirMenu() {
        System.out.println("\nSelecione a opção desejada:");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Exibir saldo conta");
        System.out.println("3 - Depositar valor");
        System.out.println("4 - Sacar valor");
        System.out.println("5 - Transferir valor");
        System.out.println("6 - Exibir extrato conta");
        System.out.println("7 - Sair");
        System.out.print("-> ");
    }

    private static void depositarValor() {
        System.out.print("Informe sua conta: ");
        int conta = SC.nextInt();
        System.out.print("Informe o valor a ser depositado: ");
        double valor = SC.nextDouble();
        BANCO.depositarValor(conta, valor);
    }

    private static void extrato() {
        System.out.print("Informe conta: ");
        int conta = SC.nextInt();
        BANCO.imprimirExtrato(conta);
    }

    private static void criarContaCorrente() {
        SC.nextLine();
        Cliente cliente = criarNovoCliente();
        BANCO.adicionarConta(cliente);
    }

    private static void criarContaPoupanca() {
        SC.nextLine();
        Cliente cliente = criarNovoCliente();
        BANCO.adicionarContaPoupanca(cliente);
    }

    private static Cliente criarNovoCliente() {
        System.out.println();
        System.out.println("### Adicionar conta ###");
        System.out.print("Informe seu nome: ");
        String nome = SC.nextLine();
        return new Cliente(nome);
    }

    private static void sacarValor() {
        System.out.print("Informe conta: ");
        int conta = SC.nextInt();
        System.out.print("Informe o valor: ");
        double valor = SC.nextDouble();
        BANCO.sacarValor(conta, valor);
    }

    private static void verificarCliente() {
        System.out.println("Deseja criar conta corrente ou poupança?");
        System.out.print("Digite 1 para corrente e 2 para poupança: ");
        int verificacao = SC.nextInt();
        if(verificacao == 1) {
            criarContaCorrente();
        } else if (verificacao == 2){
            criarContaPoupanca();
        } else {
            System.out.println("Opção invalida");
        }
    }

    private static void verificarSaldo() {
        System.out.print("Informe conta: ");
        int conta = SC.nextInt();
        double valor = BANCO.getSaldo(conta);
        System.out.printf("Saldo disponivel: R$%.2f%n", valor);
    }

    private static void transferirValor() {
        System.out.print("Informe sua conta de origem: ");
        int contaOrigem = SC.nextInt();
        System.out.print("Informe sua conta de destino: ");
        int contaDestino = SC.nextInt();
        System.out.print("Informe o valor a ser transferido: ");
        double valor = SC.nextDouble();
        BANCO.transferir(contaOrigem, contaDestino, valor);
    }

}
