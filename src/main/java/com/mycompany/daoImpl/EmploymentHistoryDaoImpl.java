/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.daoImpl;

import com.mycompany.daoInter.AbstractDao;
import com.mycompany.daoInter.EmploymentHistoryDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.entity.EmploymentHistory;
import com.mycompany.entity.User;
import java.sql.Date;

/**
 *
 * @author hp
 */
public class EmploymentHistoryDaoImpl extends AbstractDao implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
     String header = rs.getString("header");
     String jobDescription = rs.getString("job_description");
     Date beginDate=rs.getDate("begin_date");
     Date endDate=rs.getDate("end_date");
     int userId = rs.getInt("user_id");
     return new EmploymentHistory(null,header,beginDate,endDate,jobDescription,new User(userId));
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        String query = "select * from employment_history where user_id=?";
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                EmploymentHistory emp = getEmploymentHistory(rs);
                result.add(emp);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

}
