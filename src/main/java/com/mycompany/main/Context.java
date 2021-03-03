/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

import com.mycompany.daoImpl.CountrydaoImpl;
import com.mycompany.daoImpl.EmploymentHistoryDaoImpl;
import com.mycompany.daoImpl.SkilldaoImpl;
import com.mycompany.daoInter.UserdaoInter;
import com.mycompany.daoImpl.UserdaoImpl;
import com.mycompany.daoImpl.UserdaoSkillImpl;
import com.mycompany.daoInter.CountrydaoInter;
import com.mycompany.daoInter.EmploymentHistoryDaoInter;
import com.mycompany.daoInter.SkilldaoInter;
import com.mycompany.daoInter.UserdaoSkillInter;

/**
 *
 * @author hp
 */
public class Context {

    public static UserdaoInter instanceofUserDao() {
        return new UserdaoImpl();

    }

    public static UserdaoSkillInter instanceofUserSkillDao() {
        return new UserdaoSkillImpl();

    }

    public static EmploymentHistoryDaoInter instanceofEmploymentHistoryDao() {
        return new EmploymentHistoryDaoImpl();

    }

    public static CountrydaoInter instanceofCountryDao() {
        return new CountrydaoImpl();

    }
    public static SkilldaoInter instanceofSkillDao() {
        return new SkilldaoImpl();

    }
}
