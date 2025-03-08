import java.util.ArrayList;
import java.util.Set;

/** 
 * This is the Animal class. It represents the animals in the game.
 * The animals are the Lion, Tiger, Leopard, Wolf, Dog, Cat, Rat, and Elephant.
 * The animals have a rank and a name.
 * The animals can move and attack.
 * The animals can be captured by other animals.
 * Animals like Lions and Tigers can jump over water.
 * Rat can go into the water.
 * 
 */
public class Animal {
    private String species;
    private int rank;
    private int x, y;
    private String symbol;
    private static final ArrayList<String> SWIMMERS = new ArrayList<>(Set.of("Rat", "Lion", "Tiger"));
    /**
     * Constructor for the Animal class.
     * @param species The species of the animal.
     * @param rank The rank of the animal.
     * @param x The x-coordinate of the animal.
     * @param y The y-coordinate of the animal.
     * @param owner The owner of the animal.
     */
    public Animal(String species, int rank, int x, int y, String symbol) {
        this.species = species;
        this.rank = rank;
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    public Animal(String species, int rank) {
        this.species = species;
        this.rank = rank;
    }

    /**
     * This method will check if the animal can capture the opponent's target.
     * @param oppTarget The opponent's target.
     * @return True if the animal can capture the opponent's target, false otherwise.
     */
    public boolean canCapture(Animal oppTarget, Tile oppTile){
        if(oppTile.isTrap()){
            return true;
        }
    
        if (this.getSpecies().equals("Rat") && oppTarget.getSpecies().equals("Elephant")){
            return true;
        }

        return this.rank >= oppTarget.getRank();
    }

    /**
     * This method will move the animal to the new coordinates.
     * @param x The new x-coordinate of the animal.
     * @param y The new y-coordinate of the animal.
     */
    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move(String direction) {
        switch (direction.toLowerCase()) {
            case "up":
                move(x, y - 1);
                break;
            case "down":
                move(x, y + 1);
                break;
            case "left":
                move(x - 1, y);
                break;
            case "right":
                move(x + 1, y);
                break;
        }
    }

    /**
     * This method will check if the animal is a swimmer.
     * @return True if the animal is a swimmer, false otherwise.
     */
    public boolean isSwimmer(){
        return SWIMMERS.contains(this.species);
    }

    
    public String getSpecies(){
        return this.species;
    }

    /**
     * This method will return the rank of the animal.
     * @return The rank of the animal.
     */
    public int getRank(){
        return this.rank;
    }

    /**
     * This method will return the x-coordinate of the animal.
     * @return The x-coordinate of the animal.
     */
    public int getX(){
        return this.x;
    }

    /**
     * This method will set the x-coordinate of the animal.
     * @param x The x-coordinate of the animal.
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * This method will return the y-coordinate of the animal.
     * @return The y-coordinate of the animal.
     */
    public int getY(){
        return this.y;
    }

    /**
     * This method will set the y-coordinate of the animal.
     * @param y The y-coordinate of the animal.
     */
    public void setY(int y){
        this.y = y;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String toString(){
        return this.species + " " + this.rank;
    }

}
