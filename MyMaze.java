// Names:
// x500s:

import java.util.Random;

public class MyMaze{
    Cell[][] maze;

    public MyMaze(int rows, int cols) {
        maze = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = new Cell(i,j);
            }
        }
    }

    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int rows, int cols) {
        MyMaze newMaze = new MyMaze(rows, cols);
        Stack1Gen<Cell> cellStack = new Stack1Gen<>();
        cellStack.push(newMaze.maze[0][0]);
        newMaze.maze[0][0].setVisited(true);
        Random random = new Random();
        int count = 0;
        while (count != 10) {
            Cell top = cellStack.top();
            if (random.nextInt(2) == 0 && top.getCol() < newMaze.maze[0].length - 1 && top.getRow() < newMaze.maze.length - 1) {
                Cell neighborRight = newMaze.maze[top.getRow()][top.getCol() + 1];
                Cell neighborBottom = newMaze.maze[top.getRow() + 1][top.getCol()];
                if (!neighborRight.getVisited()) {
                    cellStack.push(neighborRight);
                    neighborRight.setVisited(true);
                    neighborRight.setRight(false);
                    System.out.println("+1!!!!");
                    count++;
                }
                System.out.println("Right visit: " + neighborRight.getVisited());
                System.out.println("Bottom visit: " + neighborBottom.getVisited());
                if (neighborRight.getVisited() && neighborBottom.getVisited()) {
                    System.out.println("POP!!!!!");
                    cellStack.pop();
                }
            } else if (random.nextInt(2) == 1 && top.getRow() < newMaze.maze.length - 1 && top.getCol() < newMaze.maze[0].length - 1) {
                Cell neighborRight = newMaze.maze[top.getRow()][top.getCol() + 1];
                Cell neighborBottom = newMaze.maze[top.getRow() + 1][top.getCol()];
                if (!neighborBottom.getVisited()) {
                    cellStack.push(neighborBottom);
                    neighborBottom.setVisited(true);
                    neighborBottom.setBottom(false);
                    System.out.println("+1!!!!");
                    count++;
                }
                System.out.println("Right visit: " + neighborRight.getVisited());
                System.out.println("Bottom visit: " + neighborBottom.getVisited());
                if (neighborRight.getVisited() && neighborBottom.getVisited()) {
                    System.out.println("POP!!!!!");
                    cellStack.pop();
                }
            } else {
                System.out.println("ELSE + 1");
                count++;
            }
            System.out.println("ROW: " + top.getRow() + " COL: " + top.getCol() + "\n");
        }
        for (int i = 0; i < newMaze.maze.length; i++) {
            for (int j = 0; j < newMaze.maze[i].length; j++) {
                newMaze.maze[i][j].setVisited(false);
            }
        }
        return newMaze;
    }

    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze() {
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
    }

    public static void main(String[] args){
        MyMaze test = new MyMaze(4,4);
        makeMaze(4,4);
        /* Any testing can be put in this main function */
    }
}
