
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
* @author Anderson
* Classe resposável por lidar com a conexão com o BD, acessando o drive, conectando e desconectando com o BD.
*/

public class Conexao {
    private String url = "jdbc:mysql://localhost:3306/escolaOn";
    private String user = "root";
    private String senha = "urso9090";
    private Connection con;
   
    public void conectar(){
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url,user, senha);
                //JOptionPane.showMessageDialog(null, "conexão estabelecida");
                
        }catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com Banco de Dados: " + ex.getMessage());
        }
    }
    public Connection getConexao(){
        if(con == null){
            conectar();
            return con;
        }
        return con;
    }
    
    public void desconectar() throws SQLException{
        if(con != null && !con.isClosed()){
            con.close();
        }else{
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao desconectar");
        }
    }
   
}
