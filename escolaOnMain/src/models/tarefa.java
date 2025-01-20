package models;

public class tarefa {

    private int id;
    private String nome;
    private String data_entrega;
    private String descricao;
    private int turma;
    private String professor;

    public tarefa(String nome, String data_entrega, String descricao, int turma) {
        this.nome = nome;
        this.data_entrega = data_entrega;
        this.descricao = descricao;
        this.turma = turma;
    }
    
    public tarefa(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(String data_entrega) {
        this.data_entrega = data_entrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
    
    
}
