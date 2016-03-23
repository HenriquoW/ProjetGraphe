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
public abstract class Coloration implements IColoration{
    protected boolean matrice[][];
    protected int saturation[];
    protected int couleur[];
    protected int coulMax = 1;
    protected Graphe g;
    
    public Coloration(int s, int p){
        this.g = new Graphe(s);
        this.g.genererGraphe(p);
        this.matrice= this.g.getMatrice();
        this.couleur = new int[s];
        this.saturation = new int[s];
    }
    
    public abstract void algo() throws Exception;
    
    public void result(){
        System.out.println("MATRICE");
        for (int i = 0; i < matrice.length; i++) {
           for (int j = 0; j < matrice.length; j++) {
               System.out.println(i + " "+j+" "+matrice[i][j]);
           }
        }
        System.out.println("COULEUR");
        for (int i = 0; i < couleur.length; i++) {
            System.out.println(i+" "+couleur[i]);
        }
  
    }
}
