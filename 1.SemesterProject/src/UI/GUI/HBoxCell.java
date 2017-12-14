package UI.GUI;

import Acq.IArmor;
import Acq.IConsumeable;
import Acq.IItem;
import Acq.IWeapon;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 */
// https://stackoverflow.com/questions/15661500/javafx-listview-item-with-an-image-button
public class HBoxCell extends HBox {

    private Label name = new Label();
    private Label type = new Label();
    private Label value = new Label();
    private Label sellValue = new Label();
    
    private IItem item;
    private int id;
    
    private Tooltip tooltip = new Tooltip();

    public HBoxCell(IItem item) {
        super(10);
        
        this.item = item;
        id = item.getId();
        
        String typeString = "Unknown";
        String value = "";
        
        switch (item.getId()) {
            case 0:
                typeString = "Armor";
                value = Integer.toString(((IArmor) item).getArmorValue());
                this.tooltip.setText("This indicates the amount of armor the item will grant you");
                this.value.setTooltip(tooltip);
                break;
            case 1:
                typeString = "Consumeable";
                value = Integer.toString(((IConsumeable) item).getUseValue()) + " / " + Integer.toString(((IConsumeable) item).getHungerValue());
                break;
            case 2:
                typeString = "Key";
                break;
            case 3:
                typeString = "Normal";
                break;
            case 4:
                typeString = "Weapon";
                value = Integer.toString(((IWeapon) item).getAttackValue());
                this.tooltip.setText("This indicates the amount of attack the item will grant you");
                this.value.setTooltip(tooltip);
                break;
        }

        this.name.setText(item.getName() + "AMOUNT " + item.getCount());
        this.type.setText(typeString);
        this.value.setText(value);
        this.sellValue.setText(Integer.toString(item.getSellValue()));

        HBox.setHgrow(this.name, Priority.ALWAYS);

        this.getChildren().addAll(this.name, this.type, this.value, this.sellValue);
    }
    
    public int getItemId(){
        return id;
    }
    
    public IItem getItem(){
        return item;
    }
}
