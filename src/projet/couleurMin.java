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
public class couleurMin {
    private LinkedList<nbCoul> listColor;
    
    private LinkedList<Integer> couleurVoisine;
    
    public couleurMin(){
        listColor = new LinkedList<>();
    }
    
    /**
     * Sélection de la couleur pour DSATUR modifié
     * @param cV la liste des couleurs présente au voisinage du point
     * @return la couleur sélectionner ou -1 si il n'y a pas de couleur disponible
     * @throws Exception lancée lorsque des couleurs sont disponible mais qu'il n'y en a aucune de sélectionnée
     */
    public int chooseColor(LinkedList<Integer> cV) throws Exception{
        couleurVoisine = cV;
        LinkedList<nbCoul> l = new LinkedList();
        Iterator<nbCoul> i = listColor.iterator();
        //On crée une copie de la liste de couleur
        while(i.hasNext()){
            l.add(i.next().clone());
        }
        boolean stop = false;
        while(!stop){
            stop = true;//Par défaut c'est la dernière itération
            i = l.iterator();
            int j = 0;
            while (i.hasNext()){// On parcours la liste des couleurs
                
                if(isColorInList(i.next().getCouleur())) {/*Si la couleur courrante et présente
                                                            dans le voisinage du sommet*/
                    stop = false;//Il faudras faire une itération de plus
                    break;//On arrete de parcourir la liste
                }
                j++;/*Si la couleur actuelle n'est pas dans le voisinage du sommet 
                    alors on incrémente l'index de la liste*/
            }
            if(!stop)//Si on a trouvé une couleur présente au voisinage du sommet courrant 
                l.remove(j); // elle est supprimé
        }
        if(l.size()>0)//Si la liste n'est pas vide 
            return searchBetterColor(l);//On cherche la meilleur couleur
        else {//Sinon il faut rajouter une couleur
            return -1;
        }
    }
    
    /**
     * Sélection de la couleur pour l'algorithme exacte
     * @param cV liste des couleurs disponible
     * @return la couleur sélectionnée
     */
    public int chooseColorExact(LinkedList<Integer> cV){
        couleurVoisine = cV;
        int min = 99999;
        int couleur = -1;
        Iterator<nbCoul> i = listColor.iterator();
        while(i.hasNext()){
            nbCoul nc = i.next();
            if(nc.getNombre()<min && isColorInList(nc.getCouleur())) {
                min = nc.getNombre();
                couleur = nc.getCouleur();
            }
        }
        return couleur;
    }
    
    private int searchBetterColor(LinkedList<nbCoul> list) throws Exception{
        int min = 99999;
        int color = -1;
        Iterator<nbCoul> i = list.iterator();
        while(i.hasNext()){//On parcours la liste de couleur
            nbCoul nc = i.next();
            if(nc.getNombre()< min){//Et on cherche celle qui fait le moins d'apparition
                min = nc.getNombre();
                color = nc.getCouleur();
            }
        }
        
        if(color>-1)//Si une couleur a été trouvé on la retourne
            return color;
        else//Sinon on lance une exception
            throw new Exception("couleur incorrecte fonction : searchBetterColor classe : couleurMin");
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
    
    public void newColor(int c){
        nbCoul nc = new nbCoul(c);
        nc.add();
        listColor.add(nc);
    }
    
    public void addColor(int c, int coulMax){
        Iterator<nbCoul> i = listColor.iterator();
        boolean find = false;
        while(i.hasNext()) {//On parcours la liste des couleurs
            nbCoul nc = i.next();
            if(nc.getCouleur() == c){//Une fois celle ci trouvée
                nc.add();//On incrémente le nombre d'apparition de cette couleur
                find = true;
            }
        }
        if(!find){//Si elle n'a pas été trouvée, c'est qu'une nouvelle couleur a été créée
            nbCoul nc = new nbCoul(coulMax);//On crée l'objet correspondant
            nc.add();//Et on incrémente son nombre d'apparition
            listColor.add(nc);//Puis on l'ajoute à la liste
        }
    }
}
