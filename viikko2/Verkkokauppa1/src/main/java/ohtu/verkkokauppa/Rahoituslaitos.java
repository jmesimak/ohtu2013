/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

/**
 *
 * @author jjoonia
 */
public interface Rahoituslaitos {

    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
    
}
