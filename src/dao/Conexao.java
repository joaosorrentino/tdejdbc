package dao;

import com.sun.org.apache.bcel.internal.generic.Select;
import model.Aluno;
import model.Curso;

import java.sql.*;
import java.util.Scanner;

public class Conexao {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/escola";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";


    public static Connection connect() throws SQLException{
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

        System.out.println("Conexão estabelecida!");

        return connection;
    }



    public void inserirAluno(Connection connection, String matricula, String nome , Curso curso) throws SQLException {

        String sql = "INSERT INTO alunos.Aluno (matricula, nome, id_curso) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, matricula);
        preparedStatement.setString(2, nome);
        preparedStatement.setString(3, curso.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }


    public Aluno listarAlunos(Connection connection, String mat) throws SQLException {
        String sql = "SELECT * FROM alunos.Aluno WHERE matricula = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, mat);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String nome = rs.getString("nome");
            String matricula = rs.getString("matricula");
            String cod_curso = rs.getString("cod_curso");
            System.out.println("Nome: " + nome + ", Matrícula: " + matricula + ", Código do Curso: " + cod_curso);

            return new Aluno(matricula, nome);
        }
        rs.close();
        pstmt.close();
        connection.close();

        return null;
    }
    public void inserirCurso(Connection connection, String codigo, String nome,int cargaHoraria) throws SQLException {
        String sql = "INSERT INTO alunos.curso (codigo, nome,carga_horaria) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, codigo);
        preparedStatement.setString(2, nome);
        preparedStatement.setInt(3, cargaHoraria);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    public Curso listarCurso(Connection connection, String cod_curso) throws SQLException {
        String sql = "SELECT * FROM alunos.curso WHERE codigo = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, cod_curso);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String nome = rs.getString("nome");
            String codigo = rs.getString("codigo");
            int cargaHoraria = rs.getInt("carga_horaria");
            System.out.println("Nome: " + nome + ", Código: " + codigo + ", Carga Horária: " + cargaHoraria);

            return new Curso(codigo, nome, cargaHoraria);
        }

        rs.close();
        pstmt.close();
        connection.close();

        return null;
    }
    public void consulta(Connection connection, Aluno aluno) throws SQLException {
        String select = "SELECT a.nome, a.matricula, c.nome FROM aluno a , curso c WHERE a.matricula ? AND a.id=c.id";
        PreparedStatement pstmt = connection.prepareStatement(select);
        pstmt.setString(1, aluno.getMatricula());
        ResultSet rs = pstmt.executeQuery(select);
        while (rs.next()) {
            String matricula = rs.getString("mat");
            String nome = rs.getString("nome");
            String nomeCurso = rs.getString("Curso");
            System.out.println("CURSO DO ALUNO: " + aluno.getNome());
            System.out.println("Matrícula: " + matricula + ", Nome: " + nome + ", Curso: " + nomeCurso);
        }
        pstmt.close();
        rs.close();
        connection.close();
    }
    public void exibeAlunosCurso(Connection connection, Curso curso) throws SQLException{
        // Recupera todos os alunos.
        String selectSql = "SELECT a.matricula, a.nome, c.nome FROM aluno a, curso c WHERE c.id = ? AND a.id = c.id";
        PreparedStatement pstmt = connection.prepareStatement(selectSql);
        pstmt.setString(1, curso.getId());
        ResultSet rs = pstmt.executeQuery(selectSql);


        System.out.println("ALUNOS PRESENTES NO CURSO: " + curso.getNome());
        while (rs.next()) {
            String codMat = rs.getString("matricula");
            String nome = rs.getString("nome");
            String nomeCurso = rs.getString("nome");
            System.out.println("Matrícula: " + codMat + ", Nome: " + nome+"Nome do Curso: "+nomeCurso);
        }
        pstmt.close();
        rs.close();
        connection.close();

    }

}

