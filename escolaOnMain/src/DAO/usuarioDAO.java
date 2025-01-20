package DAO;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Aluno;
import models.Pessoa;
import models.Professor;

public class usuarioDAO {

    public Pessoa validaUsuario(String login, int senha) {
        int id;
        Pessoa pessoa = new Pessoa();
        try {
            Conexao c = new Conexao();
            String sql = " select * from usuario where login = '" + login + "' and senha = '" + senha + "'";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String permissao = rs.getString("permissao");
                if (permissao.equals("aluno")) {
                    id = rs.getInt("id_aluno");
                    pessoa = new Aluno();
                    pessoa.setPermissao(permissao);
                    pessoa.setId(id);
                } else if (permissao.equals("professor")) {
                    id = rs.getInt("id_professor");
                    pessoa = new Professor();
                    pessoa.setPermissao(permissao);
                    pessoa.setId(id);
                } else if (permissao.equals("administrador")) {
                    pessoa.setPermissao(permissao);
                }
            }
            c.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pessoa;
    }
}
