
package models;


public class Pessoa {
    private int id;
    private String nome;
    private String dataNascimento;
    private String CPF;
    private String telefone;
    private String login;
    private String senha; 
    private String permissao;

    public Pessoa(int id, String nome, String dataNascimento, String CPF, String telefone, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.CPF = CPF;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
    }
    
    public Pessoa(String nome, String dataNascimento, String CPF, String telefone, String login, String senha) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.CPF = CPF;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
    }
    
    
    
    public Pessoa(){
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
    
    
    
}
