/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

/**
 * Interface des algos de colorations
 * @author Thomas
 */
public interface IColoration {
    
    public int getCoulMax();
    
    public int[] getCouleur();
    
    public void algo() throws Exception;
    
    public boolean result();
    
    public int getEquitable();
}
