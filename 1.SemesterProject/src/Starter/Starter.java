package Starter;

import Acq.IData;
import Acq.IDomainData;
import Acq.IUI;
import Data.JSONDatabase;
import Domain.DomainData;
import UI.GUI.UI;

/**
 *The starter class / glue-code. This class establish runtime dependencies
 */
public class Starter {

    public static void main(String[] args) {
        IData data = new JSONDatabase();
        IDomainData domain = DomainData.getInstance();
        domain.injectData(data);
        IUI ui = new UI();
        ui.injectDomainData(domain);
        ui.startApplication(args);
    }
}
