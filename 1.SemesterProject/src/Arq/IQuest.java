/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arq;

import java.util.List;

/**
 *
 * @author madsd
 */
public interface IQuest {

    public String getName();

    public String getDescription();

    public int getGold();

    public int getExp();

    public List<IItem> getItems();

    public String getGiver();
}
