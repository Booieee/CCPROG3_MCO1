/**
 * Board class for the game of checkers.
 * This class will create the game board. The grid has tiles of 7 rows and 9 columns.
 * 
 */
public class Board {
    private Tile[][] grid;

    /**
     * Constructor for the Board class.
     */
    public Board() {
        this.grid = new Tile[7][9];
        initializeBoard();
    }

    /**
     * This method will return the grid of the board.
     */
    void initializeBoard(){
        // i = row, j = column
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 2 && j == 0 || i == 4 && j == 0 || i == 3 && j == 1 || 
                    i == 3 && j == 7 || i == 2 && j == 8 || i == 4 && j == 8) {
                    grid[i][j] = new Tile(i, j, "Trap");
                } else if (i == 3 && j == 0 || i == 3 && j == 8) {
                    grid[i][j] = new Tile(i, j, "Den");
                } else if (i == 1 && j == 3 || i == 1 && j == 4 || i == 1 && j == 5 ||
                           i == 2 && j == 3 || i == 2 && j == 4 || i == 2 && j == 5 ||
                           i == 4 && j == 3 || i == 4 && j == 4 || i == 4 && j == 5 ||
                           i == 5 && j == 3 || i == 5 && j == 4 || i == 5 && j == 5) {
                    grid[i][j] = new Tile(i, j, "Lake");
                } else {
                    grid[i][j] = new Tile(i, j, "Normal");
                }
            }
        }

        // Set initial positions of the animals
        grid[0][2].setOccupyingAnimal(new Animal("Elephant", 8, 0, 2, "El1"));
        grid[6][6].setOccupyingAnimal(new Animal("Elephant", 8, 6, 6, "El2"));
        grid[0][8].setOccupyingAnimal(new Animal("Lion", 7, 0, 8, "Li2"));
        grid[6][0].setOccupyingAnimal(new Animal("Lion", 7, 6, 0, "Li1"));
        grid[0][0].setOccupyingAnimal(new Animal("Tiger", 6, 0, 0, "Ti1"));
        grid[6][8].setOccupyingAnimal(new Animal("Tiger", 6, 6, 8, "Ti2"));
        grid[2][6].setOccupyingAnimal(new Animal("Leopard", 5, 2, 6, "Le2"));
        grid[4][2].setOccupyingAnimal(new Animal("Leopard", 5, 4, 2, "Le1"));
        grid[2][2].setOccupyingAnimal(new Animal("Wolf", 4, 2, 2, "Wo1"));
        grid[4][6].setOccupyingAnimal(new Animal("Wolf", 4, 4, 6, "Wo2"));
        grid[1][7].setOccupyingAnimal(new Animal("Dog", 3, 1, 7, "Do2"));
        grid[5][1].setOccupyingAnimal(new Animal("Dog", 3, 5, 1, "Do1"));
        grid[1][1].setOccupyingAnimal(new Animal("Cat", 2, 1, 1, "Ca1"));
        grid[5][7].setOccupyingAnimal(new Animal("Cat", 2, 5, 7, "Ca2"));
        grid[0][6].setOccupyingAnimal(new Animal("Rat", 1, 0, 6, "Ra2"));
        grid[6][2].setOccupyingAnimal(new Animal("Rat", 1, 6, 2, "Ra1"));

    }

    /**
     * This method will print the board.
     */
    public void printBoard() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j].isOccupied()) {
                    System.out.print(grid[i][j].getOccupyingAnimal().getSymbol() + " ");
                } else if (grid[i][j].isWater()) {
                    System.out.print("=== ");
                } else if (grid[i][j].isTrap()) {
                    System.out.print(" X  ");
                } else if (grid[i][j].isDen()) {
                    System.out.print(" O  ");
                } else {
                    System.out.print("--- ");
                }
            }
            System.out.println();
        }
    }

    /**
     * This method will return if the move is valid.
     * @param animal The animal that is moving.
     * @param x The x-coordinate of the move.
     * @param y The y-coordinate of the move.
     * @return True if the move is valid, false otherwise.
     */
    boolean isValidMove(Animal animal, int x, int y) {
        if (x < 0 || x >= 7 || y < 0 || y >= 9) {
            return false;
        }
        if (grid[x][y].isTrap()) {
            return false;
        }
        if (grid[x][y].isWater() && !animal.isSwimmer()) {
            return false;
        }
        return !grid[x][y].isOccupied();
    }

    /**
     * This method will move the animal to the new tile.
     * @param animal The animal that is moving.
     * @param x The x-coordinate of the move.
     * @param y The y-coordinate of the move.
     */
    public void moveAnimal(Animal animal, int x, int y) {
        grid[animal.getX()][animal.getY()].setOccupyingAnimal(null);
        grid[x][y].setOccupyingAnimal(animal);
        animal.setX(x);
        animal.setY(y);
    }

    public void updatePosition(Animal animal, int oldX, int oldY, int newX, int newY) {
        grid[oldX][oldY].setOccupyingAnimal(null);
        grid[newX][newY].setOccupyingAnimal(animal);
        // animal.setX(newX);
        // animal.setY(newY);
    }


    public Tile getTile(int x, int y) {
        return grid[x][y];
    }

}
