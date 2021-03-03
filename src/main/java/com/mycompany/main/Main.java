/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

import com.mycompany.daoInter.CountrydaoInter;
import com.mycompany.daoInter.SkilldaoInter;


/**
 *
 * @author hp
 */
public class Main {

    public static void main(String[] args) throws Exception {
        CountrydaoInter m = Context.instanceofCountryDao();
        System.out.println(m.getAllCountry());
    }
}
