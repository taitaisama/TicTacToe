
package tictacminmax;

import java.util.*;

public class Tictacminmax {
    int depth=0;
    byte [] player1;
    byte [] player2;
    int limit=100;
    Tictacminmax (){
        player1 = new byte[9]; 
        player2 = new byte[9]; 
        for(int i=0;i<9;i++){
            player1[i]=0;
            player2[i]=0;
        }
    }
    byte recurse(byte [] b){
        int y = winner(b);
        if(y==1)
            return 1;
        else if(y==2)
            return -1;
        else if(y==4)
            return 0;
        if(depth>=limit)
            return 0;
        int countx=0;
        int counto=0;
        for(int i=0;i<9;i++){
            if(b[i]==1)
                countx++;
            else if (b[i]==2)
                counto++;
        }
        boolean player2turn=false;
        byte num[] = new byte[9];
        byte n=1;
        if(countx>counto){
            player2turn=true;
            n=2;
        }
        byte [] d = new byte[9];
        for(int j=0;j<9;j++)
            d[j]=b[j];
        for(int i=0;i<9;i++){
            if(b[i]==0){
                d[i]=n;
                depth ++;
                num[i]= recurse(d);
                depth--;
                d[i]=0;
            }
            else{
                num [i] = 100;
            }
        }
        byte min = 100;
        byte max = -100;
        for(int i=0;i<9;i++){
            if(num[i]<min&&num[i]!=100)
                min = num[i];
            if(num[i]>max&&num[i]!=100)
                max = num[i];
        }
        if(depth==0){
            for(int i=0;i<9;i++){
                if(player2turn)
                    player2[i]=num[i];
                else
                    player1[i]=num[i];
            }
        }
        if(player2turn)
            return min;
        else
            return max;
    }
    public static void main (String [] args){
        Tictacminmax ob = new Tictacminmax();
        byte x[] = {0,0,0,0,0,0,0,0,0};
	Scanner R = new Scanner(System.in);
        int l;
        int c;
        long h = System.currentTimeMillis();
	System.out.println("positions are: ");
	ob.display2();
	System.out.println("Choose 1 for player 1 or 2 for player 2");
	int pos = R.nextInt();
	if (pos == 1){
	    while(true){
		//ob.recurse(x);
		System.out.println("enter position to play");
	        pos = R.nextInt();
		pos --;
		if (pos < 0 || pos >= 9){
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
		ob.recurse(x);
		l = ob.choose2(ob.player2);
		System.out.println("computer played at :" + l);
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
	    ob.recurse(x);
	    l = ob.choose1(ob.player1);
	    System.out.println("computer played at :" + l);
	    x[l]=1;
	    c = ob.winner(x);
	    ob.display(x);
	     while(true){
		 //ob.recurse(x);
		 System.out.println("enter position to play");
		 pos = R.nextInt();
		 pos --;
		 if (pos < 0 || pos >= 9){
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
		 ob.recurse(x);
		 l = ob.choose1(ob.player1);
		 System.out.println("computer played at :" + l);
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
    int winner(byte [] x){
        if(x[0]==x[1]&&x[2]==x[0]){
            if(x[0]!=0){
                return x[0];
            }
        }
        if(x[0]==x[3]&&x[6]==x[0]){
            if(x[0]!=0){
                return x[0];
            }
        }
        if(x[3]==x[4]&&x[5]==x[3]){
            if(x[3]!=0){
                return x[3];
            }
        }
        if(x[6]==x[7]&&x[8]==x[7]){
            if(x[6]!=0){
                return x[6];
            }
        }
        if(x[1]==x[7]&&x[4]==x[7]){
            if(x[1]!=0){
                return x[1];
            }
        }
        if(x[2]==x[5]&&x[2]==x[8]){
            if(x[2]!=0){
                return x[2];
            }
        }
        if(x[0]==x[4]&&x[4]==x[8]){
            if(x[0]!=0){
                return x[0];
            }
        }
        if(x[2]==x[4]&&x[2]==x[6]){
            if(x[2]!=0){
                return x[2];
            }
        }
        boolean k = true;
        for(int i=0;i<9;i++){
            if(x[i]==0){
                k=false;
                break;
            }
        }
        if(k){
            return 4;
        }
        return 0;
    }
    void display2(){
	System.out.println();
	for(int i=0;i<9;i++){
	    System.out.print(" " + (i+1) + " ");
            System.out.print("|");
            if(i==2||i==5){
                System.out.println();
                System.out.println("___________");
            }
        }
        System.out.println();
    }
    void display(byte [] h){
        System.out.println();
	for(int i=0;i<9;i++){
            if(h[i]==0){
                System.out.print("   ");
            }
            else if(h[i]==1){
                System.out.print(" X ");
            }
            else if(h[i]==2){
                System.out.print(" O ");
            }
            System.out.print("|");
            if(i==2||i==5){
                System.out.println();
                System.out.println("___________");
            }
        }
        System.out.println();
    }
    int choose1(byte[]x){
        int numwin=0;
        int numdraw=0;
        int numloss=0;
        for(int i=0;i<9;i++){
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
        
        if(numwin!=0){
            int t = (int)(Math.random()*numwin);
            int c=0;
            for(int i=0;i<9;i++){
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
            for(int i=0;i<9;i++){
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
            for(int i=0;i<9;i++){
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
        int numwin=0;
        int numdraw=0;
        int numloss=0;
        for(int i=0;i<9;i++){
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
        
        if(numwin!=0){
            int t = (int)(Math.random()*numwin);
            int c=0;
            for(int i=0;i<9;i++){
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
            for(int i=0;i<9;i++){
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
            for(int i=0;i<9;i++){
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
}
