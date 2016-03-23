package projet;

/**
 *
 * @author Thomas²
 */
public class Graphe {
    private int sommet;
    private boolean matrice[][];
    
    public Graphe(int s) {
        this.sommet = s;
        this.matrice = new boolean[sommet][sommet];
    }
    
    public int getSommet(){
        return this.sommet;
    }
    
    public boolean[][] getMatrice(){
        return this.matrice;
    }
    
    public void genererGraphe(int p) {
        for (int i = 0; i < matrice.length-1; i++) {
            for (int j = i+1; j < matrice.length; j++) {
                boolean b = ((Math.random()*100)<p);
                matrice[i][j] = b;
                matrice[j][i] = b;
            }        
        } 
    }
}
