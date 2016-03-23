package projet;

/**
 *
 * @author Thomas
 */
public class ProjetGrapheBoucardHenriques {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        IColoration d = new DSATUR(20, 50);
        d.algo();
        d.result();
    }
    
}
