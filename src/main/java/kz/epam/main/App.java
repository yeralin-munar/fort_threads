package kz.epam.main;

import kz.epam.app.Ship;
import kz.epam.presentation.FortPresenation;
import kz.epam.thread.Fort;
import kz.epam.thread.Ocean;
import kz.epam.thread.Shipyard;

/**
 * @author Yeralin Munar
 *         date: 3/15/17
 */
public class App {
    public static void main(String[] args) {
        int totalShips = 8;
        int maximumShipsInFort = 1;
        Fort fort = new Fort("Fort 11");
        Ocean ocean = new Ocean("Ocean");
        FortPresenation fortPresenation = new FortPresenation();

        ocean.setLimit(totalShips);
        fort.setTotalShips(totalShips);
        fort.setLimit(maximumShipsInFort);
        fort.setOcean(ocean);

        Shipyard shipyard1 = new Shipyard("Верфь 1", Ship.SHIPTYPE.BOAT.toString());
        shipyard1.setFort(fort);

        Shipyard shipyard2 = new Shipyard("Верфь 2", Ship.SHIPTYPE.BOAT.toString());
        shipyard2.setFort(fort);


        Shipyard shipyard3 = new Shipyard("Верфь 3", Ship.SHIPTYPE.SHIP.toString());
        shipyard3.setFort(fort);

        Shipyard shipyard4 = new Shipyard("Верфь 4", Ship.SHIPTYPE.SHIP.toString());
        shipyard4.setFort(fort);


        Shipyard shipyard5 = new Shipyard("Верфь 5", Ship.SHIPTYPE.TANKER.toString());
        shipyard5.setFort(fort);

        Shipyard shipyard6 = new Shipyard("Верфь 6", Ship.SHIPTYPE.TANKER.toString());
        shipyard6.setFort(fort);

        shipyard1.setFortPresenation(fortPresenation);
        shipyard2.setFortPresenation(fortPresenation);
        shipyard3.setFortPresenation(fortPresenation);
        shipyard4.setFortPresenation(fortPresenation);
        shipyard5.setFortPresenation(fortPresenation);
        shipyard6.setFortPresenation(fortPresenation);

        ocean.setFortPresenation(fortPresenation);
        fort.setFortPresenation(fortPresenation);

        ocean.start();
        fort.start();

        shipyard1.start();
        shipyard2.start();
        shipyard3.start();
        shipyard4.start();
        shipyard5.start();
        shipyard6.start();


    }
}
