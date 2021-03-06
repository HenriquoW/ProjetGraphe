package projet;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Thomas²
 */
public class Dsatur extends Coloration implements IColoration{
    
    public Dsatur(int s, int p){
        super(s,p);
    }
    
    @Override
    public void algo() throws Exception{
        while(!isEnd()){ //Si il reste des sommets à colorier
            initSat();//On initialise le tableau de saturation
            int s = getSatMax();//On récupère le sommet avec la saturation maximum
            setColor(s);//On lui affecte une couleur
        }
    }
    
    protected boolean asColor(int i) {
        return (this.couleur[i]>0);//Si la couleur du sommet i et > 0 alors il est colorié
    }
    
    protected void initSat(){
        this.saturation = new int[g.getSommet()]; //On réinitialise le tableau de saturation
        for (int i = 0; i < matrice.length; i++) { 
            if(!this.asColor(i)){//Si le sommet i et colorié -> calcul inutile on laisse la saturation à 0
                for (int j = 0; j < matrice.length; j++) {
                    if(matrice[i][j] && !this.asColor(j)) {/*Si il y a une arrete entre 
                                                            i et j et que j n'est pas colorié*/
                       
                        this.saturation[i]++;//On augmente la saturation de i.
                    }
                }
            }
        }
    }
    
    protected int getSatMax() throws Exception{
        int satMax = -1;
        // on récupère la saturation maximum
        for (int i = 0; i < saturation.length; i++) {
            if(saturation[i]>satMax && couleur[i]==0) {
                satMax = saturation[i];
            }
        }
        ArrayList<Integer> l = getSommets(satMax); // puis on liste tout les sommets qui ont cette saturation
        if(l.size() ==1)//Si il n'y en a qu'un on le retourne
            return l.get(0);
        else if(l.size()>1){//Sinon on récupère le sommet avec le degré maximum
            return getDegreMax(l);
        }
        else
            throw new Exception("nombre sommet incorrecte fonction : getSatMax classe : DSATUR");
    }
    
    protected int getDegreMax(ArrayList<Integer> l) throws Exception{
        int[] degre = new int[l.size()];//Initialisation du tableau de stockage du degré des sommets
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < matrice.length; j++) {
                if(matrice[l.get(i)][j])//Pour toute arrete partant d'un sommet saturé au maximum
                    degre[i]++;//On ajoute un degré
            }            
        }
        int max = -1;
        int rang = -1;
        for (int i = 0; i < degre.length; i++) { //On récupère le sommet avec le degré maximum
            if(degre[i]> max) {
                max = degre[i];
                rang = i;
            }            
        }
        if(rang>-1)//Si un sommet a bien été trouvé, on le retourne
            return l.get(rang);
        else
            throw new Exception("rang incorrecte fonction : getDegreMax classe : DSATUR");
    }
    
    protected ArrayList<Integer> getSommets(int satMax){
        ArrayList<Integer> l = new ArrayList();
        for (int i = 0; i < saturation.length; i++) {
            if(saturation[i] == satMax && couleur[i]==0){/*Si le sommet i a la saturation maximal 
                                                            et qu'il n'a pas de couleur*/
                l.add(i);//On l'ajoute à la liste
            }
        }
        return l;
    }
    
    protected void setColor(int s) throws Exception{ /*throws exception inutile ici mais 
                                                    utile pour la surcharge de la méthode 
                                                    dans DsaturModifie */
        boolean color = false;
        boolean exist;
        for (int i = 1; i < coulMax+1 ; i++) {/*On parcours toute les couleurs existante 
                                                        tant qu'une couleur n'a pas été trouvée*/
            exist = false;
            for (int j = 0; j < matrice.length ; j++) { /*On cherche les sommets partant de s tant 
                                                                que la couleur recherchée n'a pas été trouvée*/           

                if(matrice[s][j])
                    if(couleur[j]== i){
                        exist = true;
                        break;
                    }                
            }    
            if(!exist) {//Si la couleur n'a pas été trouvée  on l'affecte au sommet s
                couleur[s] = i;
                color = true;
                break;
            }
        }
        if(!color) {
            coulMax++;
            couleur[s] = coulMax;
        }
    }
    
    protected boolean isEnd(){
        boolean end = true;
        for (int i = 0; i < couleur.length; i++) {
            if(couleur[i]==0){//Si un sommet n'est pas colorié alors l'algorithme n'est pas fini
                end = false;
            }
        }
        return end;
    }
}
