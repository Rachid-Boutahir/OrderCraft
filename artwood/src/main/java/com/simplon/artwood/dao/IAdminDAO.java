package com.simplon.artwood.dao;

import com.simplon.artwood.beans.Admin;

import java.sql.SQLException;
import java.util.List;

interface IAdminDAO{
       public void createAdmin(Admin admin) throws SQLException;
       public boolean updateAdmin(Admin admin);
       public Admin selectAdmin(int idAdmin);
       public boolean delete(Object id);

   public List<Admin> listAll();
}
