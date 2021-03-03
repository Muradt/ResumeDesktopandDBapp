/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.daoInter;

import com.mycompany.entity.UserSkill;
import java.util.List;

/**
 *
 * @author hp
 */
public interface UserdaoSkillInter {

    public List<UserSkill> getAllSkillByUserId(int userId);

    public boolean deleteUserSkill(int id);

    public boolean insertUserSkill(UserSkill u);

    public boolean updateUserSkill(UserSkill u);
}
