
package models;
import java.util.Scanner;


public class Professor extends Pessoa{
    private String disciplina;
    private int cargaHoraria;
    private double salario;
    private String comunicado;

    public Professor(int id, String disciplina, int cargaHoraria, double salario, String nome, String dataNascimento, String CPF, String telefone, String login, String senha) {
        super( id, nome, dataNascimento, CPF, telefone, login, senha);
        this.disciplina = disciplina;
        this.cargaHoraria = cargaHoraria;
        this.salario = salario;
    }
    
    public Professor(String disciplina, int cargaHoraria, float salario, String nome, String dataNascimento, String CPF, String telefone, String login, String senha) {
        super(nome, dataNascimento, CPF, telefone, login, senha);
        this.disciplina = disciplina;
        this.cargaHoraria = cargaHoraria;
        this.salario = salario;
    }
    
    public Professor(){
    }
    
    
    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getComunicado() {
        return comunicado;
    }

    public void setComunicado(String comunicado) {
        this.comunicado = comunicado;
    }
    
    
}
