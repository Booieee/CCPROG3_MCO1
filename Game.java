
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

/**
 * Game.java
 * This class is the main class for the game. It will contain the main method that will run the Jungle King game.
 * The game will have two players, a board, and a current player. The game will continue until one of the players reaches the den.
 * The player can control W,A,S,D to move the animal.
 */
public class Game {
    private Player player1, player2;
    private Board board;
    private Player currentPlayer;
    private boolean isGameOver;
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Animal> shuffledAnimals = new ArrayList<>();

    /**
     * Constructor for the Game class.
     * @param ply1Name The name of player 1.
     * @param ply2Name The name of player 2.
     */
    public Game(String ply1Name, String ply2Name) {
        this.player1 = new Player(ply1Name, "Left");
        this.player2 = new Player(ply2Name, "Right");
        this.board = new Board();
        this.isGameOver = false;
        preGame();
    }

    /**
     * This method will set a pre-game where the players will pick an animal and determine 
     * who goes first.
     */
    private void preGame(){
        // Shuffle the animals
        List<Animal> animals = Arrays.asList(
            new Animal("Rat", 1), new Animal("Cat", 2),
            new Animal("Dog", 3), new Animal("Wolf", 4),
            new Animal("Leopard", 5), new Animal("Tiger", 6),
            new Animal("Lion", 7), new Animal("Elephant", 8));

        Collections.shuffle(animals);
        shuffledAnimals.addAll(animals);

        Animal animal1, animal2;
        int animalIndex1 = 0, animalIndex2 = 0;

        //a loop to ensure error handling
        //players should only pick a number between 1-8, and players should not pick the same number
        do{
            System.out.println(player1.getName() + ", pick an animal (1-8): ");
            printShuffledAnimals();
            
            if(!sc.hasNextInt()){
                System.out.println("Invalid input. Please pick a number from the list.");
                sc.next();
                continue;
            } 

            animalIndex1 = sc.nextInt();
            sc.nextLine();

            if(animalIndex1 < 1 || animalIndex1 > shuffledAnimals.size()){
                System.out.println("Invalid number. Please pick a number from the list.");
                continue;
            }

            animal1 = shuffledAnimals.get(animalIndex1 - 1);
            break;
            
        }while(true);
            
        do{    
            System.out.println(player2.getName() + ", pick an animal (1-8): ");
            
            if(!sc.hasNextInt()){
                System.out.println("Invalid input. Please pick a number from the list.");
                sc.next();
                continue;
            }

            animalIndex2 = sc.nextInt();
            sc.nextLine();

            if(animalIndex2 < 1 || animalIndex2 > shuffledAnimals.size()){
                System.out.println("Invalid number. Please pick a number from the list.");
                continue;
            } else if(animalIndex2 == animalIndex1){
                System.out.println("You cannot pick the same animal as " + player1.getName());
                continue;
            }

            animal2 = shuffledAnimals.get(animalIndex2 - 1);
            break;

        }while(true);
        
        // Determine who goes first
        if (animal1.getRank() > animal2.getRank()) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }

        System.out.println(player1.getName() + " picked " + animal1.getSpecies() + " (Rank " + animal1.getRank() + ")");
        System.out.println(player2.getName() + " picked " + animal2.getSpecies() + " (Rank " + animal2.getRank() + ")");
        System.out.println(currentPlayer.getName() + " goes first.");
    }

    /**
     * This method will print the shuffled animals.
     */
    private void printShuffledAnimals() {
        for (int i = 0; i < shuffledAnimals.size(); i++) {
            System.out.println((i + 1) + ". ??? (Face Down)");
        }
    }

    /**
     * This method will set up the initial positions of the animals.
     */
    private void setupAnimals(){
        player1.addAnimal(new Animal("Elephant", 8, 0, 2, "El1"));
        player1.addAnimal(new Animal("Lion", 7, 6, 0, "Li1"));
        player1.addAnimal(new Animal("Tiger", 6, 0, 0, "Ti1"));
        player1.addAnimal(new Animal("Leopard", 5, 4, 2, "Le1"));
        player1.addAnimal(new Animal("Wolf", 4, 2, 2, "Wo1"));
        player1.addAnimal(new Animal("Dog", 3, 5, 1, "Do1"));
        player1.addAnimal(new Animal("Cat", 2, 1, 1, "Ca1"));
        player1.addAnimal(new Animal("Rat", 1, 6, 2, "Ra1"));

        player2.addAnimal(new Animal("Elephant", 8, 6, 6, "El2"));
        player2.addAnimal(new Animal("Lion", 7, 0, 8, "Li2"));
        player2.addAnimal(new Animal("Tiger", 6, 6, 8, "Ti2"));
        player2.addAnimal(new Animal("Leopard", 5, 2, 6, "Le2"));
        player2.addAnimal(new Animal("Wolf", 4, 4, 6, "Wo2"));
        player2.addAnimal(new Animal("Dog", 3, 1, 7, "Do2"));
        player2.addAnimal(new Animal("Cat", 2, 5, 7, "Ca2"));
        player2.addAnimal(new Animal("Rat", 1, 0, 6, "Ra2"));
    }

    /**
     * This method will run the game.
     */
    public void startGame() {
        setupAnimals();

        while (!isGameOver) {
            System.out.println("Current player: " + currentPlayer.getName());
            board.printBoard();

            System.out.println("Select an animal to move (Enter symbol): ");
            String symbol = sc.nextLine();
            Animal selectedAnimal = currentPlayer.getAnimalSymbol(symbol);

            if (selectedAnimal == null) {
                System.out.println("Invalid animal. Please select an animal from your list.");
                continue;
            }

            System.out.println("Use W,A,S,D to move the animal.");
            char move = sc.next().toUpperCase().charAt(0);
            sc.nextLine();

            int newX = selectedAnimal.getX();
            int newY = selectedAnimal.getY();

            switch (move) {
                case 'W': 
                    if ((selectedAnimal.getSpecies().equals("Lion") || 
                         selectedAnimal.getSpecies().equals("Tiger")) && board.getTile(newX - 1, newY).isWater()) {
                        newX -= 3; // Jump over the lake (3 tiles up)
                        System.out.println(selectedAnimal.getSpecies() + " jumps over the lake!");
                    } else {
                        newX--;
                    }
                    break; 
                case 'A': 
                    if ((selectedAnimal.getSpecies().equals("Lion") || 
                         selectedAnimal.getSpecies().equals("Tiger")) && board.getTile(newX, newY - 1).isWater()) {
                        newY -= 4; // Jump over the lake (4 tiles left)
                        System.out.println(selectedAnimal.getSpecies() + " jumps over the lake!");
                    } else {
                        newY--;
                    }
                    break;
                case 'S': 
                    if ((selectedAnimal.getSpecies().equals("Lion") || 
                         selectedAnimal.getSpecies().equals("Tiger")) && board.getTile(newX + 1, newY).isWater()) {
                        newX += 3; // Jump over the lake (3 tiles down)    
                        System.out.println(selectedAnimal.getSpecies() + " jumps over the lake!");
                    } else {
                        newX++;
                    }
                    break;
                case 'D': 
                    if ((selectedAnimal.getSpecies().equals("Lion") || 
                         selectedAnimal.getSpecies().equals("Tiger")) && board.getTile(newX, newY + 1).isWater()) {
                        newY += 4; // Jump over the lake (4 tiles right)
                        System.out.println(selectedAnimal.getSpecies() + " jumps over the lake!");
                    } else {
                        newY++;
                    }
                    break;
                default:
                    System.out.println("Invalid move. Use W,A,S,D to move the animal.");
                    continue;
            }

            if (board.isValidMove(selectedAnimal, newX, newY)) {
                Tile oldTile = board.getTile(selectedAnimal.getX(), selectedAnimal.getY());
                Tile targetTile = board.getTile(newX, newY);
                Animal targetAnimal = targetTile.getOccupyingAnimal();

                // Check if the animal is attacking
                if (targetAnimal != null) {
                    if(currentPlayer.getAnimals().contains(targetAnimal)){
                        System.out.println("Invalid move. You cannot attack your own animal.");
                        continue;
                    }

                    System.out.println(selectedAnimal.getSpecies() + " is trying to attack " + targetAnimal.getSpecies());

                    if (selectedAnimal.canCapture(targetAnimal, targetTile)) {
                        System.out.println(selectedAnimal.getSpecies() + " captured " + targetAnimal.getSpecies());

                        //Remove the target animal from the player's list of animals
                        if (currentPlayer == player1) {
                            player2.getAnimals().remove(targetAnimal);
                        } else {
                            player1.getAnimals().remove(targetAnimal);
                        }

                        //update the position
                        board.updatePosition(selectedAnimal, selectedAnimal.getX(), selectedAnimal.getY(), newX, newY);
                        oldTile.setOccupyingAnimal(null);

                    } else {
                        System.out.println("Invalid attack. " + selectedAnimal.getSpecies() + " cannot capture " + targetAnimal.getSpecies());
                        continue;
                    }
                } else {
                    targetTile.setOccupyingAnimal(selectedAnimal);
                    oldTile.setOccupyingAnimal(null);

                }

                 // Move the animal
                 selectedAnimal.move(newX, newY);

                
                // Check win condition
                if (currentPlayer.winCondition()) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    isGameOver = true;
                }else{
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                }
                
            } else {
                System.out.println("Invalid move. Try again.");
            }
            
        }
    }

}
