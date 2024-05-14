package br.com.fiap.teste;

import br.com.fiap.connection.ConnectionFactory;

import java.sql.Connection;

public class TestaPoolConexoes {
    public static void main(String[] args) {
        for (int i = 1; i <=1; i++) {
            Connection connection = ConnectionFactory.connect();
            System.out.println("Conexão: " + i);
        }
    }
}
