import java.awt.*;

public class GameOfLife {
    public static void main(String[]args){
        int numCells = Integer.parseInt(args[0]);
        int numCopies = Integer.parseInt(args[1]);
        if(args.length!=2||numCells < 1||numCopies < 1){
            System.err.print("The program takes in exactly two positive numbers");
            System.exit(1);
        }
        else{
            //Space for each cell
            int cellSize = 1;
            //Set the canvas size
            SetUpStdDraw(numCells,cellSize);
            int[][]rows = new int[numCells][numCells];
            //set array element values
            rows = InitialValues(numCells,numCopies);
            StdDraw.clear();
            drawRows(rows,cellSize);
            StdDraw.show();
            StdDraw.pause(120);

            //Run a loop that checks the rules for each iteration of i and j like rule 110
            while (true){
                rows = applyrule110(rows);
                StdDraw.clear();
                drawRows(rows,cellSize);
                StdDraw.show();
                StdDraw.pause(120);


            }


        }
    }
    public static void SetUpStdDraw(int numCells, int cellSize){
        StdDraw.enableDoubleBuffering();
        //StdDraw.setCanvasSize(numCells*cellSize,numCells*cellSize);
        StdDraw.setScale(0,(numCells+1)*cellSize);
    }
    //Code to assign values to all the parts of the array
    public static int[][] InitialValues(int numCells,int numCopies) {
        int[][] rows = new int[numCells][numCells];
        for (int nCopies = 0; nCopies < numCopies; nCopies++) {
            int randomNumber1I = (int) (Math.random() * numCells);
            int randomNumber1J = (int) (Math.random() * numCells);

            rows[randomNumber1I][randomNumber1J] = 1;
            rows[randomNumber1I][(rows.length + randomNumber1J - 1) % rows.length] = 1;
            rows[randomNumber1I][(randomNumber1J + 1) % rows.length] = 1;

        }
        //Code for SpaceShip Glider.
        for (int nCopies = 0; nCopies < numCopies; nCopies++) {
            int randomNumberI = (int) (Math.random() * numCells);
            int randomNumberJ = (int) (Math.random() * numCells);
            int i = randomNumberI;
            int j = randomNumberI;

            rows[i][(j + 1) % rows.length] = 1;
            rows[(rows.length + i - 1) % rows.length][(j + 1)%rows.length] = 1;
            rows[(i + 1) % rows.length][(j + 1) % rows.length] = 1;
            rows[(i + 1) % rows.length][j] = 1;
            rows[i][(rows.length + j - 1) % rows.length] = 1;

        }
        //Code for Spaceship Toad
        for (int nCopies = 0; nCopies < numCopies; nCopies++) {
            int randomNumberI = (int) (Math.random() * numCells);
            int randomNumberJ = (int) (Math.random() * numCells);
            int i = randomNumberI;
            int j = randomNumberJ;
            rows[i][j] = 1;
            rows[i][(j + 1) % rows.length] = 1;//
            rows[i][(j + 2) % rows.length] = 1;//
            rows[(i + 1) % rows.length][(j + 1) % rows.length] = 1;//
            rows[(i + 1) % rows.length][j] = 1;//
            rows[(i + 1) % rows.length][(rows.length + j - 1) % rows.length] = 1;
        }
        return rows;
    }

        public static int[][] spaceShipValues ( int numCells, int numCopies){
            int[][] rows = new int[numCells][numCells];
            for (int nCopies = 0; nCopies < numCopies; nCopies++) {
                int randomNumberI = (int) (Math.random() * numCells);
                int randomNumberJ = (int) (Math.random() * numCells);
                for (int i = 0; i < numCells; i++) {
                    for (int j = 0; j < numCells; j++) {
                        if (i == randomNumberI && j == randomNumberJ) {
                            rows[i][(j + 1) % rows.length] = 1;
                            rows[(i + 1) % rows.length][(j + 1) % rows.length] = 1;
                            rows[(i + 1) % rows.length][j] = 1;
                            rows[i][(rows.length + j - 1) % rows.length] = 1;

                        }
                    }
                }
            }
            return rows;
        }

    //What to draw when the value of the array element is 0 and what to draw when it is 1.
    public static void drawRows(int[][]rows, int cellSize){
        double halfLength = cellSize/2;
        for(int i = 0; i < rows.length; i ++){
            for( int j = 0; j < rows.length; j ++){
                if(rows[i][j]==1){
                    StdDraw.setPenColor(Color.RED);
                    //StdDraw.filledSquare(i*cellSize+halfLength,(rows.length - i)*cellSize - halfLength, halfLength);
                    StdDraw.filledSquare(i+0.5,j+0.5,.5);
                }
                else if(rows[i][j] == 0){
                    StdDraw.setPenColor(Color.ORANGE);
                   // StdDraw.square(i * cellSize + halfLength,(rows.length - i)*cellSize - halfLength, halfLength);
                    StdDraw.square(i+0.5,j+0.5,.5);
                }
            }
        }

    }
//Code to change keep track of the future state of the cell. That is, the state in the next iteration.
    public static int[][] applyrule110(int[][]rows) {
        int[][] willberows = new int[rows.length][rows.length];
        //i new int[rows.length][rows.length];
        for (int i = 0; i < rows.length; i++){
            for (int j = 0; j < rows.length; j++) {
                //NOTE: Most part of this code is just a repetition. Only did that to ensure I don't make a mistake
                int rightNeighborJindex = (j + 1) % rows.length;
                int leftNeighborJindex = (rows.length + j - 1) % rows.length;
                int upperNeighborIindex = (rows.length + i - 1) % rows.length;
                int lowerNeighborIindex = (i + 1) % rows.length;
                int upperRightNeighborIindex = (rows.length + i - 1) % rows.length;
                int upperRightNeighborJindex = (j + 1) % rows.length;
                int upperLeftNeighborIindex = (rows.length + i - 1) % rows.length;
                int upperLeftNeighborJindex = (rows.length + j - 1) % rows.length;
                int lowerLeftNeighborIindex = (i + 1) % rows.length;
                int lowerLeftNeighborJindex = (rows.length + j - 1) % rows.length;
                int lowerRightNeighborIindex = (i + 1) % rows.length;
                int lowerRightNeighborJindex = (j + 1) % rows.length;
                int rightNeighborState = rows[i][rightNeighborJindex];
                int leftNeighborState = rows[i][leftNeighborJindex];
                int upperNeighborState = rows[upperNeighborIindex][j];
                int lowerNeighborState = rows[lowerNeighborIindex][j];
                int upperRightNeighborState = rows[upperRightNeighborIindex][upperRightNeighborJindex];
                int upperLeftNeighborState = rows[upperLeftNeighborIindex][upperLeftNeighborJindex];
                int lowerRightNeighborState = rows[lowerRightNeighborIindex][lowerRightNeighborJindex];
                int lowerLeftNeighborState = rows[lowerLeftNeighborIindex][lowerLeftNeighborJindex];
                int selfState = rows[i][j];
                int neighborHoodState = rightNeighborState + leftNeighborState + upperNeighborState + lowerNeighborState
                        + upperRightNeighborState + upperLeftNeighborState + lowerRightNeighborState + lowerLeftNeighborState;
                if (selfState == 1 && neighborHoodState < 2) {
                    willberows[i][j] = 0;
                } else if (selfState == 1 && neighborHoodState < 4) {
                    willberows[i][j] = 1;
                } else if (selfState == 1) {
                    willberows[i][j] = 0;
                } else if (selfState == 0) {
                    if (neighborHoodState == 3) {
                        willberows[i][j] = 1;
                    } else {
                        willberows[i][j] = 0;
                    }
                }


            }
        }
        rows = willberows;
        return rows;
    }


}
