/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Starter;

import Arq.IData;
import Arq.IDomainData;
import Arq.IUI;
import Data.JSONDatabase;
import Domain.DomainData;
import UI.GUI.UI;

/**
 *
 * @author madsd
 */
public class Starter {

    public static void main(String[] args) {
        IData data = new JSONDatabase();
        IDomainData domain = new DomainData();
        domain.injectData(data);
        IUI ui = new UI();
        System.out.println("DOMAIN");
        System.out.println(domain);
        ui.injectDomainData(domain);

        //domain.saveGame();
        
        ui.startApplication(args);
    }
}
