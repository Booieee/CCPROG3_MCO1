import java.util.Scanner;

public class Drive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Jungle King!");
        System.out.println("Player 1, please enter your name: ");
        String ply1Name = sc.nextLine();
        System.out.println("Player 2, please enter your name: ");
        String ply2Name = sc.nextLine();

        // Create a new game object
        Game game = new Game(ply1Name, ply2Name);
        // Run the game
        game.startGame();

        sc.close();

    }
}
