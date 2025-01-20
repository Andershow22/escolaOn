package DAO;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.tarefa;

public class tarefaDAO {

    public List listarTarefas(int id) {
        try {
            Conexao c = new Conexao();
            List<tarefa> lista = new ArrayList();
            String sql = " select * from tarefa as ta join professor as p where ta.professor_id = " + id
            + " AND p.id = " +id;
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

    public void publicarTarefa(tarefa t, int id) {
        try {
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
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
        }
    }
    public tarefa buscar(int id){
        try{
            tarefa t = new tarefa();
            String sql = "select * from tarefa where id =" + id;
            Conexao c = new Conexao();
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                t.setData_entrega(rs.getString("data_entrega"));
                t.setDescricao(rs.getString("descricao"));
                t.setTurma(rs.getInt("turma_id"));
            }
            return t;
        }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, "Erro ao buscar Tarefa\n" + ex.getMessage());
             return null;
        }
    }
    public void excluirTarefa(int id) {
        try {
            Conexao c = new Conexao();
            String sql = "DELETE FROM tarefa WHERE id = " + id;
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.execute();
            ps.close();
            c.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir tarefa\n" + ex.getMessage());
        }
    }
    
    public void alterarTarefa(tarefa t){
        try {
            Conexao c = new Conexao();
            String sql = "UPDATE tarefa SET nome = '"+t.getNome()+"'"
                    + ", data_entrega = '"+t.getData_entrega()+"'"
                    + ", descricao = '"+t.getDescricao()+"'"
                    + ", turma_id = "+t.getTurma()+""
                    + " WHERE id = "+ t.getId();
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            c.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar Tarefa\n" + ex.getMessage());
        }
    }
    
}
