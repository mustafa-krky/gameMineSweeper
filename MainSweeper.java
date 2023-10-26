import java.util.Random;
import java.util.Scanner;

public class MainSweeper {
    private int row;
    private int col;
    private int counter;

    public MainSweeper(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void run(){
        Scanner inp = new Scanner(System.in);
        String[][] cBoard = new String[this.row][this.col];
        String[][] mBoard = new String[this.row][this.col];

        int mPiece = (this.row * this.col) / 4;

        int[] mIndexes1 = new int[mPiece];
        int[] mIndexes2 = new int[mPiece];

        Random rNumber = new Random();

        boolean onGame = true;
        int cWin = 0;

        for(int i = 0; i < cBoard.length; i++){

            for(int j = 0; j < cBoard[i].length; j++){

                cBoard[i][j] = " - ";
            }
        }

        for(int m = 0; m < mIndexes1.length; m++){
            int number = rNumber.nextInt(this.row);
            mIndexes1[m] = number;
        }

        for(int m = 0; m < mIndexes2.length; m++){
            int number = rNumber.nextInt(this.row);
            mIndexes2[m] = number;
        }


        for(int x = 0; x < mBoard.length; x++){
            for(int y = 0; y < mBoard[x].length; y++){
                mBoard[x][y] = " - ";
            }
        }

        for(int h = 0; h < mIndexes1.length; h++){
            mBoard[mIndexes1[h]][mIndexes2[h]] = " * ";
        }

        System.out.print("Oyuncu adınız: ");
        String name = inp.nextLine();

        System.out.println("=========================");
        System.out.println(name+", Mayın Tarlası Oyununa Hoşgeldin !");

        while (onGame){

            counter = 0;

            for(String[] row: cBoard){
                for(String col: row){
                    System.out.print(col);
                }
                System.out.println();
            }

            System.out.print("Satır giriniz: ");
            int row = inp.nextInt();
            --row;
            System.out.print("Sütun giriniz: ");
            int col = inp.nextInt();
            --col;

            if((row + 1 > mBoard.length)){
                System.out.println("Girilen satır değeri "+(mBoard.length - 1)+"'den büyük olamaz !");
                continue;
            }

            if((col + 1 > mBoard[row].length)){
                System.out.println("Girilen sütun değeri "+(mBoard[row].length - 1)+"'den büyük olamaz !");
                continue;
            }

            if(mBoard[row][col] == " * "){
                System.out.println("Mayına bastınız, oyunu kaybettiniz !");
                onGame = false;
            }

            controller(mBoard, row, col);

            cBoard[row][col] = " " + (counter) + " ";

            cWin++;

            if(cWin == (this.row*this.col) - (mPiece)){
                System.out.println("Tebrikler, oyunu kazandın !");
                onGame = false;
            }
        }

        System.out.println("=========================");
        System.out.println("Mayınların Konumu:");

        for(String[] row: mBoard){
            for(String col: row){
                System.out.print(col);
            }
            System.out.println();
        }
    }

    public void controller(String[][] mBoard, int row, int col){
        int x = row - 1;
        int y = row + 1;
        int z = col - 1;
        int t = col + 1;

        if(x < 0){
            x++;
        }

        if(z < 0){
            z++;
        }

        if(y > mBoard.length - 1){
            y--;
        }

        if(t > mBoard[row].length - 1){
            t--;
        }


        if(mBoard[x][z] == " * "){
            counter++;
        }


        if(mBoard[x][t] == " * "){
            counter++;
        }

        if(x != row && y != row){


            if(mBoard[row][z] == " * "){
                counter++;
            }


            if(mBoard[row][t] == " * "){
                counter++;
            }
        }

        if(z != col && t != col){


            if(mBoard[x][col] == " * "){
                counter++;
            }
        }

        if(y != row && z != col && t != col){

            if(mBoard[y][col] == " * "){
                counter++;
            }
        }

        if(mBoard[y][z] == " * "){
            counter++;
        }

        if(mBoard[y][t] == " * "){
            counter++;
        }
    }
}
