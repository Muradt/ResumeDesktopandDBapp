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
import com.mycompany.daoInter.UserdaoInter;

/**
 *
 * @author hp
 */
public class UserdaoImpl extends AbstractDao implements UserdaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String profileDescription = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthDate = rs.getDate("birth_date");
        Country nationality = new Country(nationalityId, nationalityStr, null);
        Country birthplace = new Country(birthplaceId, null, birthplaceStr);
        return new User(id, name, surname, email, phone, profileDescription, address, birthDate, nationality, birthplace);

    }

    @Override
    public List<User> getAllUser() {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute("select "
                    + "*, "
                    + "n.nationality as nationality, "
                    + "c.name as birthplace "
                    + "from user u "
                    + "left JOIN  country n on u.nationality_id=n.id "
                    + "left JOIN  country c on u.birthplace_id=c.id ");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {

        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=? , surname=? ,email=?,phone=?,profile_description=?, address=?,birth_date=? ,birthplace_id=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getProfileDescription());
            stmt.setString(6, u.getAddress());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getBirthplace().getId() );
            stmt.setInt(9, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(User u) {
        try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            return stmt.execute("delete from user  where id=1");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getbyid(int userId) {
        User result = null;
        try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute("select "
                    + "*, "
                    + "n.nationality as nationality, "
                    + "c.name as birthplace "
                    + "from user u "
                    + "left JOIN  country n on u.nationality_id=n.id "
                    + "left JOIN  country c on u.birthplace_id=c.id where u.id=" + userId);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {

                result = getUser(rs);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,phone,email,profile_description) values(?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDescription());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
