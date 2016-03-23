/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

/**
 *  classe mère des algos de coloration
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
    
    @Override
    public int getCoulMax(){
        return coulMax;
    }
    
    @Override
    public int[] getCouleur() {
        return couleur;
    }
    
    @Override
    public abstract void algo() throws Exception;
    
    @Override
    public boolean result(){//Fonction de vérification
        boolean fonctionne = true;
        loop1:
        for (int i = 0; i < matrice.length; i++) {
           for (int j = 0; j < matrice.length; j++) {
               if(matrice[i][j])//Si il y a une arrète
                    if(couleur[i]==couleur[j]) {//Et que les deux points sont de la même couleur
                       fonctionne = false; //alors l'algo ne fonctionne pas
                       break loop1; //et on quitte loop1(la première boucle)
                    }
           }
        }
        return fonctionne;
    }
    
    @Override
    public int getEquitable(){
        int[] nombre = new int [coulMax];
        for (int i = 0; i < couleur.length; i++) {//On compte le nombre d'apparition de chaque couleur
            nombre[couleur[i]-1]++; //Les couleurs commence à 1 il faut donc soustraire 1 pour coller à l'indice du tableau  
        }
        int min = 99999;
        int max = -1;
        for (int i = 0; i < nombre.length; i++) {//On parcour le tableau
            if(nombre[i]>max){//et on cherche la couleur qui apparrait le plus de fois
                max = nombre[i];
            }
            if(nombre[i]<min){//Et celle qui apparrait le moin de fois
                min = nombre[i];
            } 
        }
        return max - min;//Et on retourne le degré d'equitabilité
    }
}
