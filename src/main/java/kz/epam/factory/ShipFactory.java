package kz.epam.factory;

import kz.epam.app.Ship;

/**
 * @author Yeralin Munar
 *         date: 3/15/17
 */
public class ShipFactory {
    private String[] names1 = {"Bon ", "Vouyager ", "Lana ", "Don ", "Pirate ", "Leon "};
    private String[] names2 = {"Bon ", "Thunder ", "Ray ", "Toljyatti ", ""};

    public ShipFactory(){}

    public int intRND(int from, int to){
        return from + (int)(Math.random() * to);
    }

    public Ship createRNDShip(){

        int name1RND = intRND(0, names1.length);
        int name2RND = intRND(0, names2.length);;
        int nameRND = intRND(0, names1.length + names2.length);

        int capacity = intRND(10, 100);
        int amount = intRND(0, capacity);
        String name = names1[name1RND] + names2[name2RND] + (nameRND != 0? nameRND : "");
        String type = Ship.SHIPTYPE.BOAT.toString();

        if (capacity >= 10 && capacity <=30)
            type = Ship.SHIPTYPE.BOAT.toString();

        if (capacity > 30 && capacity <= 70)
            type = Ship.SHIPTYPE.SHIP.toString();

        if (capacity > 70 && capacity <=100)
            type = Ship.SHIPTYPE.TANKER.toString();

        return new Ship(name, type, amount, capacity);
    }
}
