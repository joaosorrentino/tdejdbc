package control;

import dao.Conexao;
import model.Aluno;
import model.Curso;

import java.sql.SQLException;
import java.util.Scanner;


public class Programa {
    public static void main(String[] args) throws SQLException {
        Conexao conexao = new Conexao();
        Scanner scanner = new Scanner(System.in);
        int menu = 0;

        System.out.println("Digite 1 para Cadastrar aluno");
        System.out.println("Digite 2 para Cadastrar curso");
        System.out.println("Digite 3 para Consultar curso de um dado aluno");
        System.out.println("Digite 4 para Listar alunos de um dado curso");
        menu = scanner.nextInt();
        switch (menu){
            case(1):
                System.out.println("Digite a matrícula do aluno:");
                String matricula = scanner.nextLine();

                System.out.println("Digite o nome do aluno:");
                String nome = scanner.nextLine();

                System.out.println("Insira o codigo do curso do Aluno: ");
                String cod_curso = scanner.nextLine();

                Curso c = conexao.listarCurso(Conexao.connect(), cod_curso);
                System.out.println(c.getId());

                conexao.inserirAluno(Conexao.connect(),matricula,nome,c);
                break;
            case(2):
                System.out.println("Insira o nome do Curso: ");
                String nomeCurso = scanner.nextLine();
                System.out.println("Insira o código do Curso: ");
                String codCurso= scanner.nextLine();
                System.out.println("Insira a carga horária do Curso: ");
                int chCurso = scanner.nextInt();
                conexao.inserirCurso(Conexao.connect(), codCurso, nomeCurso, chCurso);
                break;

            case(3):
                System.out.println("Digite a matrícula do Aluno: ");
                String mat = scanner.nextLine();
                Aluno a = conexao.listarAlunos(Conexao.connect(), mat);
                conexao.consulta(Conexao.connect(), a);
                break;

            case(4):
                System.out.println("Digite o código do Curso: ");
                String cod_Curso = scanner.nextLine();
                Curso curso = conexao.listarCurso(Conexao.connect(), cod_Curso);
                conexao.exibeAlunosCurso(Conexao.connect(), curso);
                break;

        }

    }
}
