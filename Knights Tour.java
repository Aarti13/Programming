import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        
        int[][] chess = new int[n][n];
        printKnightsTour(chess , r , c, 1 );
    }
    
    
    
    public static int[][] dir ={ {-2,1}, {-1,2} , {1,2} , {2,1}  , {2,-1} ,{1,-2}, {-1, -2} , {-2 , -1} };
    public static void printKnightsTour(int[][] chess, int r, int c, int upcomingMove) {
        
        if( upcomingMove == chess.length * chess.length ) 
        {
            chess[r][c]=upcomingMove;
            displayBoard(chess);
            chess[r][c]=0;
            return;
        }
        
        chess[r][c]=upcomingMove;
        for( int i=0 ; i<dir.length ; i++){
            int x = dir[i][0] + r;
            int y = dir[i][1] + c;
            if( x >= 0 && x<chess.length && y>=0 && y<chess.length && chess[x][y]==0 ){
                printKnightsTour(chess , x , y , upcomingMove+1 );
            }
           
        }
        chess[r][c]=0;
    }

    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}