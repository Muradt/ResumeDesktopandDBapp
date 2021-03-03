/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.daoImpl;

import com.mycompany.daoInter.AbstractDao;
import com.mycompany.daoInter.SkilldaoInter;
import com.mycompany.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class SkilldaoImpl extends AbstractDao implements SkilldaoInter {

    private Skill getSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String skillName = rs.getString("name");
        return new Skill(id, skillName);
    }

    @Override
    public List<Skill> getAllSkill() {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute("select * from skill");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Skill u = getSkill(rs);
                result.add(u);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

    @Override
    public boolean insertUserSkill(Skill s) {
        boolean b = true;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into skill(name) values(?)",Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, s.getSkill());
            b = stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                s.setId(generatedKeys.getInt(1));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            b = false;
        }
        return b;
    }

}
