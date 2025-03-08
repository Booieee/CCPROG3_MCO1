
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
     */
    public Game(String ply1Name, String ply2Name) {
        this.player1 = new Player(ply1Name, "Left");
        this.player2 = new Player(ply2Name, "Right");
        this.board = new Board();
        this.isGameOver = false;
        preGame();
    }

    private void preGame(){
        // Shuffle the animals
        List<Animal> animals = Arrays.asList(
            new Animal("Rat", 1), new Animal("Cat", 2),
            new Animal("Dog", 3), new Animal("Wolf", 4),
            new Animal("Leopard", 5), new Animal("Tiger", 6),
            new Animal("Lion", 7), new Animal("Elephant", 8));

        Collections.shuffle(animals);
        shuffledAnimals.addAll(animals);

        // Player pick an animal
        System.out.println(player1.getName() + ", pick an animal by selecting a number (1-8): ");
        printShuffledAnimals();
        int animalIndex1 = sc.nextInt() - 1;
        Animal animal1 = shuffledAnimals.get(animalIndex1);

        System.out.println(player2.getName() + ", pick an animal by selecting a number (1-8): ");
        int animalIndex2 = sc.nextInt() - 1;
        Animal animal2 = shuffledAnimals.get(animalIndex2);

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

    private void printShuffledAnimals() {
        for (int i = 0; i < shuffledAnimals.size(); i++) {
            System.out.println((i + 1) + ". ??? (Face Down)");
        }
    }


    // Add animals to players
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
                case 'W': newX--; break;
                case 'A': newY--; break;
                case 'S': newX++; break;
                case 'D': newY++; break;
                default:
                    System.out.println("Invalid move. Use W,A,S,D to move the animal.");
                    continue;
            }

            if (board.isValidMove(selectedAnimal, newX, newY)) {
                Tile oldTile = board.getTile(selectedAnimal.getX(), selectedAnimal.getY());
                Tile targetTile = board.getTile(newX, newY);
                Animal targetAnimal = targetTile.getOccupyingAnimal();
                
                if (targetAnimal != null) {
                    System.out.println(selectedAnimal.getSpecies() + " is trying to attack " + targetAnimal.getSpecies());

                    if (selectedAnimal.canCapture(targetAnimal, targetTile)) {
                        System.out.println(selectedAnimal.getSpecies() + " captured " + targetAnimal.getSpecies());

                        //Remove opponent's animal and update the position
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
                   // clearConsole();
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                }
                
            } else {
                System.out.println("Invalid move. Try again.");
            }
            
        }
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
