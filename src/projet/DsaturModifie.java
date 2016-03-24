/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Thomas
 */
public class DsaturModifie extends Dsatur{
    
    private final couleurMin cm;
    private LinkedList<Integer> couleurVoisine;
    
    public DsaturModifie(int s, int p){
        super(s,p);
        coulMax = 0;
        cm = new couleurMin();
    }
    
    @Override
    public void algo() throws Exception{ /*On reprend le même algorithme que DSATUR en 
                                        changeant la sélection de la couleur (setColor) */
        while(!super.isEnd()){ //Si il reste des sommets à colorier
            super.initSat();//On initialise le tableau de saturation
            int s = super.getSatMax();//On récupère le sommet avec la saturation maximum
            setColor(s);//On lui affecte une couleur
            
        }
    }
    
    @Override
    protected void setColor(int s) throws Exception{
        boolean color = false;
        couleurVoisine = new LinkedList();
        for (int i = 1; i < coulMax+1 ; i++) {/*On parcours toute les couleurs existante 
                                                        tant qu'une couleur n'a pas été trouvée*/

            for (int j = 0; j < matrice.length ; j++) { /*On cherche les sommets partant de s tant 
                                                                que la couleur recherchée n'a pas été trouvée*/           

                if(matrice[s][j])
                    if(couleur[j]>0){//Si le sommet est colorié 
                        addColorInList(couleur[j]); //On tente de l'ajouté à la liste
                    }                
            }    
        }
        int newColor = cm.chooseColor(couleurVoisine);//On choisi la nouvelle couleur
        if (newColor == -1) {//Si il n'y a pas de couleur de disponible
            coulMax++;//On en crée une nouvelle
            newColor=coulMax;
            cm.newColor(coulMax); // et on l'ajoute à la liste
        }
        addColor(s, newColor);//Et on l'affecte
    }
    
    private void addColorInList(int c){
        
        if(!isColorInList(c))//Si la couleur n'est pas dans la liste
            couleurVoisine.add(c);//On l'ajoute
    }
    
    private boolean isColorInList(int c){
        Iterator<Integer> i = couleurVoisine.iterator();
        boolean isIn = false;
        while(i.hasNext()){//On parcour la liste des couleurs
            if(i.next()==c) {//Si la couleur est déjà dans la liste
                isIn = true;
                break;//On arrête la boucle
            }
        }
        return isIn;
        
    }
    
    private void addColor(int s, int c){
        cm.addColor(c, coulMax); //On incrémente le compteur de la couleur sélectionée
        super.couleur[s] = c;//finalement on affecte la couleur au sommet
    }
}
