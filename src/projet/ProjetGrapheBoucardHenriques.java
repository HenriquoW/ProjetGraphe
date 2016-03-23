package projet;

/**
 *
 * @author Thomas
 */
public class ProjetGrapheBoucardHenriques {
    
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        int sommet = 10, proba = 0;
        IColoration d = new Dsatur(sommet, proba);
        d.algo();
        afficheEqui("DSATUR",d.getEquitable());
        d = new DsaturModifie(sommet, proba);
        d.algo();
        afficheEqui("DSATUR modifié",d.getEquitable());
    }
    
    public static void afficheEqui(String algo, int e) {
        System.out.print(algo+" : ");
        if(e==0)
            System.out.println("equitable");
        else
            System.out.println(e+"-equitable");
    }
}
