
package models;


public class Aluno extends Pessoa {
    private float nota1;
    private float nota2;
    private float nota3;
    private int turma;

    public Aluno(int id, int turma, String nome, String dataNascimento, String CPF, String telefone, String login, String senha) {
        super(id, nome, dataNascimento, CPF, telefone, login, senha);
        this.turma = turma;
    }    
    public Aluno( int turma, String nome, String dataNascimento, String CPF, String telefone, String login, String senha){
        super(nome, dataNascimento, CPF, telefone, login, senha);
        this.turma = turma;
    }
    
    public Aluno(){
    };
    
    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }

    public float getNota3() {
        return nota3;
    }

    public void setNota3(float nota3) {
        this.nota3 = nota3;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }
    
    @Override
    public String toString(){
        return this.getNome();
    }
}
