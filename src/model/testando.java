/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import expressoes_regulares.QuadrinhoER;

/**
 *
 * @author Thairam Michel
 */
public class testando {

    public static void main(String[] args) {
        String txt = "além de inspirar o filme, é um dos meus prediletos.";

        System.out.println(txt.matches(QuadrinhoER.ER_NOME));

    }
}
