/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thairam Michel
 */
public class Utils {

    static private Locale localeBR = new Locale("pt", "BR");

    public static String quotedStr(String str) {
        return "\"" + str + "\"";
    }

    //criei esse método para resolver um problema dos campos formatados para cpf e outros.
    public static String toString(String str) {
        return str.replaceAll("[^0-9]", "");
    }

    public static double toDouble(String val) {
        if (val == null) {
            return 0;
        } else {
            val = val.replaceAll("[^0-9,]", "").replace(",", ".").replaceAll(",", "");
            return val.length() == 0 ? 0 : Double.parseDouble(val);
        }
    }

    public static void main(String[] args) {
        System.out.println(toDouble("R$ 3,59"));
    }

    public static int toInt(String val) {
        if (val == null) {
            return 0;
        } else {
            val = val.replaceAll("[^0-9]", "");
            return val.length() == 0 ? 0 : Integer.parseInt(val);
        }
    }

    public static String calendToString(Calendar cal, String format) {
        if (cal == null) {
            cal = Calendar.getInstance();
        }
        return (new SimpleDateFormat(format)).format(cal.getTime());
    }

    public static String calendToString(Calendar cal) {
        if (cal == null) {
            cal = Calendar.getInstance();
        }
        return (new SimpleDateFormat("dd/MM/yyyy")).format(cal.getTime());
    }

    public static Calendar stringToCalend(String s) {
        Calendar c = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            c.setTime(sdf.parse(s));
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public static String formatDouble(double value) {
        return String.format("R$ %,.2f", value); //  Formata moeda
    }
}
