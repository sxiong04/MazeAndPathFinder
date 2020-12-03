// Names: Samantha Xiong, Alex Xiong
// x500s: xion1884, xion1889

import java.lang.reflect.Array;
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
        boolean[] visited = new boolean[] {false, false, false, false};
        while (!stack.isEmpty()) {
            Cell top = stack.top();
            int next  = random.nextInt(4);
            if (visited[0] && visited[1] && visited[2] && visited[3]) {
                stack.pop();
                visited = new boolean[] {false, false, false, false};
            }
            if (next == 0 && !visited[0]) {
                visited[0] = true;
                if (top.getRow() > 0 && !maze.maze[top.getRow() - 1][top.getCol()].getVisited() && top.getRow() - 1 >= 0) {
                    stack.push(maze.maze[top.getRow() - 1][top.getCol()]);
                    maze.maze[top.getRow() - 1][top.getCol()].setVisited(true);
                    maze.maze[top.getRow() - 1][top.getCol()].setBottom(false);
                    System.out.println("above");
                }
            }
            else if (next == 1 && !visited[1]) {
                visited[1] = true;
                if (top.getCol() < cols - 1 && !maze.maze[top.getRow()][top.getCol() +1].getVisited()) {
                    stack.push(maze.maze[top.getRow()][top.getCol() +1]);
                    maze.maze[top.getRow()][top.getCol() +1].setVisited(true);
                    maze.maze[top.getRow()][top.getCol() +1].setRight(false);
                    System.out.println("right");
                }
            }
            else if (next == 2 && !visited[2]) {
                visited[2] = true;
                if (top.getRow() < rows - 1 && !maze.maze[top.getRow() + 1][top.getCol()].getVisited()) {
                    stack.push(maze.maze[top.getRow() + 1][top.getCol()]);
                    maze.maze[top.getRow() + 1][top.getCol()].setVisited(true);
                    top.setBottom(false);
                    System.out.println("below");
                }
            }
            else if (next == 3 && !visited[3]) {
                visited[3] = true;
                if (top.getCol() > 0 && !maze.maze[top.getRow()][top.getCol() - 1].getVisited() && top.getCol() - 1 >= 0) {
                    stack.push(maze.maze[top.getRow()][top.getCol() - 1]);
                    maze.maze[top.getRow()][top.getCol() - 1].setVisited(true);
                    top.setRight(false);
                    System.out.println("left");
                }
            }
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
        String[][] printMaze = new String[maze.length*2 + 1][maze[0].length];
        for (int i = 0; i < printMaze.length; i++) {
            for (int j = 0; j < printMaze[0].length; j++) {
                //printMaze[i][j] = "(" + i + "," + j + ")";
                if (i == 0 || i == printMaze.length - 1 || i % 2 == 0) {
                    printMaze[i][j] = "|---";
                } else {
                    printMaze[i][j] = "| " + maze[(i-1)/2][j].isVisited() + " ";
                }
            }
        }
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (!maze[i][j].getRight()) {
                    printMaze[i*2+1][j] = "  " + maze[i][j].isVisited() + " ";
                }
                if (!maze[i][j].getBottom()) {
                    printMaze[i*2+2][j] = "|   ";
                }
            }
        }
        printMaze[printMaze.length - 2][printMaze[0].length - 1] = "|   ";
        printMaze[1][0] = "  " + maze[0][1].isVisited() + " ";
        for (int i = 0; i < printMaze.length; i++) {
            for (int j  = 0; j < printMaze[0].length; j++) {
                System.out.print(printMaze[i][j]);
            }
            if (i != printMaze.length - 2) {
                System.out.println("|");
            } else {
                System.out.println();
            }

        }
    }


    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
    }

    public static void main(String[] args){
        makeMaze(5, 20);
    }
}
