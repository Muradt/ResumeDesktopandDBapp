/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.daoInter;

import com.mycompany.entity.User;

import java.util.List;

/**
 *
 * @author hp
 */
public interface UserdaoInter {

    public List<User> getAllUser();

    public boolean updateUser(User u);

    public boolean deleteUser(User u);

    public boolean addUser(User u);

    public User getbyid(int userId);
  
}
