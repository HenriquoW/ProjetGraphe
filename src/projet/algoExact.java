package projet;

import java.util.LinkedList;

/**
 *
 * @author Thomas
 */
public class algoExact extends Coloration implements IColoration{
    private final couleurMin cm;
    LinkedList<Integer> couleurDispo;
    
    public algoExact(int s, int p) {
        super(s, p);
        cm = new couleurMin();
        coulMax = 0;
    }

    @Override
    public void algo() throws Exception {
        colore(0);
    }

    private void colore(int s) throws Exception {
        boolean colorie = false;
        if(s<g.getSommet()){
            couleurDispo = new LinkedList();
            for (int i = 1; i < coulMax+1; i++) {
                if(convient(s, i)){
                    couleurDispo.add(i);
                    break;
                }
            }
            int color = -1;
            if(couleurDispo.size()>0)
                color = cm.chooseColorExact(couleurDispo);
            if(couleurDispo.size()==0 || color == -1) {
                coulMax++;
                color = coulMax;
                cm.newColor(color);
            }
            couleur[s] = color;
            colore(s+1);
        }
    }
    
    private boolean convient(int s, int c) {
        boolean result = true;
        for (int i = 0; i < matrice.length; i++) {
            if(matrice[s][i]) {
                if(couleur[i] == c) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
