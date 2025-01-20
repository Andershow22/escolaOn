package DAO;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Aluno;
import models.Professor;
import models.tarefa;

public class professorDAO {

    public void inserirProfessor(Professor prof) {
        try {
            Conexao c = new Conexao();
            String sql = "INSERT INTO professor (`nome`,`data_de_nascimento`, `CPF`, `telefone`, `disciplina`, `carga_Horaria`, `salario`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ? )";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.setString(1, prof.getNome());
            ps.setString(2, prof.getDataNascimento());
            ps.setString(3, prof.getCPF());
            ps.setString(4, prof.getTelefone());
            ps.setString(5, prof.getDisciplina());
            ps.setInt(6, prof.getCargaHoraria());
            ps.setDouble(7, prof.getSalario());
            ps.execute();
            inserirUsuario(prof, c);
            ps.close();
            c.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados" + ex.getMessage());
        }
    }
    
    public void inserirUsuario(Professor p, Conexao c){
        try {
            String sql = "INSERT INTO usuario (id_professor, permissao, login, senha)"
                    + " VALUES (?,?,?,?)";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            int id = buscarId(c);
            ps.setInt(1, id);
            ps.setString(2, "professor");
            ps.setString(3, p.getLogin());
            ps.setString(4, p.getSenha());
            ps.execute();
            ps.close();
            c.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir usuario\n" + ex.getMessage());
        }
    }
    private int buscarId(Conexao c){
        try{
            int id = 0;
            
            String sql = "SELECT id FROM professor ORDER BY id DESC LIMIT 1"; 
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next()){
                id = rs.getInt("id");
            }
            return id;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar ID\n" + ex.getMessage());
            return 0;
        }
    }

    public Professor buscar(int id) {
        try {
            Conexao c = new Conexao();
            String sql = "SELECT * FROM professor AS p JOIN usuario AS u WHERE p.id = " + id + " AND u.id_professor = p.id";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Professor prof = new Professor();
            while (rs.next()) {
                prof.setId(rs.getInt("id"));
                prof.setNome(rs.getString("nome"));
                prof.setDataNascimento(rs.getString("data_de_nascimento"));
                prof.setCPF(rs.getString("CPF"));
                prof.setTelefone(rs.getString("telefone"));
                prof.setDisciplina(rs.getString("disciplina"));
                prof.setCargaHoraria(rs.getInt("carga_Horaria"));
                prof.setSalario(rs.getFloat("salario"));
                prof.setComunicado(rs.getString("comunicado"));
                prof.setLogin(rs.getString("login"));
                prof.setSenha(rs.getString("senha"));
            }
            return prof;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
            return null;
        }
    }

    public boolean alterarNotas(int nota1, int nota2, int nota3, String nome) {
        Conexao c = new Conexao();
        try{
            String sql = "UPDATE aluno SET `nota1` = "+nota1+", `nota2` = "+nota2+", `nota3` = "+nota3+" WHERE nome = "+ nome;
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro alterar notas\n" + ex.getMessage());
            return false;
        }    
        
    }
    
    public void publicarTarefa(tarefa t, int id){
        try{
            Conexao c = new Conexao();
            String sql = "INSERT INTO tarefa(nome, data_entrega, descricao, turma_id, professor_id) VALUES(?,?,?,?,?)";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.setString(1, t.getNome());
            ps.setString(2, t.getData_entrega());
            ps.setString(3, t.getDescricao());
            ps.setInt(4, t.getTurma());
            ps.setInt(5, id);
            ps.execute();
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro \n" + ex.getMessage());
        }
    }
    
    public void publicarComunicado(String comunicado){
        try{
            Conexao c = new Conexao();
            String sql = "UPDATE professor SET `comunicado` = '"+comunicado+"' WHERE id > 0";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
        }
    }
    
    public List<Professor> listarProfessores(){
        List<Professor> lista = new ArrayList();
        try {
            Conexao c = new Conexao();
            String sql = "SELECT * FROM professor AS a JOIN usuario AS u WHERE a.id = u.id_professor";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Professor p = new Professor();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDataNascimento(rs.getString("data_de_nascimento"));
                p.setCPF(rs.getString("CPF"));
                p.setTelefone(rs.getString("telefone"));
                p.setDisciplina(rs.getString("disciplina"));
                p.setLogin(rs.getString("login"));
                p.setSenha(rs.getString("senha"));
                p.setCargaHoraria(rs.getInt("carga_horaria"));
                p.setSalario(rs.getDouble("salario"));
                lista.add(p);
            }
            ps.close();
            c.desconectar();
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
            return null;
        }
    }
    public void excluirProfessor(int id){
        try {
            Conexao c = new Conexao();
            String sql = "DELETE FROM usuario WHERE id_aluno = "+ id;
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.execute();
            String sql2 ="DELETE FROM professor WHERE id = "+ id;
            PreparedStatement ps2 = c.getConexao().prepareStatement(sql2);
            ps2.execute();
            ps.close();
            ps2.close();
            c.desconectar();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir professor(a)\n" + ex.getMessage());
        }
    }
    
    public void alterarProfessor(Professor p){
        try {
            Conexao c = new Conexao();
            String sql = "UPDATE professor AS p JOIN usuario AS u SET p.nome = '"+p.getNome()+"', p.data_de_nascimento = '"
                    +p.getDataNascimento()+"', p.CPF = '"+p.getCPF()+"'"
                    + ", p.telefone = '"+p.getTelefone()+"', p.disciplina = '"+p.getDisciplina()+"', p.carga_horaria = "+p.getCargaHoraria()+
                    ", p.salario = " + p.getSalario()
                    + ", u.login = '"+p.getLogin()+"', u.senha = '"+p.getSenha()+"'"
                    + " WHERE p.id = "+p.getId()+" AND u.id_professor = "+ p.getId();
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            c.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir professor(a)\n" + ex.getMessage());
        }
    }
    
}
