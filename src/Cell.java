/*
    A Maze is made up of Cells
 */
public class Cell {
    private boolean visited;    // whether the cell has been visited (true if visited, false if not visited)
    private boolean right;      // whether the cell has a right border (true if a right boundary, false if an open right)
    private boolean bottom;     // whether the cell has a bottom border (true if a bottom boundary, false if an open bottom)
    private int row;
    private int col;

    // All cells are initialized to full walls
    public Cell(){
        visited = false;
        right = true;
        bottom = true;
    }

    public Cell(int row, int col){
        visited = false;
        right = true;
        bottom = true;
        this.row = row;
        this.col = col;
    }

    /**********
     * Setter functions
     **********/
    public void setVisited(boolean visited) { this.visited = visited; }
    public void setRight(boolean right) { this.right = right; }
    public void setBottom(boolean bottom) { this.bottom = bottom; }
    public void setCol(int col) { this.col = col; }
    public void setRow(int row) { this.row = row; }

    /**********
     * Getter functions
     **********/
    public boolean getVisited() { return visited; }
    public boolean getRight() { return right; }
    public boolean getBottom() { return bottom; }
    public int getCol() { return col; }
    public int getRow() {return row; }
}
