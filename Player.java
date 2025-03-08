import java.util.ArrayList;

/**
 * Player class
 * Initializes the player object
 */
public class Player {
    private String name;
    private ArrayList<Animal> animals;

    private static final int PLAYER1_DEN_X = 0;  // Example values
    private static final int PLAYER1_DEN_Y = 3;
    private static final int PLAYER2_DEN_X = 8;
    private static final int PLAYER2_DEN_Y = 3;
 

    /**
     * Constructor for the Player class.
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.animals = new ArrayList<>();
    }

    /**
     * This method will add an animal to the player's list of animals.
     * @param animal The animal to be added.
     */
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    // This method will check if the player wins if any of the animals reach the den
    public boolean winCondition(){
        for (Animal animal : animals) {
            if (this.name.equals("Player1") && animal.getX() == PLAYER2_DEN_X && animal.getY() == PLAYER2_DEN_Y) {
                return true;
            }
            if (this.name.equals("Player2") && animal.getX() == PLAYER1_DEN_X && animal.getY() == PLAYER1_DEN_Y) {
                return true;
            }
        }
        return false;
    }

    public Animal getAnimalSymbol(String symbol) {
       for (Animal animal : animals) {
           if (animal.getSymbol().equals(symbol)) {
               return animal;
           }
       }
        return null;
    }


    public String getName(){
        return this.name;
    }

    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAnimals(ArrayList<Animal> animals){
        this.animals = animals;
    }
}
