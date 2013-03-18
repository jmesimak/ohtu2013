/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

/**
 *
 * @author jjoonia
 */
public interface Kori {

    int hinta();

    void lisaa(Tuote t);

    void poista(Tuote t);
    
}
