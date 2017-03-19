package kz.epam.thread;

import kz.epam.app.Ship;
import kz.epam.presentation.FortPresenation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yeralin Munar
 *         date: 3/15/17
 */
public class Fort extends Thread{
    private final int DEFAULT_TOTAL_SHIPS = 4;
    private final int DEFAULT_LIMIT = 1;


    private volatile List<Ship> ships = new ArrayList<Ship>();
    private Ocean ocean;
    private FortPresenation fortPresenation;

    private int totalShips = DEFAULT_TOTAL_SHIPS; // Общее количество кораблей проходящи через порт
    private int limit = DEFAULT_LIMIT; // Пропускная способность порта (limit кораблей за раз)

    private boolean full = false;
    private volatile boolean finish = false;

    public Fort(){
    }

    public Fort(String name){
        setName(name);
    }

    public synchronized void addShip(Ship ship){
        ships.add(ship);
    }

    public synchronized List<Ship> getShips(){
        return this.ships;
    }

    public synchronized Ship getShipByType(String type){
        if (!getShips().isEmpty()) {
            Iterator<Ship> shipIterator = getShips().iterator();
            while (shipIterator.hasNext()) {
                Ship ship = shipIterator.next();
                if (ship.getType().equals(type) && !ship.isLoaded() ) {
                    shipIterator.remove();
                    return ship;
                }
            }
        }
        return null;
    }

    public void setLimit(int limit) {
        if (limit > 0)
            this.limit = limit;
        else
            this.limit = 1;
    }

    public void setTotalShips(int totalShips) {
        if (totalShips > 0)
            this.totalShips = totalShips;
        else
            this.totalShips = 4;
    }

    public void setOcean(Ocean ocean){
        this.ocean = ocean;
    }

    public boolean isFull() {
        return getShips().size() == limit;
    }

    public synchronized boolean isFinish() {
        return finish;
    }

    public synchronized void finish(){
        finish = true;
    }

    public void setFortPresenation(FortPresenation fortPresenation) {
        fortPresenation.setFort(this);
        this.fortPresenation = fortPresenation;
    }

    public void run(){
        //int count = 0;
        while (!finish) {

            if (!isFull() && !ocean.getShips().isEmpty()){

                for (int i = 0; i < limit; i++){
                    Ship ship = ocean.getShip();
                    if (ship != null) {
                        getShips().add(ship);
                        //count++;
                        fortPresenation.getScheme();
                    }
                }

            }

            if (ocean.isAlive()) {
                if (!getShips().isEmpty()) {
                    Iterator<Ship> shipIterator = getShips().iterator();
                    while (shipIterator.hasNext()) {
                        Ship ship = shipIterator.next();
                        if (ship!= null && ship.isLoaded()) {
                            shipIterator.remove();
                            ocean.addShip(ship);
                            fortPresenation.getScheme();
                            //count++;
                        }
                    }
                }
            }
            if (ocean.isFinish()) {
                finish();
                System.out.println(getName() + " остановился");
            }
        }
    }
}
