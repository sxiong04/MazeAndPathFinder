// Names: Samantha Xiong, Alex Xiong
// x500s: xion1884, xion1889

import java.util.Arrays;
import java.util.Random;

public class MyMaze{
    Cell[][] maze;

    public MyMaze(int rows, int cols) {
        maze = new Cell[rows][cols];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = new Cell(i, j);
            }
        }
    }

    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int rows, int cols) {
        MyMaze maze = new MyMaze(rows, cols);
        Stack1Gen<Cell> stack = new Stack1Gen<>();
        stack.push(maze.maze[0][0]);
        maze.maze[0][0].setVisited(true);

        Random random = new Random();
        while (!stack.isEmpty()) {
            Cell top = stack.top();
            int neighborCell = random.nextInt(4);
            if (neighborCell == 0 && top.getRow() > 0 && !maze.maze[top.getRow() - 1][top.getCol()].getVisited() && top.getRow() - 1 >= 0){
                stack.push(maze.maze[top.getRow() - 1][top.getCol()]);
                maze.maze[top.getRow() - 1][top.getCol()].setVisited(true);
                maze.maze[top.getRow() - 1][top.getCol()].setBottom(false);
            } // neighbor above
            else if (neighborCell == 1 && top.getCol() < cols - 1 && !maze.maze[top.getRow()][top.getCol() +1].getVisited()) {
                stack.push(maze.maze[top.getRow()][top.getCol() +1]);
                maze.maze[top.getRow()][top.getCol() +1].setVisited(true);
                maze.maze[top.getRow()][top.getCol() +1].setRight(false);
            } // neighbor right
            else if (neighborCell == 2 && top.getRow() < rows - 1 && !maze.maze[top.getRow() + 1][top.getCol()].getVisited()) {
                stack.push(maze.maze[top.getRow() + 1][top.getCol()]);
                maze.maze[top.getRow() + 1][top.getCol()].setVisited(true);
                top.setBottom(false);
            } // neighbor below
            else if (neighborCell == 3 && top.getCol() > 0 && !maze.maze[top.getRow()][top.getCol() - 1].getVisited() && top.getCol() - 1 >= 0) {
                stack.push(maze.maze[top.getRow()][top.getCol() - 1]);
                maze.maze[top.getRow()][top.getCol() - 1].setVisited(true);
                top.setRight(false);
            } // neighbor left
            else {
                stack.pop();
            } // pop cell
        }

//        for (int i = 0; i < maze.maze.length; i++) {
//            for (int j = 0; j < maze.maze[i].length; j++) {
//                maze.maze[i][j].setVisited(false);
//            }
//        }
        maze.printMaze();
        return maze;
    }

    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print("-".repeat(3));
            }
            System.out.println();
        }
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
    }

    public static void main(String[] args){
        makeMaze(5, 21);
    }
}
