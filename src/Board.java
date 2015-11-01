import java.util.HashMap;
import java.util.Map;

/**
 * Created by Neboola on 22.10.2015.
 */
public class Board {


    public String boardLine;

    public char whoWin;

    public HashMap<Index, Cell> map;


    Board() {

        whoWin = Cell.emptyCell.filling;

        map = new HashMap<Index, Cell>();
        //System.out.println("Board creation started");


        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {

                map.put(new Index(i, j), Cell.emptyCell);
                //System.out.println("Field created: " + i + "," + j);

            }
        }

        boardLine = "1";
        //System.out.println("Board creation finished. Board line is: " + boardLine);
        //System.out.println();

    }


    Board(Board prevBoard, Move move) {

        whoWin = Cell.emptyCell.filling;

        Index moveIndex = move.index;
        Cell moveCell = move.cell;
        map = new HashMap<Index, Cell>();

        for (Map.Entry<Index, Cell> field : prevBoard.map.entrySet()) {
            Index indexKey = field.getKey();
            Cell cellValue = field.getValue();


            if ((moveIndex.horizontal == indexKey.horizontal) && (moveIndex.vertical == indexKey.vertical)) {
                map.put(indexKey, moveCell);
            } else {
                map.put(indexKey, cellValue);
            }

        }

    }

    public Move obtainWinMove(Move currentMove, Board board){

        System.out.println("check obtainWinMove =================");

        Cell targetCell = currentMove.cell;

        Move blockMove = new Move(currentMove);

        int counterXO;
        Index currentIndex = new Index();

        for (int h = 1; h < 4; h++) {
            counterXO = 0;

            for (int v = 1; v < 4; v++) {
                System.out.println("h: " + h + " v: " + v);

                for(Map.Entry<Index, Cell> entry : map.entrySet()){
                    Index index = entry.getKey();

                    if((index.horizontal == h) && (index.vertical == v)){



                        if(entry.getValue() == targetCell) counterXO++;
                        else {
                            currentIndex.horizontal = h;
                            currentIndex.vertical = v;

                        }

                    }

                }

            }



            if (counterXO == 2) {
                blockMove.index = currentIndex;
                System.out.println("counter = 2. block found? current h: " + currentIndex.horizontal + " v: " + currentIndex.vertical);

                if(board.canMakeMove(blockMove)) {
                     System.out.println("block found!!! h: " + blockMove.index.horizontal + " v: " + blockMove.index.vertical);

                     return blockMove;
                 }
                else System.out.println("cant go to " + blockMove.index.horizontal + "," + blockMove.index.vertical);

            }


        }

        System.out.println();

        for (int v = 1; v < 4; v++) {
            counterXO = 0;




            for (int h = 1; h < 4; h++) {

                System.out.println("h: " + h + " v: " + v);

                for(Map.Entry<Index, Cell> entry : map.entrySet()){
                    Index index = entry.getKey();

                    if((index.horizontal == h) && (index.vertical == v)){



                        if(entry.getValue() == targetCell) counterXO++;
                        else {
                            currentIndex.horizontal = h;
                            currentIndex.vertical = v;
                        }

                    }

                }

            }

            if (counterXO == 2) {
                blockMove.index = currentIndex;
                System.out.println("counter = 2. block found? current h: " + currentIndex.horizontal + " v: " + currentIndex.vertical);

                if(board.canMakeMove(blockMove)) {
                    System.out.println("block found!!! h: " + blockMove.index.horizontal + " v: " + blockMove.index.vertical);

                    return blockMove;
                }
                else System.out.println("cant go to " + blockMove.index.horizontal + "," + blockMove.index.vertical);

            }



        }



        for (int hv = 1; hv < 4; hv++) {
            counterXO = 0;

            for(Map.Entry<Index, Cell> entry : map.entrySet()){
                Index index = entry.getKey();

                if((index.horizontal == hv) && (index.vertical == hv)){



                    if(entry.getValue() == targetCell) counterXO++;
                    else {
                        currentIndex.horizontal = hv;
                        currentIndex.vertical = hv;
                    }

                }

            }

            if (counterXO == 2) {
                blockMove.index = currentIndex;
                if(board.canMakeMove(blockMove)) return blockMove;
                else System.out.println("cant go to " + blockMove.index.horizontal + "," + blockMove.index.vertical);

            }




        }




        for (int h = 1; h < 4; h++) {
            int v = 4 - h;
            counterXO = 0;

            for(Map.Entry<Index, Cell> entry : map.entrySet()){
                Index index = entry.getKey();

                if((index.horizontal == h) && (index.vertical == v)){



                    if(entry.getValue() == targetCell) counterXO++;
                    else {
                        currentIndex.horizontal = h;
                        currentIndex.vertical = v;
                    }

                }

            }


            if (counterXO == 2) {
                blockMove.index = currentIndex;
                if(board.canMakeMove(blockMove)) return blockMove;
                else System.out.println("cant go to " + blockMove.index.horizontal + "," + blockMove.index.vertical);

            }


        }

        return null;
    }


    public Move obtainBlockMove(Move currentMove, Board board){

        System.out.println("check obtainBlockMove =================");

        Cell targetCell = currentMove.cell;
        if(currentMove.cell == Cell.oCell) {
            targetCell = Cell.xCell;
        }
        else {
            if (currentMove.cell == Cell.xCell) {
                targetCell = Cell.oCell;
            } else {
                System.out.println("Who steps???? Current steper is " + currentMove.cell.filling);
                Tictactoe.sec(10);
            }
        }

        Move blockMove = new Move(currentMove);

        int counterXO;
        Index currentIndex = new Index();

        for (int h = 1; h < 4; h++) {
            counterXO = 0;

            for (int v = 1; v < 4; v++) {
                System.out.println("h: " + h + " v: " + v);

                for(Map.Entry<Index, Cell> entry : map.entrySet()){
                    Index index = entry.getKey();

                    if((index.horizontal == h) && (index.vertical == v)){



                        if(entry.getValue() == targetCell) counterXO++;
                        else {
                            currentIndex.horizontal = h;
                            currentIndex.vertical = v;

                        }

                    }

                }

            }



            if (counterXO == 2) {
                blockMove.index = currentIndex;
                System.out.println("counter = 2. block found? current h: " + currentIndex.horizontal + " v: " + currentIndex.vertical);

                if(board.canMakeMove(blockMove)) {
                    System.out.println("block found!!! h: " + blockMove.index.horizontal + " v: " + blockMove.index.vertical);

                    return blockMove;
                }
                else System.out.println("cant go to " + blockMove.index.horizontal + "," + blockMove.index.vertical);

            }


        }

        System.out.println();

        for (int v = 1; v < 4; v++) {
            counterXO = 0;




            for (int h = 1; h < 4; h++) {

                System.out.println("h: " + h + " v: " + v);

                for(Map.Entry<Index, Cell> entry : map.entrySet()){
                    Index index = entry.getKey();

                    if((index.horizontal == h) && (index.vertical == v)){



                        if(entry.getValue() == targetCell) counterXO++;
                        else {
                            currentIndex.horizontal = h;
                            currentIndex.vertical = v;
                        }

                    }

                }

            }

            if (counterXO == 2) {
                blockMove.index = currentIndex;
                System.out.println("counter = 2. block found? current h: " + currentIndex.horizontal + " v: " + currentIndex.vertical);

                if(board.canMakeMove(blockMove)) {
                    System.out.println("block found!!! h: " + blockMove.index.horizontal + " v: " + blockMove.index.vertical);

                    return blockMove;
                }
                else System.out.println("cant go to " + blockMove.index.horizontal + "," + blockMove.index.vertical);

            }



        }



        for (int hv = 1; hv < 4; hv++) {
            counterXO = 0;

            for(Map.Entry<Index, Cell> entry : map.entrySet()){
                Index index = entry.getKey();

                if((index.horizontal == hv) && (index.vertical == hv)){



                    if(entry.getValue() == targetCell) counterXO++;
                    else {
                        currentIndex.horizontal = hv;
                        currentIndex.vertical = hv;
                    }

                }

            }

            if (counterXO == 2) {
                blockMove.index = currentIndex;
                if(board.canMakeMove(blockMove)) return blockMove;
                else System.out.println("cant go to " + blockMove.index.horizontal + "," + blockMove.index.vertical);

            }




        }




        for (int h = 1; h < 4; h++) {
            int v = 4 - h;
            counterXO = 0;

            for(Map.Entry<Index, Cell> entry : map.entrySet()){
                Index index = entry.getKey();

                if((index.horizontal == h) && (index.vertical == v)){



                    if(entry.getValue() == targetCell) counterXO++;
                    else {
                        currentIndex.horizontal = h;
                        currentIndex.vertical = v;
                    }

                }

            }


            if (counterXO == 2) {
                blockMove.index = currentIndex;
                if(board.canMakeMove(blockMove)) return blockMove;
                else System.out.println("cant go to " + blockMove.index.horizontal + "," + blockMove.index.vertical);

            }


        }

        return null;
    }


    public void checkWin() {

        int counterX;
        int counterO;





        for (int h = 1; h < 4; h++) {

             counterX = 0;
             counterO = 0;

            for (int v = 1; v < 4; v++) {

                for(Map.Entry<Index, Cell> entry : map.entrySet()){
                    Index index = entry.getKey();

                    if((index.horizontal == h) && (index.vertical == v)){
                        Cell cell = entry.getValue();

                        if(cell == Cell.oCell) counterO++;
                        if(cell == Cell.xCell) counterX++;

                    }

                }

            }

            if (counterX == 3) {
                whoWin = 'X';

            }
            if (counterO == 3) {
                whoWin = 'O';

            }

        }




        for (int v = 1; v < 4; v++) {

             counterX = 0;
             counterO = 0;

            for (int h = 1; h < 4; h++) {

                for(Map.Entry<Index, Cell> entry : map.entrySet()){
                    Index index = entry.getKey();

                    if((index.horizontal == h) && (index.vertical == v)){
                        Cell cell = entry.getValue();

                        if(cell == Cell.oCell) counterO++;
                        if(cell == Cell.xCell) counterX++;

                    }

                }

            }

            if (counterX == 3) {
                whoWin = 'X';

            }
            if (counterO == 3) {
                whoWin = 'O';

            }

        }





        for (int hv = 1; hv < 4; hv++) {
            counterX = 0;
            counterO = 0;






                for(Map.Entry<Index, Cell> entry : map.entrySet()){
                    Index index = entry.getKey();

                    if((index.horizontal == hv) && (index.vertical == hv)){
                        Cell cell = entry.getValue();

                        if(cell == Cell.oCell) counterO++;
                        if(cell == Cell.xCell) counterX++;

                    }

                }



            if (counterX == 3) {
                whoWin = 'X';

            }
            if (counterO == 3) {
                whoWin = 'O';

            }

        }



        for (int h = 1; h < 4; h++) {
            int v = 4 - h;

            counterX = 0;
            counterO = 0;





            for(Map.Entry<Index, Cell> entry : map.entrySet()){
                Index index = entry.getKey();

                if((index.horizontal == h) && (index.vertical == v)){
                    Cell cell = entry.getValue();

                    if(cell == Cell.oCell) counterO++;
                    if(cell == Cell.xCell) counterX++;

                }

            }



            if (counterX == 3) {
                whoWin = 'X';

            }
            if (counterO == 3) {
                whoWin = 'O';

            }

        }










    }




    public void printBoardPG() {




        for (int j = 3; j > 0; j--) {
            System.out.print(j + "  | ");
            for (int i = 1; i < 4; i++) {

                System.out.print(getCellFilling(i, j) + " | ");
            }
            System.out.println();
            System.out.println("   -------------");
        }
        System.out.println("     1   2   3  ");




        if (whoWin != Cell.emptyCell.filling) {
            System.out.println(whoWin + " WIN! ========================");
            System.out.println();
            System.out.println();


        }
        else if(isFull()) {
            System.out.println("DRAW! ========================");
            System.out.println();
            System.out.println();


        }


        //Tictactoe.sec(1);

    }


    public char getCellFilling(int h, int v){

        Index indexKey = new Index(h, v);

        for (Map.Entry<Index, Cell> field : map.entrySet()) {
            indexKey = field.getKey();
            //Cell cellValue = field.getValue();

            if ((indexKey.horizontal == h) && (indexKey.vertical == v)) {
                break;
            }



        }
        return map.get(indexKey).filling;


    }



    public boolean isFull(){

        boolean full = true;

        for (Map.Entry<Index, Cell> field : map.entrySet()) {
            //Index indexKey = field.getKey();
            Cell cellValue = field.getValue();

            if (cellValue == Cell.emptyCell) {
                full = false;
            }



        }

        return full;
    }


    public boolean canMakeMove(Move move){
        //Index moveIndex = move.index;
        //Cell moveCell = move.cell;


        for (Map.Entry<Index, Cell> field : map.entrySet()) {
            Index indexKey = field.getKey();
            Cell cellValue = field.getValue();

            if ((move.index.horizontal == indexKey.horizontal) && (move.index.vertical == indexKey.vertical)) {
                if(cellValue != Cell.emptyCell){
                    return false;
                }
                else{

                    return true;
                }


            }

        }


        return true;
    }

    public boolean makeMove(Move move){



        for (Map.Entry<Index, Cell> field : map.entrySet()) {
            Index indexKey = field.getKey();
            Cell cellValue = field.getValue();

            if ((move.index.horizontal == indexKey.horizontal) && (move.index.vertical == indexKey.vertical)) {
                if(cellValue != Cell.emptyCell){
                    return false;
                }
                else{
                    field.setValue(move.cell);
                    return true;
                }


            }

        }


        return true;
    }

}