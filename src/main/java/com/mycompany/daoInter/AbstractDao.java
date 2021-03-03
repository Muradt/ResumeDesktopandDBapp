/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.daoInter;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hp
 */
public abstract class AbstractDao {
    public Connection connect() throws Exception {

        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "200214";
        Connection c = DriverManager.getConnection(url, username, password);

        return c;

    }
}
