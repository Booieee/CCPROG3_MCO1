/**
 * Tile.java
 * This class will represent a tile in the game. The tiles form the foundation of the movement logic in the game.
 * The tiles will be used to create the game board.
 * 
 */
public class Tile {
    private int x;
    private int y;
    private String type; // "Normal", "Lake", "Den", "Trap"
    private Animal isOccupied;

    /**
     * Constructor for the Tile class.
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @param type The type of the tile.
     */
    public Tile(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.isOccupied = null;
    }

    /**
     * This method will check if the tile is occupied by an animal.
     * @return True if the tile is occupied, false otherwise.
     */
    public boolean isOccupied() {
        return isOccupied != null;
    }

    /**
     * This method will check if the tile is a water tile.
     * @return True if the tile is a Lake tile, false otherwise.
     */
    public boolean isWater() {
        return type.equals("Lake");
    }

    /**
     * This method will check if the tile is a den tile.
     * @return True if the tile is a den tile, false otherwise.
     */
    public boolean isDen() {
        return type.equals("Den");
    }

    /**
     * This method will check if the tile is a trap tile.
     * @return True if the tile is a trap tile, false otherwise.
     */
    public boolean isTrap() {
        return type.equals("Trap");
    }

    /**
     * This method will gets the x-coordinate of the tile.
     * @return The x-coordinate of the tile.
     */
    public int getX() {
        return x;
    }

    /**
     * This method will gets the x-coordinate of the tile.
     * @return The y-coordinate of the tile.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the type of the tile (e.g., "Normal", "Lake", "Den", "Trap").
     * @return The type of the tile.
     */
    public String getType() {
        return type;
    }

    /**
     * This method will return the type of the tile.
     * @return The type of the tile.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * This method will set the x-coordinate of the tile.
     * @param x The x-coordinate of the tile.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * This method will set the y-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method will set the type of the tile.
     * @param type The type of the tile.
     */
    public Animal getOccupyingAnimal() {
        return isOccupied;
    }

    /**
     * This method will return the animal that is occupying the tile.
     * @return The animal that is occupying the tile.
     */
    public void setOccupyingAnimal(Animal isOccupied) {
        this.isOccupied = isOccupied;
    }

}
