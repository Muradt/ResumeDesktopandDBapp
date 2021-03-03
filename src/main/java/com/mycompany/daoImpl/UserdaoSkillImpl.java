/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.daoImpl;

import com.mycompany.entity.Country;
import com.mycompany.entity.Skill;
import com.mycompany.entity.User;
import com.mycompany.entity.UserSkill;
import com.mycompany.daoInter.AbstractDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.daoInter.UserdaoSkillInter;

/**
 *
 * @author hp
 */
public class UserdaoSkillImpl extends AbstractDao implements UserdaoSkillInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userSkillId = rs.getInt("userSkillId");
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        return new UserSkill(userSkillId, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "u.*,"
                    + " us.id AS userSkillId,"
                    + "	us.skill_id,"
                    + "	s.NAME AS skill_name,"
                    + "	us.power "
                    + "FROM"
                    + "	user_skill us"
                    + "	LEFT JOIN USER u ON us.user_id = u.id"
                    + "	LEFT JOIN skill s ON us.skill_id = s.id  where  us.user_id= ? "
            );

            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

    public boolean deleteUserSkill(int id) {
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("delete from user_skill  where id=?");
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertUserSkill(UserSkill u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user_skill(user_id,skill_id,power) values(?,?,?)");
            stmt.setInt(1, u.getUser().getId());
            stmt.setInt(2, u.getSkill().getId());
            stmt.setInt(3, u.getPower());

            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
       @Override
    public boolean updateUserSkill(UserSkill u) {

        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user_skill set user_id=? , skill_id=? ,power=? where id=?");
           stmt.setInt(1, u.getUser().getId());
           stmt.setInt(2, u.getSkill().getId());
           stmt.setInt(3, u.getPower());
           stmt.setInt(4, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
}
}
