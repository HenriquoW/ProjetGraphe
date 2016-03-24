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
        int sommet = 10, proba = 50;
        IColoration d = new Dsatur(sommet, proba);
        d.algo();
        if(!d.verification())
            throw new Exception ("Résultat incorrecte");
        afficheEqui("DSATUR",d.getEquitable());
        d = new DsaturModifie(sommet, proba);
        d.algo();
        if(!d.verification())
            throw new Exception ("Résultat incorrecte");
        afficheEqui("DSATUR modifié",d.getEquitable());
        d = new algoExact(sommet, proba);
        d.algo();
        if(!d.verification())
            throw new Exception ("Résultat incorrecte");
        afficheEqui("Algo exact",d.getEquitable());
    }
    
    public static void afficheEqui(String algo, int e) {
        System.out.print(algo+" : ");
        if(e==0)
            System.out.println("equitable");
        else
            System.out.println(e+"-equitable");
    }
}
