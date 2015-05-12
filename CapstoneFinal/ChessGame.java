import java.util.*;
public class ChessGame {
    private static String chessBoard[][]=
    {{"r","n","b","q","k","b","n","r"},
     {"p","p","p","p","p","p","p","p"},
     {" "," "," "," "," "," "," "," "},
     {" "," "," "," "," "," "," "," "},
     {" "," "," "," "," "," "," "," "},
     {" "," "," "," "," "," "," "," "},
     {"P","P","P","P","P","P","P","P"},
     {"R","N","B","Q","K","B","N","R"}};
     private static int kingPositionW = 60;
     private static int kingPositionB = 4;
    public static void main(String[] args) 
    {
        while(possibleMoves().length() != 0)
        {
            computerMove();
            humanMove();
        }
        System.out.println("");
        System.out.println("Checkmate");
    }
    public static void computerMove()
    {
        double r = Math.random();
        int number = (int)(r*possibleMoves().length());
        number = number/5;
        number = number*5;
        makeMove(Integer.parseInt(possibleMoves().substring(number, number + 4)));
    }
    public static void humanMove()
    {
        Scanner s = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("Enter Move: ");
        int move = s.nextInt();
        makeMove(move);
    }
     public static void makeMove(int move) 
     {
        int start = move/100;
        int end = move%100;
        int starta = start/10;
        int startb = start%10;
        int enda = end/10;
        int endb = end%10;
        chessBoard[enda][endb] = chessBoard[starta][startb];
        chessBoard[starta][startb] = " ";
        System.out.println(" ");
        System.out.println(" ");
        for(int i = 0; i < 8; i++)
        {
            System.out.println(" ");
            for(int j = 0; j < 8; j++)
            {
                System.out.print(chessBoard[i][j]);
            }
        }
       
     }
    public static String possibleMoves() 
    {
        String list="";
        for (int i=0; i<64; i++) 
        {
            if (chessBoard[i/8][i%8].equals("P"))
            {
                list+=possiblePawnMoves(i);
            }
            if (chessBoard[i/8][i%8].equals("N"))
            {
                list+=possibleKnightMoves(i);
            }
            if (chessBoard[i/8][i%8].equals("B"))
            {
                list+=possibleBishopMoves(i);
            }
            if (chessBoard[i/8][i%8].equals("Q"))
            {
                list+=possibleQueenMoves(i);
            }
            if (chessBoard[i/8][i%8].equals("K"))
            {
                list+=possibleKingMoves(i);
            }
            if (chessBoard[i/8][i%8].equals("R"))
            {
                list+=possibleRookMoves(i);
            }
        }
        return list;
    }
    public static String possiblePawnMoves(int i) 
    {
        String list="";
        int m=i/8;
        int n=i%8;
        try{
        for (int j=-1; j<=1; j+=2) 
        {
            if (Character.isLowerCase(chessBoard[m-1][n+j].charAt(0)) && i>=16) 
            {
                chessBoard[m][n]=" ";
                String temp = chessBoard[m-1][n+j];
                chessBoard[m-1][n+j]="P";
                if (isCheck() == false) 
                {
                    list=list+m+n+(m-1)+(n+j)+" ";
                }
                chessBoard[m][n]="P";
                chessBoard[m-1][n+j] = temp;
            }
            if (Character.isLowerCase(chessBoard[m-1][n+j].charAt(0)) && i<16) 
            {
                String[] temp={"Q","R","B","K"};
                for (int k=0; k<4; k++) {
                    chessBoard[m][n]=" ";
                    chessBoard[m-1][n+j]=temp[k];
                    if (isCheck() == false) 
                    {
                        list=list+n+(n+j)+temp[k]+"P";
                    }
                    chessBoard[m][n]="P";
                }
            }
        }
    } catch(Exception e){}
    try{
        if (" ".equals(chessBoard[m-1][n]) && i>=16) 
        {
            chessBoard[m][n]=" ";
            String temp = chessBoard[m-1][n];
            chessBoard[m-1][n]="P";
            if (isCheck() == false) 
            {
                list=list+m+n+(m-1)+n+" ";
            }
            chessBoard[m][n]="P";
            chessBoard[m-1][n] = temp;
        }
    }catch(Exception e){}
    try{
        if (" ".equals(chessBoard[m-1][n]) && i<16) 
        {
            String[] temp={"Q","R","B","K"};
            for (int k=0; k<4; k++) 
            {
                chessBoard[m][n]=" ";
                chessBoard[m-1][n]=temp[k];
                if (isCheck() == false) 
                {
                    list=list+n+n+temp[k]+"P";
                }
                chessBoard[m][n]="P";
            }
        }
    } catch(Exception e){}
    try{
        if(" ".equals(chessBoard[m-1][n]) && " ".equals(chessBoard[m-2][n]) && i>=48) 
        {
            chessBoard[m][n]=" ";
            String temp = chessBoard[m-2][n];
            chessBoard[m-2][n]="P";
            if (isCheck() == false) 
            {
                list=list+m+n+(m-2)+n+" ";
            }
            chessBoard[m][n]="P";
            chessBoard[m-2][n] = temp;
        }
    } catch(Exception e){}

    return list;
    }
    public static String possibleRookMoves(int i) 
    {
        String list="";
        int m=i/8;
        int n=i%8;
        int counter=1;
        try{
        for (int j=-1; j<=1; j+=2) 
        {
            while(" ".equals(chessBoard[m][n+counter*j]))
            {
                chessBoard[m][n]=" ";
                String temp = chessBoard[m][n+counter*j];
                chessBoard[m][n+counter*j]="R";
                if (isCheck() == false) 
                {
                    list=list+m+n+m+(n+counter*j)+" ";
                }
                chessBoard[m][n]="R";
                chessBoard[m][n+counter*j]=temp;
                counter++;
            }
            if (Character.isLowerCase(chessBoard[m][n+counter*j].charAt(0))) 
            {
                chessBoard[m][n]=" ";
                String temp = chessBoard[m][n+counter*j];
                chessBoard[m][n+counter*j]="R";
                if (isCheck() == false) 
                {
                    list=list+m+n+m+(n+counter*j)+" ";
                }
                chessBoard[m][n]="R";
                chessBoard[m][n+counter*j]=temp;
            }
        }
    } catch(Exception e){}
        counter=1;
        try{
        for (int j=-1; j<=1; j+=2) 
        {
            while(" ".equals(chessBoard[m+counter*j][n]))
            {
                chessBoard[m][n]=" ";
                String temp = chessBoard[m+counter*j][n];
                chessBoard[m+counter*j][n]="R";
                if (isCheck() == false) 
                {
                    list=list+m+n+(m+counter*j)+n+" ";
                }
                chessBoard[m][n]="R";
                chessBoard[m+counter*j][n]= temp;
                counter++;
            }
            if (Character.isLowerCase(chessBoard[m+counter*j][n].charAt(0))) 
            {
                chessBoard[m][n]=" ";
                String temp = chessBoard[m+counter*j][n];
                chessBoard[m+counter*j][n]="R";
                if (isCheck() == false) 
                {
                    list=list+m+n+(m+counter*j)+n+" ";
                }
                chessBoard[m][n]="R";
                chessBoard[m+counter*j][n]=temp;
            }
        }
    } catch(Exception e){}
        return list;
    }

    public static String possibleKnightMoves(int i) 
    {
        String list="";
        int m=i/8;
        int n=i%8;
        for (int j=-1; j<=1; j+=2) 
        {
            for (int k=-1; k<=1; k+=2)
            {
            try{
                if (Character.isLowerCase(chessBoard[m+j][n+k*2].charAt(0)) || " ".equals(chessBoard[m+j][n+k*2])) 
                {
                    chessBoard[m][n]=" ";
                    String temp = chessBoard[m+j][n+k*2];
                    chessBoard[m+j][n+k*2] = "N";
                    if (isCheck() == false) 
                    {
                        list=list+m+n+(m+j)+(n+k*2)+" ";
                    }
                    chessBoard[m][n]="N";
                    chessBoard[m+j][n+k*2]=temp;
                }
            } catch(Exception e) {}
            try{
                if (Character.isLowerCase(chessBoard[m+j*2][n+k].charAt(0)) || " ".equals(chessBoard[m+j*2][n+k])) 
                {
                    chessBoard[m][n]=" ";
                    String temp = chessBoard[m+j*2][n+k];
                    chessBoard[m+j*2][n+k]="N";
                    if (isCheck() == false) 
                    {
                        list=list+m+n+(m+j*2)+(n+k)+" ";
                    }
                    chessBoard[m][n]="N";
                    chessBoard[m+j*2][n+k]=temp;
                } 
            } catch (Exception e) {}
        }
        
    } 
        return list;
    }
    public static String possibleBishopMoves(int i) 
    {
        String list="";
        int m=i/8; 
        int n=i%8;
        int counter=1;
        try{
        for (int j=-1; j<=1; j+=2) 
        {
            for (int k=-1; k<=1; k+=2) 
            {
                while(" ".equals(chessBoard[m+counter*j][n+counter*k]))
                {
                    chessBoard[m][n]=" ";
                    if (isCheck() == false) 
                    {
                        list=list+m+n+(m+counter*j)+(n+counter*k)+" ";
                    }
                    chessBoard[m][n]="B";
                    counter++;
                }
                if (Character.isLowerCase(chessBoard[m+counter*j][n+counter*k].charAt(0))) 
                {
                    chessBoard[m][n]=" ";
                    String temp = chessBoard[m+counter*j][n+counter*k];
                    chessBoard[m+counter*j][n+counter*k]="B";
                    if (isCheck() == false) 
                    {
                        list=list+m+n+(m+counter*j)+(n+counter*k);
                    }
                    chessBoard[m][n]="B";
                    chessBoard[m+counter*j][n+counter*j]=temp;
                }
                counter=1;
            }
        }
    }catch(Exception e){}
        return list;
    }
    public static String possibleQueenMoves(int i) 
    {
        String list="";
        int m=i/8;
        int n=i%8;
        int counter=1;
        try{
        for (int j=-1; j<=1; j++) 
        {
            for (int k=-1; k<=1; k++) 
            {
                if (j!=0 || k!=0) 
                {
                    while(" ".equals(chessBoard[m+counter*j][n+counter*k]))
                    {
                        chessBoard[m][n]=" ";
                        String temp = chessBoard[m+counter*j][n+counter*j];
                        chessBoard[m+counter*j][n+counter*k]="Q";
                        if (isCheck() == false) 
                        {
                            list=list+m+n+(m+counter*j)+(n+counter*k)+" ";
                        }
                        chessBoard[m][n]="Q";
                        chessBoard[m+counter*j][n+counter*k]=temp;
                        counter++;
                    }
                    if (Character.isLowerCase(chessBoard[m+counter*j][n+counter*k].charAt(0))) 
                    {
                        chessBoard[m][n]=" ";
                        String temp = chessBoard[m+counter*j][n+counter*k];
                        chessBoard[m+counter*j][n+counter*k]="Q";
                        if (isCheck() == false) 
                        {
                            list=list+m+n+(m+counter*j)+(n+counter*k)+" ";
                        }
                        chessBoard[m][n]="Q";
                        chessBoard[m+counter*j][n+counter*k]=temp;
                    }
                    counter=1;
                }
            }
        }
    } catch(Exception e){}
        return list;
    }
    public static String possibleKingMoves(int i) 
    {
        String list="";
        int m=i/8;
        int n=i%8;
        try{
        for (int j=0; j<9; j++) 
        {
            if (j!=4) 
            {
                if (Character.isLowerCase(chessBoard[m-1+j/3][n-1+j%3].charAt(0)) || " ".equals(chessBoard[m-1+j/3][n-1+j%3])) 
                {
                    chessBoard[m][n]=" ";
                    chessBoard[m-1+j/3][n-1+j%3]="K";
                    if (isCheck() == false) 
                    {
                        list=list+m+n+(m-1+j/3)+(n-1+j%3)+" ";
                    }
                    chessBoard[m][n]="K";
                    chessBoard[m-1+j/3][n-1+j%3]=" ";
                }
            }
        }
    } catch(Exception e){}
        return list;
    }
    public static boolean isCheck()
    {
       int temp=1;
        for (int i=-1; i<=1; i+=2)
        {
            for (int j=-1; j<=1; j+=2) 
            {
                try {
                    while(" ".equals(chessBoard[kingPositionW/8+temp*i][kingPositionW%8+temp*j])) 
                    {
                        temp++;
                    }
                    if ("b".equals(chessBoard[kingPositionW/8+temp*i][kingPositionW%8+temp*j]) ||
                            "q".equals(chessBoard[kingPositionW/8+temp*i][kingPositionW%8+temp*j])) 
                    {
                        return true;
                    }
                } catch (Exception e) {}
                temp=1;
            }
        }
        for (int i=-1; i<=1; i+=2) {
            try {
                while(" ".equals(chessBoard[kingPositionW/8][kingPositionW%8+temp*i])) 
                {
                    temp++;
                }    
                if ("r".equals(chessBoard[kingPositionW/8][kingPositionW%8+temp*i]) ||
                        "q".equals(chessBoard[kingPositionW/8][kingPositionW%8+temp*i])) 
                {
                    return true;
                }
            } catch (Exception e) {}
            temp=1;
            try {
                while(" ".equals(chessBoard[kingPositionW/8+temp*i][kingPositionW%8])) 
                {
                    temp++;
                }
                if ("r".equals(chessBoard[kingPositionW/8+temp*i][kingPositionW%8]) ||
                        "q".equals(chessBoard[kingPositionW/8+temp*i][kingPositionW%8])) 
                {
                    return true;
                }
            } catch (Exception e) {}
            temp=1;
        }
        for (int i=-1; i<=1; i+=2) 
        {
            for (int j=-1; j<=1; j+=2) 
            {
                try {
                    if ("k".equals(chessBoard[kingPositionW/8+i][kingPositionW%8+j*2])) 
                    {
                        return true;
                    }
                } catch (Exception e) {}
                try {
                    if ("k".equals(chessBoard[kingPositionW/8+i*2][kingPositionW%8+j])) 
                    {
                        return true;
                    }
                } catch (Exception e) {}
            }
        }
        if (kingPositionW>=16) 
        {
            try {
                if ("p".equals(chessBoard[kingPositionW/80-1][kingPositionW%8-1])) 
                {
                    return true;
                }
            } catch (Exception e) {}
            try {
                if ("p".equals(chessBoard[kingPositionW/80-1][kingPositionW%8+1])) 
                {
                    return true;
                }
            } catch (Exception e) {}
            for (int i=-1; i<=1; i++) 
            {
                for (int j=-1; j<=1; j++) 
                {
                    if (i!=0 || j!=0) 
                    {
                        try {
                            if ("a".equals(chessBoard[kingPositionW/8+i][kingPositionW%8+j])) 
                            {
                                return false;
                            }
                        } catch (Exception e) {}
                    }
                }
            }
        }
        return false;
    }
}