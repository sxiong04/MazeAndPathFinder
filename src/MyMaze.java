// Names: Samantha Xiong, Alex Xiong
// x500s: xion1884, xion1889

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
            } // pop
            if (next == 0 && !visited[0]) {
                visited[0] = true;
                if (top.getRow() > 0 && !maze.maze[top.getRow() - 1][top.getCol()].getVisited() && top.getRow() - 1 >= 0) {
                    stack.push(maze.maze[top.getRow() - 1][top.getCol()]);
                    maze.maze[top.getRow() - 1][top.getCol()].setVisited(true);
                    maze.maze[top.getRow() - 1][top.getCol()].setBottom(false);
                    visited = new boolean[] {false, false, false, false};
                }
            } // top
            else if (next == 1 && !visited[1]) {
                visited[1] = true;
                if (top.getCol() < cols - 1 && !maze.maze[top.getRow()][top.getCol() +1].getVisited()) {
                    stack.push(maze.maze[top.getRow()][top.getCol() +1]);
                    maze.maze[top.getRow()][top.getCol() +1].setVisited(true);
                    maze.maze[top.getRow()][top.getCol() +1].setRight(false);
                    visited = new boolean[] {false, false, false, false};
                }
            } // rgt
            else if (next == 2 && !visited[2]) {
                visited[2] = true;
                if (top.getRow() < rows - 1 && !maze.maze[top.getRow() + 1][top.getCol()].getVisited()) {
                    stack.push(maze.maze[top.getRow() + 1][top.getCol()]);
                    maze.maze[top.getRow() + 1][top.getCol()].setVisited(true);
                    top.setBottom(false);
                    visited = new boolean[] {false, false, false, false};
                }
            } // btm
            else if (next == 3 && !visited[3]) {
                visited[3] = true;
                if (top.getCol() > 0 && !maze.maze[top.getRow()][top.getCol() - 1].getVisited() && top.getCol() - 1 >= 0) {
                    stack.push(maze.maze[top.getRow()][top.getCol() - 1]);
                    maze.maze[top.getRow()][top.getCol() - 1].setVisited(true);
                    top.setRight(false);
                    visited = new boolean[] {false, false, false, false};
                }
            } // lft
        }

        for (int i = 0; i < maze.maze.length; i++) {
            for (int j = 0; j < maze.maze[i].length; j++) {
                maze.maze[i][j].setVisited(false);
            }
        }
        return maze;
    }

    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze() {
        String[][] printMaze = new String[maze.length*2 + 1][maze[0].length];
        for (int i = 0; i < printMaze.length; i++) {
            for (int j = 0; j < printMaze[0].length; j++) {
                //printMaze[i][j] = "(" + i + "," + j + ")";
                if (i == printMaze.length - 1 || i % 2 == 0) {
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
        Q1Gen<Cell> queue = new Q1Gen<>();
        queue.add(maze[0][0]);
        while(!queue.isEmpty()) {
            Cell current = queue.remove();
            if (current.getRow() == maze.length - 1 && current.getCol() == maze[0].length - 1) {
                break;
            } else {
                if (current.getRow() > 0 && !maze[current.getRow() - 1][current.getCol()].getVisited() && current.getRow() - 1 >= 0 && !maze[current.getRow() - 1][current.getCol()].getBottom()) {
                    maze[current.getRow() - 1][current.getCol()].setVisited(true);
                    queue.add(maze[current.getRow() - 1][current.getCol()]);
                } //top
                if (current.getCol() > 0 && !maze[current.getRow()][current.getCol() - 1].getVisited() && !current.getRight()) {
                    maze[current.getRow()][current.getCol() - 1].setVisited(true);
                    queue.add(maze[current.getRow()][current.getCol() - 1]);
                } // lft
                if (current.getCol() < maze[0].length - 1 && !maze[current.getRow()][current.getCol() +1].getVisited() && !maze[current.getRow()][current.getCol() +1].getRight()) {
                    maze[current.getRow()][current.getCol() +1].setVisited(true);
                    queue.add(maze[current.getRow()][current.getCol() +1]);
                } // rgt
                if (current.getRow() < maze.length - 1 && !maze[current.getRow() + 1][current.getCol()].getVisited() && !current.getBottom()) {
                    maze[current.getRow() + 1][current.getCol()].setVisited(true);
                    queue.add(maze[current.getRow() + 1][current.getCol()]);
                } //btm
            }
        }
        printMaze();
    }

    public static void main(String[] args){
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int row = random.nextInt(50);
            int col = random.nextInt(50);
            makeMaze(5, 5).solveMaze();
            System.out.println();
            System.out.println();
        }
    }
}
