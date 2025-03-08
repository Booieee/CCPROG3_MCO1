import java.util.ArrayList;

/**
 * Player class
 * Initializes the player object
 */
public class Player {
    private String name;
    private String side;
    private ArrayList<Animal> animals;

    private static final int PLAYER1_DEN_X = 3;  // Example values
    private static final int PLAYER1_DEN_Y = 0;
    private static final int PLAYER2_DEN_X = 3;
    private static final int PLAYER2_DEN_Y = 8;
 

    /**
     * Constructor for the Player class.
     * @param name The name of the player.
     */
    public Player(String name, String side) {
        this.name = name;
        this.side = side;
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
        int opponentDenX = side.equals("Left") ? PLAYER2_DEN_X : PLAYER1_DEN_X;
        int opponentDenY = side.equals("Left") ? PLAYER2_DEN_Y : PLAYER1_DEN_Y;


        for (Animal animal : animals) {
            if (animal.getX() == opponentDenX && animal.getY() == opponentDenY) {
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
