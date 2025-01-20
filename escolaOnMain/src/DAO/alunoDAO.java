package DAO;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Aluno;
import models.tarefa;

public class alunoDAO {

    public void inserirAluno(Aluno aluno, int turma) {
        try {
            Conexao c = new Conexao();
            String sql = "INSERT INTO aluno (`nome`,`data_de_nascimento`, `cpf`, `telefone`, `turma_id`)"
                    + " VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getDataNascimento());
            ps.setString(3, aluno.getCPF());
            ps.setString(4, aluno.getTelefone());
            ps.setInt(5, turma);
            ps.execute();
            ps.close();
            inserirUsuario(aluno);
            c.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir aluno\n " + ex.getMessage());
        }
    }

    public void inserirUsuario(Aluno aluno) {
        try {
            Conexao c = new Conexao();;
            String sql = "INSERT INTO usuario (id_aluno, permissao, login, senha)"
                    + " VALUES (?,?,?,?)";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            int id = buscarId();
            ps.setInt(1, id);
            ps.setString(2, "aluno");
            ps.setString(3, aluno.getLogin());
            ps.setString(4, aluno.getSenha());
            ps.execute();
            ps.close();
            c.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir usuario\n" + ex.getMessage());
        }
    }

    private int buscarId() {
        try {
            int id = 0;
            Conexao c = new Conexao();
            String sql = "SELECT id FROM aluno ORDER BY id DESC LIMIT 1";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("id");
            }
            ps.close();
            c.desconectar();
            return id;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar ID\n" + ex.getMessage());
            return 0;
        }
    }

    public List<Aluno> buscarPorTurma(int turma) {
        try {
            List<Aluno> lista = new ArrayList();
            Conexao c = new Conexao();
            String sql = "SELECT nome, id FROM aluno WHERE turma_id = " + turma;
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setNome(rs.getString("nome"));
                a.setId(rs.getInt("id"));
                lista.add(a);
            }
            ps.close();
            c.desconectar();
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar aluno pela turma\n" + ex.getMessage());
            return null;
        }
    }

    public List<Integer> buscarTurmas() {
        try {
            List<Integer> lista = new ArrayList();
            Conexao c = new Conexao();
            String sql = "SELECT turma FROM turma";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                lista.add(rs.getInt("turma"));
            }
            ps.close();
            c.desconectar();
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar turmas\n" + ex.getMessage());
            return null;
        }
    }

    public Aluno buscar(int id) {
        try {
            Conexao c = new Conexao();
            String sql = "SELECT * FROM aluno AS a JOIN usuario AS u WHERE a.id = " + id + " AND u.id_aluno = a.id";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Aluno a = new Aluno();
            while (rs.next()) {
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setDataNascimento(rs.getString("data_de_nascimento"));
                a.setCPF(rs.getString("CPF"));
                a.setTelefone(rs.getString("telefone"));
                a.setTurma(rs.getInt("turma_id"));
                a.setLogin(rs.getString("login"));
                a.setSenha(rs.getString("senha"));
            }
            ps.close();
            rs.close();
            c.desconectar();
            return a;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
            return null;
        }
    }

    public List listarTarefasId(int id_turma) {
        try {
            Conexao c = new Conexao();
            List<tarefa> lista = new ArrayList();
            String sql = " select ta.id, ta.nome, ta.data_entrega, ta.descricao, ta.turma_id, p.nome from tarefa as ta "
                    + " join professor as p "
                    + " where " + id_turma + " = ta.turma_id "
                    + " and p.id = ta.professor_id;";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tarefa f = new tarefa();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setData_entrega(rs.getString("data_entrega"));
                f.setDescricao(rs.getString("descricao"));
                f.setTurma(rs.getInt("turma_id"));
                f.setProfessor(rs.getString("p.nome"));
                lista.add(f);
            }
            ps.close();
            c.desconectar();
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Listar dados" + ex.getMessage());
            return null;
        }
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> lista = new ArrayList();
        try {
            Conexao c = new Conexao();
            String sql = "SELECT * FROM aluno AS a JOIN usuario AS u WHERE a.id = u.id_aluno";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setDataNascimento(rs.getString("data_de_nascimento"));
                a.setCPF(rs.getString("CPF"));
                a.setTelefone(rs.getString("telefone"));
                a.setTurma(rs.getInt("turma_id"));
                a.setLogin(rs.getString("login"));
                a.setSenha(rs.getString("senha"));
                a.setNota1(rs.getFloat("nota1"));
                a.setNota2(rs.getFloat("nota2"));
                a.setNota3(rs.getFloat("nota3"));
                lista.add(a);
            }
            ps.close();
            c.desconectar();
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
            return null;
        }
    }

    public void excluirAluno(int id) {
        try {
            Conexao c = new Conexao();
            String sql = "DELETE FROM usuario WHERE id_aluno = " + id;
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.execute();
            String sql2 = "DELETE FROM aluno WHERE id = " + id;
            PreparedStatement ps2 = c.getConexao().prepareStatement(sql2);
            ps2.execute();
            ps.close();
            ps2.close();
            c.desconectar();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir aluno(a)\n" + ex.getMessage());
        }
    }

    public void alterarAluno(Aluno a) {
        try {
            Conexao c = new Conexao();
            String sql = "UPDATE aluno AS a JOIN usuario AS u SET a.nome = '" + a.getNome() + "', a.data_de_nascimento = '"
                    + a.getDataNascimento() + "', a.CPF = '" + a.getCPF() + "'"
                    + ", a.telefone = '" + a.getTelefone() + "', a.turma_id = " + a.getTurma()
                    + ", u.login = '" + a.getLogin() + "', u.senha = '" + a.getSenha() + "'"
                    + " WHERE a.id = " + a.getId() + " AND u.id_aluno = " + a.getId();
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            c.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir aluno(a)\n" + ex.getMessage());
        }
    }

}
