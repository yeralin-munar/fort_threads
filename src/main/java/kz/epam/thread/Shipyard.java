package kz.epam.thread;

import kz.epam.app.Ship;
import kz.epam.presentation.FortPresenation;

/**
 * @author Yeralin Munar
 *         date: 3/15/17
 */
public class Shipyard extends Thread{
    private String name;
    private String type;
    private Fort fort;
    private Ship ship;
    private volatile FortPresenation fortPresenation;

    private boolean finish = false;

    public Shipyard(String name, String type){
        setName(name);
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setFort(Fort fort){
        this.fort = fort;
    }

    public Ship getShip() {
        return ship;
    }

    public void finish(){
        finish = true;
    }

    public void setFortPresenation(FortPresenation fortPresenation) {
        fortPresenation.addShipyard(this);
        this.fortPresenation = fortPresenation;
    }

    public void run(){
        //System.out.println(getName() + " работает");
        while (!finish) {
            if (fort.isFull() && fort.isAlive()) {

                if (ship == null) {
                    ship = fort.getShipByType(type);

                    if (ship != null && !ship.isLoaded() && !fort.isFinish()) {
                        try {
                            ship.setAmount(0);
                            Thread.sleep(100 + ship.getCapacity() * 2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //fortPresenation.getScheme();
                        ship.setAmount(ship.getCapacity());
                        ship.setLoaded(true);
                        //ship = null;
                    }
                }
                if (ship != null && ship.isLoaded()){
                    Ship shipFromFort = fort.getShipByType(type);
                    if (shipFromFort != null){
                        Ship temp = ship;
                        ship = shipFromFort;
                        fort.addShip(temp);
                    }
                }
                //fortPresenation.getScheme();
            }
            if (!fort.isFull() && ship != null && ship.isLoaded()){
                fort.addShip(ship);
                ship = null;
                fortPresenation.getScheme();
            }
            if (fort.isFinish()) {
                finish();
                System.out.println(getName() + " остановилась");
                //fortPresenation.getScheme();
            }
        }

    }
}
