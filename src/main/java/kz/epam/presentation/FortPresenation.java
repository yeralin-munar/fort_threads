package kz.epam.presentation;

import kz.epam.app.Ship;
import kz.epam.thread.Fort;
import kz.epam.thread.Ocean;
import kz.epam.thread.Shipyard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yeralin Munar
 *         date: 3/18/17
 */
public class FortPresenation {
    private volatile Ocean ocean;
    private volatile Fort fort;
    private volatile List<Shipyard> shipyards = new ArrayList<Shipyard>();

    public synchronized void getScheme(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println(toString());
    }

    public void setOcean(Ocean ocean){
        this.ocean = ocean;
    }

    public void setFort(Fort fort) {
        this.fort = fort;
    }

    public void addShipyard(Shipyard shipyard){
        shipyards.add(shipyard);
    }

    @Override
    public String toString(){
        String fortString = "";

        fortString += "Океан - " + ocean.getName() +"(" + ocean.getState() + ")" + "\n";
        fortString += "Корабли: ";
        if (!ocean.getShips().isEmpty()) {
            fortString += ocean.getShips();
        }
        else
            fortString += "[Пусто]";
        fortString += "\n\n";


        fortString += "     Форт - " + fort.getName() +"(" + fort.getState() + ")" + "\n";

        fortString += "     Корабли: ";
        if (!fort.getShips().isEmpty()) {
            fortString += fort.getShips();
        }
        else
            fortString += "[Пусто]";
        fortString += "\n";


        for (Shipyard shipyard: shipyards){
            String prefix = "           ";
            fortString += prefix + "Верфь - " + shipyard.getName() + "(" + shipyard.getType() + ")";
            Ship ship = shipyard.getShip();
            if (ship != null)
                fortString += " : " + ship + "\n";
            else
                fortString += " : " + "Свободно\n";
        }

        return fortString;
    }
}
