package kz.epam.app;

/**
 * @author Yeralin Munar
 *         date: 3/15/17
 */
public class Ship {
    private String name;
    private String type;
    private int amount;
    private int capacity;
    private boolean loaded = false;
    public enum SHIPTYPE {BOAT, SHIP, TANKER};

    public Ship(){}

    public Ship(String name, String type, int amount, int capacity){
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.capacity = capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public int getCapacity() {
        return capacity;
    }

    public int load(){
        double amount = this.amount;
        double capacity = this.capacity;
        return (int)(amount/capacity * 100);
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public String toString(){
        return getName() + " (type:" +getType()+"; load:"+load()+ ")" + isLoaded();
    }
}
