/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.daoImpl;

import com.mycompany.daoInter.AbstractDao;
import com.mycompany.daoInter.CountrydaoInter;
import com.mycompany.entity.Country;
import com.mycompany.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class CountrydaoImpl extends AbstractDao implements CountrydaoInter {

    private Country getCountry(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String countryName = rs.getString("name");
        String nationality = rs.getString("nationality");
        return new Country(id, countryName, nationality);
    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> result = new ArrayList<>();
        try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute("select * from country ");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Country u = getCountry(rs);
                result.add(u);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

}
