package br.com.fiap.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String SCHEMA = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static DataSource conexao = null;
    public ConnectionFactory() {

    }
    // SINGLETON (apenas uma única instância de conexão)
    public static Connection connect() {
       if (conexao == null) {
           final ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
           comboPooledDataSource.setJdbcUrl(SCHEMA);
           comboPooledDataSource.setUser("RM99748");
           comboPooledDataSource.setPassword("130303");
           comboPooledDataSource.setMaxPoolSize(10);
           conexao = comboPooledDataSource;
       }
       try {
        return conexao.getConnection();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
    }
}