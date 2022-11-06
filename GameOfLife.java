package conwaygame;
import java.util.ArrayList;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Sara Phondge
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE; 
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {
        StdIn.setFile(file);
        int row=StdIn.readInt();
        int col=StdIn.readInt();
        grid = new boolean[row][col];
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                grid[r][c] = StdIn.readBoolean();
            }
        }
    }

    /**x
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

        // WRITE YOUR CODE HERE
        return grid [row][col]; // update this line, provided so that code compiles
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {
        // nested for loop
        // WRITE YOUR CODE HERE
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid.length; c++){
                if(grid[r][c] == ALIVE){
                    return true;
                }
            }
        }   
        return false; // update this line, provided so that code compiles
    }
    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {      
        int numAlive = 0;
        int numOfRows = grid.length;
        int numOfCols = grid[0].length;

        // Check if the Clicked Cell is in the Periphery? 
        if (row==0 || col==0 || row==(numOfRows-1) || col==(numOfCols-1)){

            if (row==0) {       // Search Cells Horizontally for row = 0 going Right 
                if (col==0){    // Left corner cell 0,0 was clicked
                        // check 8 neighboring cells according to rules
                       
                        if ( grid[row]  [col+1] == ALIVE )            numAlive++;         // 0,1
                        if ( grid[row]  [numOfCols-1] == ALIVE )      numAlive++;         // 0,3
                        if ( grid[row+1][col] == ALIVE )              numAlive++;         // 1,0
                        if ( grid[row+1][col+1] == ALIVE )          numAlive++;         // 1,1
                        if ( grid[numOfRows-1][col] == ALIVE )        numAlive++;         // 3,0
                        if ( grid[numOfRows-1][col+1] == ALIVE )    numAlive++;         // 3,1
                        if ( grid[row+1][numOfCols-1] == ALIVE )    numAlive++;         // 1,3
                        if ( grid[numOfRows-1][numOfCols-1] == ALIVE ) numAlive++;      // 3,3
                        
                } else if (col == numOfCols-1) {  //Right corner cell 0,3 was clicked
                        // check 8 neighboring cells according to rules
                     
                        if ( grid[row]  [numOfCols-2] == ALIVE ) numAlive++;            // 0,2
                        if ( grid[row+1]  [numOfCols-1] == ALIVE ) numAlive++;        // 1,3
                        if ( grid[row+1][numOfCols-2] == ALIVE ) numAlive++;          // 1,2
                        if ( grid[numOfRows-1][numOfCols-1] == ALIVE ) numAlive++;  // 3,3
                        if ( grid[numOfRows-1][numOfCols-2] == ALIVE ) numAlive++;  // 3,2
                        if ( grid[numOfRows-1][0] == ALIVE ) numAlive++;              // 3,0
                        if ( grid[0][0] == ALIVE ) numAlive++;                          // 0,0
                        if ( grid[row+1][0] == ALIVE ) numAlive++;                      // 1,0
                        //return numAlive;
                } else {    // Cells between 0,0 and 0,3 were clicked 
                          
                            // check 8 neighboring cells according to rules
                            if (grid[row][col-1] == ALIVE) numAlive++;
                            if (grid[row][col+1] == ALIVE) numAlive++;
                            if (grid[row+1][col-1] == ALIVE) numAlive++;
                            if (grid[row+1][col] == ALIVE) numAlive++;
                            if (grid[row+1][col+1] == ALIVE) numAlive++;
                            if (grid[numOfRows-1][col-1] == ALIVE) numAlive++;
                            if (grid[numOfRows-1][col] == ALIVE) numAlive++;
                            if (grid[numOfRows-1][col+1] == ALIVE) numAlive++;
                       
                } 

            } else if (col==0) { // Search Cells Verticaly from Col = 0 going down 

                if (row==0){ // 0,0 was clicked 
                    // This is already covered by 0,0 above so no need to calculate
                } else if (row == numOfRows-1) {   // corner cell 3,0 was clicked
                        // check 8 neighboring cells according to rules
                       
                        if ( grid[row-1]  [col] == ALIVE ) numAlive++;                  // 2,0
                        if ( grid[row-1]  [col+1] == ALIVE ) numAlive++;                // 2,1
                        if ( grid[row][col+1] == ALIVE ) numAlive++;                    // 3,1
                        if ( grid[0][0] == ALIVE ) numAlive++;                          // 0,0
                        if ( grid[0][col+1] == ALIVE ) numAlive++;                      // 0,1
                        if ( grid[row-1][numOfCols-1] == ALIVE ) numAlive++;            // 2,3
                        if ( grid[row][numOfCols-1] == ALIVE ) numAlive++;              // 3,3
                        if ( grid[0][numOfCols-1] == ALIVE ) numAlive++;                // 0,3
                        //return numAlive;
                } else { //cells between 0,0 and 3,0 was clicked 
                                    
                        // check 8 neighboring cells according to rules
                        if (grid[row-1][col] == ALIVE) numAlive++;
                        if (grid[row+1][col] == ALIVE) numAlive++;
                        if (grid[row-1][col+1] == ALIVE) numAlive++;
                        if (grid[row][col+1] == ALIVE) numAlive++;
                        if (grid[row+1][col+1] == ALIVE) numAlive++;
                        if (grid[row-1][numOfCols-1] == ALIVE) numAlive++;
                        if (grid[row][numOfCols-1] == ALIVE) numAlive++;
                        if (grid[row+1][numOfCols-1] == ALIVE) numAlive++;
            
                } 

            } else if (row==(numOfRows-1)) { // Search Cells Horizonatlly from 3,0 to 3,3 going Right 
                if (col==0) { // 3,0 was clicked
                    // This cell is already covered 3,0
                } else if (col==(numOfCols-1) ) { // Corner Cell 3,3
                   
                    // check 8 neighboring cells according to rules
                        if ( grid[row-1]  [col] == ALIVE ) numAlive++;                  // 2,3
                        if ( grid[row-1]  [col-1] == ALIVE ) numAlive++;                // 2,2
                        if ( grid[row][col-1] == ALIVE ) numAlive++;                    // 3,2
                        if ( grid[row-1][0] == ALIVE ) numAlive++;                      // 2,0
                        if ( grid[row][0] == ALIVE ) numAlive++;                        // 3,0
                        if ( grid[0][col-1] == ALIVE ) numAlive++;                      // 0,2
                        if ( grid[0][col] == ALIVE ) numAlive++;                        // 0,3
                        if ( grid[0][0] == ALIVE ) numAlive++;                          // 0,0
                        //return numAlive;
 
                } else { //Cells between 3,0 and 3,3 was clicked
                       
                            // check 8 neighboring cells according to rules
                            if (grid[row][col-1] == ALIVE) numAlive++;
                            if (grid[row-1][col-1] == ALIVE) numAlive++;
                            if (grid[row-1][col] == ALIVE) numAlive++;
                            if (grid[row-1][col+1] == ALIVE) numAlive++;
                            if (grid[row][col+1] == ALIVE) numAlive++;
                            if (grid[0][col-1] == ALIVE) numAlive++;
                            if (grid[0][col] == ALIVE) numAlive++;
                            if (grid[0][col+1] == ALIVE) numAlive++;

                }

            } else if (col==(numOfCols-1)) { // Search Cells Vertically  from 0,3 to 3,3 going down 
                if (row==0) {
                    // This cell is already cover 3,0
                } else if (row==(numOfRows-1) ) { 
                    // This cell is already covered Cell 3,3
                } else { //Cells between 0,3 and 3,3
                       
                        // check 8 neighboring cells according to rules
                        if (grid[row-1][col] == ALIVE) numAlive++;
                        if (grid[row-1][col-1] == ALIVE) numAlive++;
                        if (grid[row][col-1] == ALIVE) numAlive++;
                        if (grid[row+1][col-1] == ALIVE) numAlive++;
                        if (grid[row+1][col] == ALIVE) numAlive++;
                        if (grid[row-1][0] == ALIVE) numAlive++;
                        if (grid[row][0] == ALIVE) numAlive++;
                        if (grid[row+1][0] == ALIVE) numAlive++;
            }
                
        }
    } else { // Check if clicked cell is in center (not in the periphery)
            int rowLen = (row + 2);
            int colLen = (col + 2);
          
            for (int r=row-1; r<rowLen; r++ ){
                for(int c=col-1; c<colLen; c++) {
                    if ( (r==row) && (c==col) ) {
                     
                    } else {

                        if (grid[r][c] == ALIVE) { 
                            numAlive++;
                          
                        } 
                    }
                    
                    
                }
                
            }
        }
        
        return numAlive; // update this line, provided so that code compiles
    
    }
      /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid() {
        // create new board containing new generation 
        // WRITE YOUR CODE HERE
        int r = grid.length;
        int c = grid[0].length;
        boolean[][] newboard = new boolean[r][c];
        for (int i = 0; i < grid.length; i++){
            for( int j = 0; j < grid[0].length; j++){
                if( (grid[i][j]) && numOfAliveNeighbors(i, j) == 2 || numOfAliveNeighbors(i, j) == 3){
                    newboard[i][j] = ALIVE;
                } else {
                    if ((!grid[i][j]) && numOfAliveNeighbors(i, j) == 3){
                        newboard[i][j] = ALIVE;
                    }
                }

                }

            }

        
        
        return newboard;// update this line, provided so that code compiles
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {
       grid = computeNewGrid();
       int total = 0;
       for (int r = 0; r < grid.length; r++){
           for (int c = 0; c < grid[0].length; c++){
               if (grid[r][c]) total++;

           }
       }
       totalAliveCells = total;

        // WRITE YOUR CODE HERE
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {
        for (int i = 0; i < n; i++){
            nextGeneration();
        }


        // WRITE YOUR CODE HERE
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {
        // WRITE YOUR CODE HERE
        WeightedQuickUnionUF wuf = new WeightedQuickUnionUF(grid.length, grid[0].length);
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                if (grid[r][c]){
                    int numAliveNeighbors = numOfAliveNeighbors(r, c);
                    if (numAliveNeighbors > 0){
                        int left = c-1;
                        int right = c+1;
                        int up = r-1;
                        int down = r+1;

                        if (left < 0)left = grid[0].length - 1;
                        if (right > (grid[0].length-1)) right =0;
                        if (up < 0) up = grid.length - 1;
                        if (down > (grid.length -1)) down = 0;

                        if (grid[up][right]) wuf.union(r,c,up,right);
                        if (grid[down][left]) wuf.union(r,c,down,left);
                        if(grid[down][c]) wuf.union(r,c,down,c);
                        if(grid[down][right]) wuf.union(r,c,down,right);
                        if(grid[r][left]) wuf.union(r,c,r,left);
                        if(grid[r][right]) wuf.union (r,c,r,right);
                        if(grid[up][left]) wuf.union (r,c,up,left);
                        if(grid[up][c]) wuf.union(r,c,up,c);
                       
                    }
                } else{
                    wuf.union(r,c,0,0);
                }
                
               }
           }
           for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                if (wuf.find(r,c) != 1){
                if (!list.contains(wuf.find(r, c))) list.add(wuf.find(r, c));
                }
            }
        }

      
          return list.size();
   
    }
}

      