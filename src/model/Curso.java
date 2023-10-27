package model;

import java.util.HashSet;

public class Curso {
    private String id;
    private String nome;
    private int carga_horaria;
    private HashSet<HashSet> aluno = new HashSet<HashSet>();

    public Curso(String id, String nome, int carga_horaria) {
        this.id = id;
        this.nome = nome;
        this.carga_horaria = carga_horaria;
    }

    public  String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public void adicionaAluno(HashSet alunos){
        aluno.add(alunos);
    }
    public void removeAluno(HashSet alunos){
        aluno.remove(alunos);
    }
}
