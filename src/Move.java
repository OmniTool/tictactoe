import java.util.*;

/**
 * Created by Neboola on 22.10.2015.
 */
public class Move {

    public static int counter;

    public Move preMove;

    public Index index;
    public Cell cell;

    public Board nextBoard;
    public ArrayList<Move> movesList;

    public int priority;

    Move(){

        this.priority = -9;

    }

    Move(Move move){

        preMove = move.preMove;

        index = new Index(move.index.horizontal, move.index.vertical);
        cell = move.cell;

        nextBoard = move.nextBoard;
        movesList = move.movesList;

        priority = move.priority;

    }

    Move(Index index, Cell cell, Move preMove){
        this.index = index;
        this.cell = cell;
        this.preMove = preMove;
        this.priority = 9;
    }

    public static Move treeRoot(){
        Move root = new Move();
        root.cell = Cell.oCell;
        root.nextBoard = new Board();
        root.generateTree();
        root.generatePriorities();
        return root;
    }

    public Move getRoot(int level){
        Move root = new Move();
        root.cell = cell;
        root.nextBoard = nextBoard;
        root.generateNextMoves(level);
        root.generatePriorities();
        return root;
    }

    public void generateNextMoves(int level){

        if(nextBoard.isFull()) return;
        if(level == 0) return;
        level--;

            Cell steper;
            if(cell.filling == Cell.oCell.filling){
                steper = Cell.xCell;
            }
            else{
                steper = Cell.oCell;
            }
            //System.out.println("Steper is: " + steper.filling);

            movesList = new ArrayList<Move>();

            HashMap<Index, Cell> map = nextBoard.map;


            int j = 1;
            for(Map.Entry<Index, Cell> field : map.entrySet()){
                Index indexKey = field.getKey();

                Cell cellValue = field.getValue();

                //================

                if(cellValue == Cell.emptyCell){

                    //System.out.println(indexKey.horizontal + "," + indexKey.vertical + " : is empty");


                    Move newMove = new Move(indexKey, steper, this);
                    newMove.nextBoard = new Board(nextBoard, newMove);
                    newMove.nextBoard.checkWin();
                    newMove.nextBoard.boardLine = nextBoard.boardLine + "." + j;
                    movesList.add(newMove);

                    j++;
                    counter++;
                    //System.out.println("New move added: " + nextBoard.boardLine + "." + k + "    Move# " + counter);

                }
/*
            else{
                //System.out.println(indexKey.horizontal + "," + indexKey.vertical + " : " + cellValue.filling);
            }
*/
                //================

            }



            for(Move nextMove : movesList){

/*
            System.out.println();
            System.out.println(nextMove.cell.filling + " steps to " + nextMove.index.horizontal + "," + nextMove.index.vertical );
            System.out.println("New board created. Board Line is: " + nextMove.nextBoard.boardLine);
            System.out.println("Priority is " + priority);

            nextMove.nextBoard.printBoardPG();
*/

                if(nextMove.nextBoard.whoWin == Cell.emptyCell.filling) nextMove.generateNextMoves(level);






            }





    }

    public void generateTree(){
        generateNextMoves(9); // full tree if level > 8

    }

    public void tryStepTreeDown(){



        //System.out.println("=================== ST DOWN");

        if(nextBoard != null){

            //System.out.println("Board Line is: " + nextBoard.boardLine);


            if (nextBoard.whoWin != Cell.emptyCell.filling) {
                //System.out.println("=================== ST UP");
                this.stepTreeUp(nextBoard.whoWin, 9);


                //return;

            }
            else if(nextBoard.isFull()) {
                //System.out.println("=================== ST UP");
                this.stepTreeUp(nextBoard.whoWin, 0);


                //return;

            }

        }



        if(movesList == null){

            //System.out.println("movesList is empty. StepTreeDown stopped.");
            //System.out.println();
            //System.out.println();


            return;

        }
        else{

            for (Move nextMove : movesList) {

                nextMove.tryStepTreeDown();

            }
        }

    }

    public void stepTreeUp(char winner, int prior){

        //System.out.println("============= inside ST Up");






            if(winner != cell.filling){
                if(-prior < priority) priority = -prior;


            }
            else {
                if (preMove != null)
                    if (preMove.preMove != null)
                if (preMove.preMove.nextBoard.whoWin == cell.filling) priority = prior;
                if ((nextBoard.whoWin == cell.filling)) priority = prior;
                else
                if(prior < priority) priority = prior;
            }










        if (preMove != null) preMove.stepTreeUp(winner, prior - 1);
        //else System.out.println("preMove is empty");



    }

    public void printTree() {

        System.out.println("Priority is " + priority);

        if(cell != null){
            System.out.print(cell.filling + " steps to ");
        }
        else{
            System.out.println("cell is empty");
        }

        if(index != null){
            System.out.println(index.horizontal + "," + index.vertical );

        }
        else{
            System.out.println("index is empty");
        }

        if(nextBoard != null){

            System.out.println("Board Line is: " + nextBoard.boardLine);
            nextBoard.printBoardPG();

            if (nextBoard.whoWin != Cell.emptyCell.filling) {
                System.out.println(nextBoard.whoWin + " WIN! ========================");
                System.out.println();
                System.out.println();

                return;
            }
            else if(nextBoard.isFull()) {
                System.out.println("DRAW! ========================");
                System.out.println();
                System.out.println();

                return;
            }

        }
        else{
            System.out.println("nextBoard is empty");
        }









        //System.out.println("printTree started.");

        if(movesList == null){



            System.out.println("movesList is empty. printTree stopped.");
            System.out.println();
            System.out.println();

            Tictactoe.sec(10);


            return;

        }
        else{

            for (Move nextMove : movesList) {

                    nextMove.printTree();

            }
        }


    }

    public void generatePriorities(){
        this.tryStepTreeDown();
    }


}