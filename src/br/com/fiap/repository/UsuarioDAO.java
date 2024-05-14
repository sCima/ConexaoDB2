package br.com.fiap.repository;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
        this.connection = new ConnectionFactory().connect();
    }

    public void insert(Usuario usuario) {
        String SQL = "insert into usuarios(nome,email,senha,data) values (?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, String.valueOf(usuario.getData()));
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Usuario usuario) {
        String SQL = "update usuarios set nome=?, email=?, senha=?, data=?, where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL); // Prepara para inserir valores do objeto no SQL
            statement.setString(1, usuario.getNome()); // Seta o nome
            statement.setString(2, usuario.getEmail()); // Seta o email
            statement.setString(3, usuario.getSenha()); // Seta a senha
            statement.setDate(4, usuario.getData()); // Seta a data de nascimento
            statement.execute(); // Executa
            statement.close(); // Fecha a conexão
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> selectAll() {
        try {
            String SQL = "SELECT * FROM usuario";
            List<Usuario> usuarioList = new ArrayList<Usuario>();
            PreparedStatement statement = this.connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                // Populando os dados do objeto Cliente
                usuario.setId(resultSet.getInt("idCliente"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setEmail(resultSet.getString("cpf"));
                usuario.setSenha(resultSet.getString("telefone"));
                usuario.setData(resultSet.getDate("dataNasc"));
                // Adicionar o objeto ao clienteList
                usuarioList.add(usuario);
            }
            resultSet.close();
            statement.close();
            return usuarioList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String SQL = "delete from cliente where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
