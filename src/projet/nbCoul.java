/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

/**
 *
 * @author Thomas
 */
public class nbCoul implements Cloneable{
    //Objet servant à stocké le nombre de sommet colorié par chaque couleur

    private int couleur;
    private int nombre;

    public nbCoul(int c) {
        couleur = c;
        nombre = 0;
    }

    public void setCouleur(int c) {
        couleur = c;
    }

    public void add(){
        nombre++;
    }

    public int getCouleur(){
        return couleur;
    }

    public int getNombre(){
        return nombre;
    }

    @Override
    public nbCoul clone() {
            nbCoul o = null;
            try {
                    // On récupère l'instance à renvoyer par l'appel de la 
                    // méthode super.clone()
                    o = (nbCoul)super.clone();
            } catch(CloneNotSupportedException cnse) {
                    // Ne devrait jamais arriver car nous implémentons 
                    // l'interface Cloneable
                    cnse.printStackTrace(System.err);
            }
            // on renvoie le clone
            return o;
    }

}
