/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Victor Gram
 */
public interface Database {
    List<HighscoreWrapper> getHighscore(); 
    void saveProgress(); 
    
    
    
    
}