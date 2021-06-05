
import java.util.*;

public class Tictactoe5 {
    int limit ;
    int depth=0;
    byte [] player1;
    byte [] player2;    
    byte [] winstreak;
    Tictactoe5 (){
        player1 = new byte[25]; 
        player2 = new byte[25]; 
        winstreak = new byte[4];
        for(int i=0;i<25;i++){
            player1[i]=0;
            player2[i]=0;
        }
    }
    byte recurse(byte [] b){
        //System.out.println("lolololol");
        byte num[] = new byte[25];
        int y = winner(b);
        if(y==1){
            return 1;    
        }
        else if(y==2){
            return -1;
        }
        else if(y==4){
            return 0;
        }
        if(depth>=limit){
            //System.out.println("depth ="+ depth);
            return 0;
        }
        int countx=0;
        int counto=0;
        for(int i=0;i<25;i++){
            if(b[i]==1){
                countx++;
            }
            else if (b[i]==2){
                counto++;
            }
        }
        
        if(countx==0){
            for(int i=0;i<25;i++){
                if(b[i]!=0){
                    num[i]=-100;
                }
                else{
                    num[i]=-1;
                }
            }
            num[12]=0;
            for(int i=0;i<25;i++){
                player1[i]=num[i];
            }
        }
        else if(countx==1&&counto==0){
            if(b[12]==0){
                for(int i=0;i<25;i++){
                    if(b[i]!=0){
                        num[i]=100;
                    }
                    else{
                        num[i]=1;
                    }
                }
                num[12]=0;
                for(int i=0;i<25;i++){
                    player2[i]=num[i];
                }
            }
            else{
                for(int i=0;i<25;i++){
                    if(b[i]!=0){
                        num[i]=100;
                    }
                    else{
                        num[i]=1;
                    }
                }
                num[6]=0;
                num[8]=0;
                num[16]=0;
                num[18]=0;
                for(int i=0;i<25;i++){
                    player2[i]=num[i];
                }
            }
        }
        else if(countx==1&&counto==1){
            if(b[12]==0){
                for(int i=0;i<25;i++){
                    if(b[i]!=0){
                        num[i]=-100;
                    }
                    else{
                        num[i]=-1;
                    }
                }
                num[12]=0;
                for(int i=0;i<25;i++){
                    player1[i]=num[i];
                }
            }
            else {
                for(int i=0;i<25;i++){
                    if(b[i]!=0){
                        num[i]=-100;
                    }
                    else{
                        num[i]=-1;
                    }
                }
                if(b[6]==2||b[18]==2){
                    num[8]=0;
                    num[16]=0;
                }
                else if(b[8]==2||b[16]==2){
                    num[6]=0;
                    num[18]=0;
                }
                else{
                    num[6]=0;
                    num[8]=0;
                    num[16]=0;
                    num[18]=0;
                }
                for(int i=0;i<25;i++){
                    player1[i]=num[i];
                }
            }
        }
        else if(countx==2&&counto==1){
            int h = findtwo(b,1);
            if(h!=99){
                for(int i=0;i<25;i++){
                    if(b[i]!=0){
                        num[i]=100;
                    }
                    else{
                        num[i]=1;
                    }
                }
                num[h]=0;
            }
            else{
                for(int i=0;i<25;i++){
                    if(b[i]!=0){
                        num[i]=100;
                    }
                    else{
                        num[i]=1;
                    }
                }
                byte [] k = new byte[25];
                for(int i=0;i<25;i++){
                    k[i]=b[i];
                }
                for(int i=0;i<25;i++){
                    if(b[i]==0){
                        k[i]=2;
                        if (findtwo(k,2)!=996&&b[i]==0){
                            num[i]=0;
                        }
                        k[i]=0;
                    }
                }
            }
            for(int i=0;i<25;i++){
                player2[i]=num[i];
            }
        }
        else if(countx==2&&counto==2){
            int h = findtwo(b,1);
            for(int i=0;i<25;i++){
                if(b[i]!=0){
                    num[i]=-100;
                }
                else{
                    num[i]=-1;
                }
            }
            if(h!=99){
                System.out.println("Found 2 for himself");
                for(int i=0;i<25;i++){
                    if(b[i]==0){
                        num[i]=0;
                    }
                }
                num[h]=1;
            }
            else{
                h = findtwo(b,2);
                if(h!=99){
                    System.out.println("found 2 for me");
                    num[h]=0;
                }
                else {
                    byte [] k = new byte[25];
                    for(int i=0;i<25;i++){
                        k[i]=b[i];
                    }
                    for(int i=0;i<25;i++){
                        if(b[i]==0){
                            k[i]=1;
                            if (findtwo(k,1)!=99&&b[i]==0){
                                num[i]=0;
                            }
                            k[i]=0;
                        }
                    }
                }
            }
            for(int i=0;i<25;i++){
                player1[i]=num[i];
            }
        }
        else{
            if(countx>counto){//player 2 turn
                byte [] d = new byte[25];
                int m=check2(b);
                if(m!=99){
                    if(depth==0){
                        for(int i=0;i<25;i++){
                            if(b[i]!=0){
                                num[i]=100;
                            }
                            else{
                                num[i]=0;
                            }
                        }
                        num[m]=-1;
                        for(int i=0;i<25;i++){
                            player2[i]=num[i];
                        }
                    }
                    return -1;
                }
                int c=check1(b);
                if(c!=99){
                    //System.out.println(c);
                    //display(b);
                    for(int j=0;j<25;j++){
                        d[j]=b[j];
                    }
                    d[c]=2;
                    //display(d);
                    depth++;
                    byte u = recurse(d);
                    depth--;
                    if(depth==0){
                        for(int i=0;i<25;i++){
                            if(b[i]!=0){
                                num[i]=100;
                            }
                            else{
                                num[i]=1;
                            }
                        }
                        num[c]=0;
                        for(int i=0;i<25;i++){
                            player2[i]=num[i];
                        }
                    }
                    return u;
                }
                for(int j=0;j<25;j++){
                    d[j]=b[j];
                }
                for(int i=0;i<25;i++){
                    if(b[i]==0){
                        d[i]=2;
                        depth ++;
                        num[i]= recurse(d);
                        depth--;
                        d[i]=0;
                        if(num[i]==-1){
                            if(depth==0){
                                for(int j=0;j<25;j++){
                                    if(b[j]!=0){
                                        num[j]=100;
                                    }
                                    else{
                                        num[j]=0;
                                    }
                                }
                                num[i]=-1;
                                for(int j=0;j<25;j++){
                                    player2[j]=num[j];
                                }
                            }
                            return -1;
                        }
                    }
                    else{
                        num [i] = 100;
                    }
                }
                byte min =100;
                int g=0;
                for(int i=0;i<25;i++){
                    if(num[i]<min){
                        min = num[i];
                        g=i;
                    }
                }
                if(depth==0){
                    for(int i=0;i<25;i++){
                        player2[i]=num[i];
                    }
                }
                //System.out.println(g);
                //display(b);
                return min;
            }
            else{//player 1 turn
                byte [] d = new byte[25];
                int m= check1(b);
                if(m!=99){
                    if(depth==0){
                        for(int i=0;i<25;i++){
                            if(b[i]!=0){
                                num[i]=-100;
                            }
                            else{
                                num[i]=0;
                            }
                        }
                        num[m]=1;
                        for(int i=0;i<25;i++){
                            player1[i]=num[i];
                        }
                    }
                    return 1;
                }
                int c=check2(b);
                if(c!=99){
                    //System.out.println(c);
                    //display(b);
                    for(int j=0;j<25;j++){
                        d[j]=b[j];
                    }
                    d[c]=1;
                    //display(d);
                    depth++;
                    byte u = recurse(d);
                    depth--;
                    if(depth==0){
                        for(int i=0;i<25;i++){
                            if(b[i]!=0){
                                num[i]=-100;
                            }
                            else{
                                num[i]=-1;
                            }
                        }
                        num[c]=0;
                        for(int i=0;i<25;i++){
                            player1[i]=num[i];
                        }
                    }
                    return u;
                }
                for(int j=0;j<25;j++){
                    d[j]=b[j];
                }
                for(int i=0;i<25;i++){
                    if(b[i]==0){
                        d[i]=1;
                        depth++;
                        num[i]= recurse(d);
                        depth--;
                        d[i]=0;
                        if(num[i]==1){
                            if(depth==0){
                                for(int j=0;j<25;j++){
                                    if(b[j]!=0){
                                        num[j]=-100;
                                    }
                                    else{
                                        num[j]=0;
                                    }
                                }
                                num[i]=1;
                                for(int j=0;j<25;j++){
                                    player1[j]=num[j];
                                }
                            }
                            return 1;
                        }
                    }
                    else{
                        num[i]=-100;
                    }
                }
                byte max = -100;
                int g=0;
                for(int i=0;i<25;i++){
                    if(num[i]>max){
                        max = num[i];
                        g=i;
                    }
                }
                if(depth==0){
                    for(int i=0;i<25;i++){
                        player1[i]=num[i];
                    }
                }
                //System.out.println(g);
                //display(b);
                return max;
            }
        }
        return 0;
    }
    int findtwo(byte [] b, int h){//finds two consequetive x or o in the middle area
    //to prevent double trap
        for(int i=0;i<5;i++){
            if(b[5+i]==h&&b[10+i]==h){
                if(b[15+i]==0&&b[i]==0&&b[20+i]==0){
                    //System.out.println("pointer 1 returns"+(15+i));
                    return (15+i);
                }
            }
            if(b[15+i]==h&&b[10+i]==h){
                if(b[5+i]==0&&b[i]==0&&b[20+i]==0){
                    //System.out.println("pointer 2 returns"+(5+i));
                    return (5+i);
                }
            }
            if(b[15+i]==h&&b[5+i]==h){
                if(b[10+i]==0&&b[i]==0&&b[20+i]==0){
                    //System.out.println("pointer 3 returns"+(10+i));
                    return (10+i);
                }
            }
            if(b[1+(5*i)]==h&&b[2+(i*5)]==h){
                if(b[(5*i)]==0&&b[3+(5*i)]==0&&b[4+(5*i)]==0){
                    //System.out.println("pointer 4 returns"+(5*i+3));
                    return (3+(5*i));
                }
            }
            if(b[3+(5*i)]==h&&b[2+(i*5)]==h){
                if(b[(5*i)]==0&&b[1+(5*i)]==0&&b[4+(5*i)]==0){
                    //System.out.println("pointer 5 returns"+(5*i+1));
                    return (1+(5*i));
                }
            }
            if(b[3+(5*i)]==h&&b[1+(i*5)]==h){
                if(b[(5*i)]==0&&b[2+(5*i)]==0&&b[4+(5*i)]==0){
                    //System.out.println("pointer 6 returns"+(5*i+2));
                    return (2+(5*i));
                }
            }
        }
        if(b[12]==h){
            if(b[0]==0&&b[24]==0){
                if(b[6]==0&&b[18]==h){
                    return 6;
                }
                if(b[18]==0&&b[6]==h){
                    return 18;
                }
            }
            if(b[4]==0&&b[20]==0){
                if(b[8]==0&&b[16]==h){
                    return 8;
                }
                if(b[16]==0&&b[8]==h){
                    return 16;
                }
            }
        }
        return 99;
    }
    public static void main (String [] args){
        Tictactoe5 ob = new Tictactoe5();
        Scanner R = new Scanner(System.in);
        byte x[] = {0,0,0,0,0,
                    0,0,0,0,0,
                    0,0,0,0,0,
                    0,0,0,0,0,
                    0,0,0,0,0};
        //int h = ob.winner(x);
//      int p = ob.findtwoo(x);
        //System.out.println(h);
        ob.display(x);
        //ob.findtwo(x,1);
        //System.out.println(p);
        int l;
        int c;
        //System.out.println(ob.recurse(x));
        //ob.display(x);
        //System.out.println(ob.check1(x));
        //System.out.println(ob.check2(x));
	System.out.println("Choose 1 for player 1 or 2 for player 2");
	int pos = R.nextInt();
        if (pos == 1){
	    while(true){
		//ob.recurse(x);
		System.out.println("enter position to play");
	        pos = R.nextInt();
		pos --;
		if (pos < 0 || pos >= 25){
		    System.out.println("invalid position, try again");
		    continue;
		}
		else if (x[pos] != 0){
		    System.out.println("whoops someone has already played here");
		    continue;
		}
		//l = ob.choose1(ob.player1);
		x[pos]=1;
		c = ob.winner(x);
		ob.display(x);
		if(c!=0){
		    if (c == 4){
			System.out.println("its a draw ");
		    }
		    else
			System.out.println("winner is player "+ c);
		    break;
		}
		ob.decidelimit(x);
		ob.recurse(x);
		l = ob.choose2(ob.player2);
		System.out.println("computer played at :" + (l+1));
		System.out.println();
		x[l]=2;
		c = ob.winner(x);
		ob.display(x);
		if(c!=0){
		    if (c == 4){
			System.out.println("its a draw ");
		    }
		    else 
			System.out.println("winner is player "+ c);
		    break;
		}
	    }
	}
	else {
	    ob.decidelimit(x);
	    ob.recurse(x);
	    l = ob.choose1(ob.player1);
	    System.out.println("computer played at :" + (l+1));
	    System.out.println();
	    x[l]=1;
	    c = ob.winner(x);
	    ob.display(x);
	     while(true){
		 //ob.recurse(x);
		 System.out.println("enter position to play");
		 pos = R.nextInt();
		 pos --;
		 if (pos < 0 || pos >= 25){
		     System.out.println("invalid position, try again");
		     continue;
		 }
		 else if (x[pos] != 0){
		     System.out.println("whoops someone has already played here");
		     continue;
		 }
		 //l = ob.choose1(ob.player1);
		 x[pos]=2;
		 c = ob.winner(x);
		 ob.display(x);
		 if(c!=0){
		     if (c == 4){
			 System.out.println("its a draw ");
		     }
		     else 
			 System.out.println("winner is player "+ c);
		     break;
		 }
		 ob.decidelimit(x);
		 ob.recurse(x);
		 l = ob.choose1(ob.player1);
		 System.out.println("computer played at :" + (l+1));
		 System.out.println();
		 x[l]=1;
		 c = ob.winner(x);
		 ob.display(x);
		 if(c!=0){
		     if (c == 4){
			 System.out.println("its a draw ");
		     }
		     else 
			 System.out.println("winner is player "+ c);
		     break;
		 }
	     }
	}
    }
    void display(byte [] x){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(j!=0){
                    if(x[i*5+j]==1){
                        System.out.print("| X ");
                    }
                    else if(x[i*5+j]==2){
                        System.out.print("| O ");
                    }
                    else if(x[i*5+j]==0){
                        System.out.print("|   ");
                    }
                }
                else {
                    if(x[i*5+j]==1){
                        System.out.print(" X ");
                    }
                    else if(x[i*5+j]==2){
                        System.out.print(" O ");
                    }
                    else if(x[i*5+j]==0){
                        System.out.print("   ");
                    }
                }
            }
            System.out.println();
            if(i!=4){
                System.out.print("___________________");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    int winner(byte [] A){
        for(int i=0;i<5;i++){
            if(A[i*5+1]==A[i*5+2]&&A[i*5+1]==A[i*5+3]&&A[i*5+1]==A[i*5+4]){
                if(A[i*5+1]!=0){
                    return A[i*5+1];
                }
            }
            if(A[i*5]==A[i*5+1]&&A[i*5]==A[i*5+2]&&A[i*5]==A[i*5+3]){
                if(A[i*5]!=0){
                    return A[i*5];
                }
            }
            if(A[5+i]==A[10+i]&&A[5+i]==A[15+i]&&A[5+i]==A[20+i]){
                if(A[5+i]!=0){
                    return A[5+i];
                }
            }
            if(A[0*5+i]==A[5+i]&&A[0*5+i]==A[10+i]&&A[0*5+i]==A[15+i]){
                if(A[0*5+i]!=0){
                    return A[0*5+i];
                }
            }
        }
        if(A[6]==A[12]&&A[18]==A[6]){
            if(A[0]==A[6]&&A[0]!=0){
                return A[0];
            }
            if(A[24]==A[6]&&A[24]!=0){
                return A[24];
            }
        }
        if(A[8]==A[12]&&A[16]==A[12]){
            if(A[4]==A[12]&&A[4]!=0){
                return A[4];
            }
            if(A[20]==A[12]&&A[20]!=0){
                return A[20];
            }
        }
        if(A[1]==A[7]&&A[13]==A[19]&&A[7]==A[13]){
            if(A[1]!=0){
                return A[1];
            }
        }
        if(A[5]==A[11]&&A[17]==A[23]&&A[17]==A[11]){
            if(A[5]!=0){
                return A[5];
            }
        }
        if(A[3]==A[7]&&A[11]==A[15]&&A[7]==A[11]){
            if(A[3]!=0){
                return A[3];
            }
        }
        if(A[9]==A[13]&&A[17]==A[21]&&A[17]==A[13]){
            if(A[9]!=0){
                return A[9];
            }
        }
        boolean k= true;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(A[i*5+j]==0){
                    k=false;
                    break;
                }
            }
            if(!k){
                break;
            }
        }
        if(k){
            return 4;
        }
        return 0;
    }
    
    /*int findbest(byte [][] x,int p){
        /*will return an array of all places where placing x or o will make a 
        line of 3
        if row ==2 or column ==2, then that move isnt as favourable 

        
        int [][] a = new int [5][5];    
        for(inr i=0;i<5;i++){
            for(int j=0;j<5;j++){
                a[i][j]=x[i][j];
            }
        }
        for(inr i=0;i<5;i++){
            for(int j=0;j<5;j++){
                
                
            }
        }
        
        for(){}
        int y;
        
        return y;
    }
    */
    void decidelimit(byte [] x){
        int y = countmove(x);
        if(y<=4){
            limit = 1;
        }
        else if(y<15){
            limit = 6;
        }
        else if(y<20){
            limit =7;
        }
        else{
            limit = 100;
        }
    }
    int check1(byte b[]){
        byte [] a = new byte [25];
        for(int i=0;i<25;i++){
            a[i]=b[i];
        }
        for(int i=0;i<25;i++){
            if(b[i]==0){
                a[i]=1;
                if(winner(a)==1){
                    return i;
                }
                a[i]=0;
            }
        }
        return 99;
    }
    int check2(byte b[]){
        byte [] a = new byte [25];
        for(int i=0;i<25;i++){
            a[i]=b[i];
        }
        for(int i=0;i<25;i++){
            if(b[i]==0){
                a[i]=2;
                if(winner(a)==2){
                    return i;
                }
                a[i]=0;
            }
        }
        return 99;
    }
    /*
    byte [][] convert(byte [] x){
        byte[][] u = new byte [5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                u[i][j]=x[(i*5+j)];
            }
        }
        return u;
    }
    byte [] convert(byte [][] x){
        byte []u = new byte [25];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                u[5*i+j]=x[i][j];
            }
        }
        return u;
    }*/
    
    int choose1(byte[]x){
        //System.out.println("Choose 1");
        //display2(x);
        int numwin=0;
        int numdraw=0;
        int numloss=0;
        for(int i=0;i<25;i++){
            if(x[i]==1){
                numwin++;
            }
            else if(x[i]==0){
                numdraw++;
            }
            else if(x[i]==-1){
                numloss++;
            }
        }
        if(numwin==0&&numdraw!=0){
            if(x[12]==0){
                return 12;
            }
            int y=0;
            for(int i=1;i<4;i++){
                for(int j=1;j<4;j++){
                    if(x[i*5+j]==0){
                        y++;
                    }
                }
            }
            if(y!=0){
                int [] h = new int[y];
                int n=0;
                for(int i=1;i<4;i++){
                    for(int j=1;j<4;j++){
                        if(x[i*5+j]==0){
                            h[n]=(i*5+j);
                            n++;
                        }
                    }
                }
                int a = (int)(Math.random()*y);
                return h[a];
            }
        }
        if(numwin!=0){
            int t = (int)(Math.random()*numwin);
            int c=0;
            for(int i=0;i<25;i++){
                if(x[i]==1){
                    c++;
                }
                if(c>t){
                    return i;
                }
            }
        }
        if(numdraw!=0){
            int t = (int)(Math.random()*numdraw);
            int c=0;
            for(int i=0;i<25;i++){
                if(x[i]==0){
                    c++;
                }
                if(c>t){
                    return i;
                }
            }
        }
        if(numloss!=0){
            int t = (int)(Math.random()*numloss);
            int c=0;
            for(int i=0;i<25;i++){
                if(x[i]==-1){
                    c++;
                }
                if(c>t){
                    return i;
                }
            }
        }
        System.out.println("lol");
        return 99;
    }
    int choose2(byte[]x){
        //System.out.println("Choose 2");
        //display2(x);
        int numwin=0;
        int numdraw=0;
        int numloss=0;
        for(int i=0;i<25;i++){
            if(x[i]==-1){
                numwin++;
            }
            else if(x[i]==0){
                numdraw++;
            }
            else if(x[i]==1){
                numloss++;
            }
        }
        if(numwin==0&&numdraw!=0){
            if(x[12]==0){
                return 12;
            }
            int y=0;
            for(int i=1;i<4;i++){
                for(int j=1;j<4;j++){
                    if(x[i*5+j]==0){
                        y++;
                    }
                }
            }
            if(y!=0){
                int [] h = new int[y];
                int n=0;
                for(int i=1;i<4;i++){
                    for(int j=1;j<4;j++){
                        if(x[i*5+j]==0){
                            h[n]=(i*5+j);
                            n++;
                        }
                    }
                }
                int a = (int)(Math.random()*y);
                return h[a];
            }
        }
        if(numwin!=0){
            int t = (int)(Math.random()*numwin);
            int c=0;
            for(int i=0;i<25;i++){
                if(x[i]==-1){
                    c++;
                }
                if(c>t){
                    return i;
                }
            }
        }
        if(numdraw!=0){
            int t = (int)(Math.random()*numdraw);
            int c=0;
            for(int i=0;i<25;i++){
                if(x[i]==0){
                    c++;
                }
                if(c>t){
                    return i;
                }
            }
        }
        if(numloss!=0){
            int t = (int)(Math.random()*numloss);
            int c=0;
            for(int i=0;i<25;i++){
                if(x[i]==1){
                    c++;
                }
                if(c>t){
                    return i;
                }
            }
        }
        System.out.println("lol");
        return 99;
    }
    int countmove(byte[]x){
        int n=0;
        for(int i =0;i<25;i++){
            if(x[i]!=0){
                n++;
            }
        }
        return n;
    }
}
