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
    private LinkedList<nbCoul> listColor;
    
    //Objet servant à stocké le nombre de sommet colorié par chaque couleur
    private class nbCoul implements Cloneable{
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
    };
    
    private LinkedList<Integer> couleurVoisine;
    
    public DsaturModifie(int s, int p){
        super(s,p);
        coulMax = 0;
        listColor = new LinkedList();
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
        int newColor = chooseColor();//On choisi la nouvelle couleur
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
    
    private int chooseColor() throws Exception{
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
            super.coulMax++;
            return super.coulMax;
        }
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
            throw new Exception("couleur incorrecte fonction : searchBetterColor classe : DsaturModifie");
    }
    
    private void addColor(int s, int c){
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
            nbCoul nc = new nbCoul(super.coulMax);//On crée l'objet correspondant
            nc.add();//Et on incrémente son nombre d'apparition
            listColor.add(nc);//Puis on l'ajoute à la liste
        }
        super.couleur[s] = c;//finalement on affecte la couleur au sommet
    }
}
