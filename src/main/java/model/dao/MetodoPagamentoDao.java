package model.dao;

import model.bean.MetodoBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MetodoPagamentoDao {
    public static Collection<MetodoBean> doRetrieveAll(String email) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        Collection<MetodoBean> metodi = new ArrayList<MetodoBean>();
        String query = "SELECT * FROM metodopagamento where email = ?";
        try {
            conn = ConnectionPool.conn();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            MetodoBean metodoBean = new MetodoBean();
            while (rs.next()) {
                metodoBean.setCognome(rs.getString("cognome"));
                metodoBean.setEmail(rs.getString("email"));
                metodoBean.setNome(rs.getString("nome"));
                metodoBean.setCvv(Integer.parseInt(rs.getString("cvv")));
                metodoBean.setScadenza(rs.getString("scadenza"));
                metodoBean.setTipo(rs.getString("tipo"));
                metodoBean.setNumeroCarta(rs.getString("numerocarta"));
                metodi.add(metodoBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
        return metodi;
    }

    public synchronized boolean doInsertMetodo(MetodoBean metodo) throws SQLException, ClassNotFoundException {
        boolean insert = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        String query = "INSERT INTO metodopagamento VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = ConnectionPool.conn();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, metodo.getNumeroCarta());
            stmt.setString(2, metodo.getTipo());
            stmt.setString(3, metodo.getScadenza());
            stmt.setString(4, String.valueOf(metodo.getCvv()));
            stmt.setString(5, metodo.getNome());
            stmt.setString(6, metodo.getCognome());
            stmt.setString(7, metodo.getEmail());
            insert = stmt.executeUpdate() == 1;
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
        return insert;
    }

    public synchronized boolean doDeleteByNumero(String numerocarta) throws  SQLException{
        boolean delete = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM metodopagamento WHERE numerocarta=?";
        try{
            conn = ConnectionPool.conn();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,numerocarta);
            delete = stmt.executeUpdate() == 1;
            conn.commit();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return delete;
    }

    public synchronized boolean doModifyMetodo(MetodoBean bean) throws SQLException
    {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE indirizzo SET numerocarta=?,tipo=?,scadenza=?,cvv=?,nome=?,cognome=? WHERE email=?";
        try{
            conn = ConnectionPool.conn();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,bean.getNumeroCarta());
            stmt.setString(2,bean.getTipo());
            stmt.setString(3, bean.getScadenza());
            stmt.setInt(4, bean.getCvv());
            stmt.setString(5, bean.getNome());
            stmt.setString(6, bean.getCognome());
            stmt.setString(7,bean.getEmail());

            check = stmt.executeUpdate() == 1;
            conn.commit();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return check;
    }
}
