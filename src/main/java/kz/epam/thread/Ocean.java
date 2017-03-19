package kz.epam.thread;

import kz.epam.app.Ship;
import kz.epam.factory.ShipFactory;
import kz.epam.presentation.FortPresenation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yeralin Munar
 *         date: 3/15/17
 */
public class Ocean extends Thread {
    private final int DEFAULT_LIMIT = 4;

    private volatile List<Ship> ships = new ArrayList<Ship>();
    private int limit = DEFAULT_LIMIT; // Максимальное количество кораблей
    private volatile FortPresenation fortPresenation;

    private boolean full = false;
    private boolean finish = false;

    public Ocean(){}

    public Ocean(String name){
        setName(name);
    }

    public synchronized List<Ship> getShips() {
        return ships;
    }

    public synchronized Ship getShip(){
        if (!ships.isEmpty()) {
            Iterator<Ship> shipIterator = ships.iterator();
            while (shipIterator.hasNext()) {
                Ship ship = shipIterator.next();
                if (!ship.isLoaded()) {
                    ships.remove(ship);
                    return ship;
                }
            }
        }
        return null;
    }

    public synchronized void addShip(Ship ship){
        ships.add(ship);
    }

    public void removeShip(Ship ship){
        ships.remove(ship);
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setFortPresenation(FortPresenation fortPresenation) {
        fortPresenation.setOcean(this);
        this.fortPresenation = fortPresenation;
    }

    public boolean isFinish() {
        return finish;
    }

    public void finish(){
        finish = true;
    }

    public void run(){
        int addCount = 0;
        int removeCount = 0;
        ShipFactory shipFactory = new ShipFactory();
        while (!finish){
            if (addCount < limit) {
                int rndShipCount = (int) (Math.random() * limit);
                for (int i = 0; i <= rndShipCount; i++) {
                    if (rndShipCount <= (limit - addCount)) {
                        Ship ship = shipFactory.createRNDShip();
                        addShip(ship);
                        addCount++;
                        System.out.println("ocean " + addCount);
                    }
                }
                try {
                    fortPresenation.getScheme();

                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (!getShips().isEmpty()) {
                fortPresenation.getScheme();
                Iterator<Ship> shipIterator = getShips().iterator();
                while (shipIterator.hasNext()) {
                    Ship ship = shipIterator.next();
                    if (ship.isLoaded()) {
                        shipIterator.remove();
                        System.out.println(getName() + " удаление корабля");
                        removeCount++;
                        System.out.println("ocean " + removeCount);
                    }
                }
            }
            if ((addCount + removeCount) == (2 * limit) && ships.size() == 0){
                finish();
                System.out.println(getName() + " остановился");
            }
        }
    }

    @Override
    public String toString(){
        String str = "";
        for (Ship ship:ships)
            str += "{" + ship.getName() + "}";

        return str;
    }
}
