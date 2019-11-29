/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import model.Utils;

/**
 *
 * @author Thairam Michel
 */
public class testando {

    public static void main(String[] args) {

        Date data = new Date();
        Calendar dataCal = new GregorianCalendar();
        dataCal.setTime(data);

        String dataDevolucao = "27/11/2019";
        Calendar devolucao = Utils.stringToCalend(dataDevolucao);
        
        System.out.println(devolucao.before(dataCal));

    }

}
