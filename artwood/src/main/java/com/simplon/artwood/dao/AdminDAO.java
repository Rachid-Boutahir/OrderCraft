package com.simplon.artwood.dao;

import com.simplon.artwood.beans.Admin;
import com.simplon.artwood.jbdc.DB;
import org.jetbrains.annotations.NotNull;


//import java.sql.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class AdminDAO implements IAdminDAO {
    private static DB db = null;
    private static Connection connection = null;
    private static PreparedStatement prepStmt = null;
    private static Statement stmt = null;

    private static final String SELECT_ALL_ADMINS_SQL = "select * from admin";
    private static final String INSERT_ADMIN_SQL = "INSERT INTO Admin" + " (email, password, fullName) VALUES "+ " (?, ?, ?);";
    private static final String UPDATE_ADMIN_SQL = "UPDATE admin SET email = ?, password = ?, fullName = ? WHERE admin_id = ?;";



    @Override
    public void createAdmin(Admin admin) throws SQLException {
        System.out.println("===========================SELECT_ALL_ADMINS_SQL===========================");
        System.out.println(SELECT_ALL_ADMINS_SQL);

        try {
            db = DB.getInstance();
            connection = db.getConnection();
            prepStmt = connection.prepareStatement(INSERT_ADMIN_SQL);

            // Set values for the placeholders
            prepStmt.setString(1, admin.getEmail());
            prepStmt.setString(2, admin.getPassword());
            prepStmt.setString(3, admin.getFullName());

            // Executer la requette SQL
            int rowsAffected = prepStmt.executeUpdate();

            // Vérifier si l'insertion a été réussie
            if (rowsAffected > 0) {
                System.out.println("L'insertion dans la base de données a été réussie !");
            } else {
                System.out.println("L'insertion dans la base de données a échoué.");
            }

        }catch(SQLException ex) {
            printSQLException(ex);
        } finally {
            closeResources(prepStmt,connection);
        }

    }

    @Override
    public List<Admin> listAll(){
        System.out.println("==========================="+SELECT_ALL_ADMINS_SQL+"===========================");
        ResultSet rs = null;
        List<Admin> listAdmin = new ArrayList<Admin>();
        Admin AdminBean = new Admin();

        try{
            db = DB.getInstance();
            connection = db.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(SELECT_ALL_ADMINS_SQL);

            while (rs.next()) {
                AdminBean.setAdminId(rs.getInt("user_id"));
                AdminBean.setEmail(rs.getString("email"));
                AdminBean.setFullName(rs.getString("full_name"));
                AdminBean.setRole(rs.getString("role"));
                listAdmin.add(AdminBean);
            }

            if( rs != null ||  stmt != null || connection != null){
                rs.close();
                stmt.close();
                connection.close();
            }
        }catch (SQLException ex){
            printSQLException(ex);
        } finally {
            closeResources((PreparedStatement) stmt,connection, rs);
        }
        return listAdmin;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        int rowUpdated ;
        try {
            db = DB.getInstance();
            connection = db.getConnection();
            prepStmt = connection.prepareStatement(UPDATE_ADMIN_SQL);

            prepStmt.setString(1, admin.getEmail());
            prepStmt.setString(2, admin.getPassword());
            prepStmt.setString(3, admin.getFullName());

            rowUpdated = prepStmt.executeUpdate();

            // Vérifier si la mise a jour a été réussie
            if (rowUpdated > 0) {
                System.out.println("la mise a jour dans la base de données a été réussie !");
            } else {
                System.out.println("la mise a jour dans la base de données a échoué.");
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            closeResources(prepStmt,connection);
        }

        return rowUpdated;
    }

    @Override
    public Admin selectAdmin(int idAdmin) {
        return null;
    }

    @Override
    public boolean delete(Object id) {
        return false;
    }



    public Boolean checkLogin(String email,String password){

        return true;
    }
    public Boolean findByEmail(String email){
        return false;
    }

    private void printSQLException(@NotNull SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    private void closeResources(PreparedStatement prepStmt, Connection connection) {
        closeResources(prepStmt, connection, null);
    }
    private void closeResources(PreparedStatement prepStmt, Connection connection, ResultSet resultSet) {
        // Fermeture des ressources (PreparedStatement, Connection, ResultSet)
        try {
            if (prepStmt != null) {
                 prepStmt.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
