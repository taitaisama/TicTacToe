

package tictak4;

import java.util.*;

public class Main {

    int limit ;
    int depth=0;
    byte [] player1;
    byte [] player2;
    int [][] wincombos;
    Main (){
        player1 = new byte[16]; 
        player2 = new byte[16]; 
        for(int i=0;i<16;i++){
            player1[i]=0;
            player2[i]=0;
        }
        int[][] l = {{0,4,8,12},
                     {1,5,9,13},
                     {2,6,10,14},
                     {3,7,11,15},
                     {0,1,2,3},
                     {4,5,6,7},
                     {8,9,10,11},
                     {12,13,14,15},
                     {0,5,10,15},
                     {3,6,9,12}};
        wincombos =l;
    }
    byte recurse(byte [] b){
        //System.out.println("lolololol");
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
        for(int i=0;i<16;i++){
            if(b[i]==1){
                countx++;
            }
            else if (b[i]==2){
                counto++;
            }
        }
        
        if(countx>counto){//player 2 turn
            byte [] d = new byte[16];
            byte num[] = new byte[16];
            int m=check2(b);
            if(m!=99){
                if(depth==0){
                    for(int i=0;i<16;i++){
                        if(b[i]!=0){
                            num[i]=100;
                        }
                        else{
                            num[i]=0;
                        }
                    }
                    num[m]=-1;
                    for(int i=0;i<16;i++){
                        player2[i]=num[i];
                    }
                }
                return -1;
            }
            int c=check1(b);
            if(c!=99){
                //System.out.println(c);
                //display(b);
                for(int j=0;j<16;j++){
                    d[j]=b[j];
                }
                d[c]=2;
                //display(d);
                depth++;
                byte u = recurse(d);
                depth--;
                if(depth==0){
                    for(int i=0;i<16;i++){
                        if(b[i]!=0){
                            num[i]=100;
                        }
                        else{
                            num[i]=1;
                        }
                    }
                    num[c]=0;
                    for(int i=0;i<16;i++){
                        player2[i]=num[i];
                    }
                }
                return u;
            }
            for(int j=0;j<16;j++){
                d[j]=b[j];
            }
            for(int i=0;i<16;i++){
                if(b[i]==0){
                    d[i]=2;
                    depth ++;
                    num[i]= recurse(d);
                    depth--;
                    d[i]=0;
                    if(num[i]==-1){
                        if(depth==0){
                            for(int j=0;j<16;j++){
                                if(b[j]!=0){
                                    num[j]=100;
                                }
                                else{
                                    num[j]=0;
                                }
                            }
                            num[i]=-1;
                            for(int j=0;j<16;j++){
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
            for(int i=0;i<16;i++){
                if(num[i]<min){
                    min = num[i];
                    g=i;
                }
            }
            if(depth==0){
                for(int i=0;i<16;i++){
                    player2[i]=num[i];
                }
            }
            //System.out.println(g);
            //display(b);
            return min;
        }
        else{//player 1 turn
            byte [] d = new byte[16];
            byte num[] = new byte[16];
            int m= check1(b);
            if(m!=99){
                if(depth==0){
                    for(int i=0;i<16;i++){
                        if(b[i]!=0){
                            num[i]=-100;
                        }
                        else{
                            num[i]=0;
                        }
                    }
                    num[m]=1;
                    for(int i=0;i<16;i++){
                        player1[i]=num[i];
                    }
                }
                return 1;
            }
            int c=check2(b);
            if(c!=99){
                //System.out.println(c);
                //display(b);
                for(int j=0;j<16;j++){
                    d[j]=b[j];
                }
                d[c]=1;
                //display(d);
                depth++;
                byte u = recurse(d);
                depth--;
                if(depth==0){
                    for(int i=0;i<16;i++){
                        if(b[i]!=0){
                            num[i]=-100;
                        }
                        else{
                            num[i]=-1;
                        }
                    }
                    num[c]=0;
                    for(int i=0;i<16;i++){
                        player1[i]=num[i];
                    }
                }
                return u;
            }
            for(int j=0;j<16;j++){
                d[j]=b[j];
            }
            for(int i=0;i<16;i++){
                if(b[i]==0){
                    d[i]=1;
                    depth++;
                    num[i]= recurse(d);
                    depth--;
                    d[i]=0;
                    if(num[i]==1){
                        if(depth==0){
                            for(int j=0;j<16;j++){
                                if(b[j]!=0){
                                    num[j]=-100;
                                }
                                else{
                                    num[j]=0;
                                }
                            }
                            num[i]=1;
                            for(int j=0;j<16;j++){
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
            for(int i=0;i<16;i++){
                if(num[i]>max){
                    max = num[i];
                    g=i;
                }
            }
            if(depth==0){
                for(int i=0;i<16;i++){
                    player1[i]=num[i];
                }
            }
            //System.out.println(g);
            //display(b);
            return max;
        }
        //return 0;
    }
    /*byte recurse(byte [] b){
        //display(b);
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
        int countx=0;
        int counto=0;
        for(int i=0;i<16;i++){
            if(b[i]==1){
                countx++;
            }
            else if (b[i]==2){
                counto++;
            }
        }
        if((countx+counto)<6){
            for(int i=0;i<16;i++){
                player1[i]=-100;
                player2[i]=100;
            }
            if(countx==0){
                int [] e = {5,6,9,10};
                int r = chooserand(e);
                System.out.println("1st choice ="+r);
                player1[r]=0;
            }
            else if(counto==0){
                int [] e = {5,6,9,10};
                int r = chooserand(e);
                while(b[r]!=0){
                   r = chooserand(e); 
                }
                player2[r]=0;
            }
            else if(counto==1&&countx==1){
                int [] e = {5,6,9,10};
                int r = chooserand(e);
                while(b[r]!=0){
                   r = chooserand(e); 
                }
                player1[r]=0;
            }
            else if(countx==2&&counto==1){
                int [] e = {5,6,9,10};
                int r = chooserand(e);
                while(b[r]!=0){
                   r = chooserand(e); 
                }
                player2[r]=0;
            }
            else if(counto==2&&countx==2){
                int h = findtwox(b);
                if(h!=99){
                    player1[h]=0;
                }
                else{
                    h=findtwoo(b);
                    if(h!=99){
                        player1[h]=0;
                    }
                    else{
                        int v = (int)(Math.random()*16);
                        while(b[v]!=0){
                            v = (int)(Math.random()*16);
                        }
                        player1[v]=0;
                    }
                }
            }
            else{   
                int h = findtwox(b);
                if(h!=99){
                    player2[h]=0;
                }
                else{
                    h=findtwoo(b);
                    if(h!=99){
                        player2[h]=0;
                    }
                    else{
                        int v = (int)(Math.random()*16);
                        while(b[v]!=0){
                            v = (int)(Math.random()*16);
                        }
                        player2[v]=0;
                    }
                }
            }
        }
        else{
            if(countx>counto){//player 2 turn
                byte [] d = new byte[16];
                byte num[] = new byte[16];
                int m=check2(b);
                if(m!=99){
                    if(depth==0){
                        for(int i=0;i<16;i++){
                            if(b[i]!=0){
                                num[i]=100;
                            }
                            else{
                                num[i]=0;
                            }
                        }
                        num[m]=-1;
                        for(int i=0;i<16;i++){
                            player2[i]=num[i];
                        }
                    }
                    return -1;
                }
                int c=check1(b);
                if(c!=99){
                    //System.out.println(c);
                    //display(b);
                    for(int j=0;j<16;j++){
                        d[j]=b[j];
                    }
                    d[c]=2;
                    //display(d);
                    depth++;
                    byte u = recurse(d);
                    depth--;
                    if(depth==0){
                        for(int i=0;i<16;i++){
                            if(b[i]!=0){
                                num[i]=100;
                            }
                            else{
                                num[i]=1;
                            }
                        }
                        num[c]=0;
                        for(int i=0;i<16;i++){
                            player2[i]=num[i];
                        }
                    }
                    return u;
                }
                for(int j=0;j<16;j++){
                    d[j]=b[j];
                }
                for(int i=0;i<16;i++){
                    if(b[i]==0){
                        d[i]=2;
                        depth ++;
                        num[i]= recurse(d);
                        depth--;
                        d[i]=0;
                        if(num[i]==-1){
                            if(depth==0){
                                for(int j=0;j<16;j++){
                                    if(b[j]!=0){
                                        num[j]=100;
                                    }
                                    else{
                                        num[j]=0;
                                    }
                                }
                                num[i]=-1;
                                for(int j=0;j<16;j++){
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
                for(int i=0;i<16;i++){
                    if(num[i]<min){
                        min = num[i];
                        g=i;
                    }
                }
                if(depth==0){
                    for(int i=0;i<16;i++){
                        player2[i]=num[i];
                    }
                }
                //System.out.println(g);
                //display(b);
                return min;
            }
            else{//player 1 turn
                byte [] d = new byte[16];
                byte num[] = new byte[16];
                int m= check1(b);
                if(m!=99){
                    if(depth==0){
                        for(int i=0;i<16;i++){
                            if(b[i]!=0){
                                num[i]=-100;
                            }
                            else{
                                num[i]=0;
                            }
                        }
                        num[m]=1;
                        for(int i=0;i<16;i++){
                            player1[i]=num[i];
                        }
                    }
                    return 1;
                }
                int c=check2(b);
                if(c!=99){
                    //System.out.println(c);
                    //display(b);
                    for(int j=0;j<16;j++){
                        d[j]=b[j];
                    }
                    d[c]=1;
                    //display(d);
                    depth++;
                    byte u = recurse(d);
                    depth--;
                    if(depth==0){
                        for(int i=0;i<16;i++){
                            if(b[i]!=0){
                                num[i]=-100;
                            }
                            else{
                                num[i]=-1;
                            }
                        }
                        num[c]=0;
                        for(int i=0;i<16;i++){
                            player1[i]=num[i];
                        }
                    }
                    return u;
                }
                for(int j=0;j<16;j++){
                    d[j]=b[j];
                }
                for(int i=0;i<16;i++){
                    if(b[i]==0){
                        d[i]=1;
                        depth++;
                        num[i]= recurse(d);
                        depth--;
                        d[i]=0;
                        if(num[i]==1){
                            if(depth==0){
                                for(int j=0;j<16;j++){
                                    if(b[j]!=0){
                                        num[j]=-100;
                                    }
                                    else{
                                        num[j]=0;
                                    }
                                }
                                num[i]=1;
                                for(int j=0;j<16;j++){
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
                for(int i=0;i<16;i++){
                    if(num[i]>max){
                        max = num[i];
                        g=i;
                    }
                }
                if(depth==0){
                    for(int i=0;i<16;i++){
                        player1[i]=num[i];
                    }
                }
                //System.out.println(g);
                //display(b);
                return max;
            }
        }
        return 0;
    }*/
    /*byte recurse(byte [] b){
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
        int countx=0;
        int counto=0;
        for(int i=0;i<16;i++){
            if(b[i]==1){
                countx++;
            }
            else if (b[i]==2){
                counto++;
            }
        }
        if(countx>counto){
            byte num[] = new byte[16];
            for(int i=0;i<16;i++){
                if(b[i]==0){
                    byte [] d = new byte[16];
                    for(int j=0;j<16;j++){
                        d[j]=b[j];
                    }
                    d[i]=2;
                    depth ++;
                    num[i]= recurse(d);
                    depth--;
                }
                else{
                    num [i] = 100;
                }
            }
            byte min =100;
            int g=0;
            for(int i=0;i<16;i++){
                if(num[i]<min){
                    min = num[i];
                    g=i;
                }
            }
            if(depth==0){
                for(int i=0;i<16;i++){
                    player2[i]=num[i];
                }
            }
            //System.out.println(g);
            //display(b);
            return min;
        }
        else{
            byte num[] = new byte[16];
            for(int i=0;i<16;i++){
                if(b[i]==0){
                    byte [] d = new byte[16];
                    for(int j=0;j<16;j++){
                        d[j]=b[j];
                    }
                    d[i]=1;
                    depth++;
                    num[i]= recurse(d);
                    depth--;
                }
                else{
                    num[i]=-100;
                }
            }
            byte max = -100;
            int g=0;
            for(int i=0;i<16;i++){
                if(num[i]>max){
                    max = num[i];
                    g=i;
                }
            }
            if(depth==0){
                for(int i=0;i<16;i++){
                    player1[i]=num[i];
                }
            }
            //System.out.println(g);
            //display(b);
            return max;
        }
    }*/
    public static void main (String [] args){
        Main ob = new Main();
        byte x[] = {0,0,0,0,
                    0,0,0,0,
                    0,0,0,0,
                    0,0,0,0};
        int h = ob.winner(x);
	Scanner R = new Scanner(System.in);
//      int p = ob.findtwoo(x);
        //System.out.println(h);
        ob.display(x);
        //System.out.println(p);
        int l;
        int c;
        //System.out.println(ob.recurse(x));
        //ob.display(x);
        //System.out.println(ob.check1(x));
        //System.out.println(ob.check2(x));
	System.out.println("Choose 1 for player 1 or 2 for player 2");
	int pos = R.nextInt();
        boolean error = false;
        if (pos == 1){
	    while(true){
		//ob.recurse(x);
		System.out.println("enter position to play");
	        pos = R.nextInt();
		pos --;
		if (pos < 0 || pos >= 16){
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
	    ob.decidelimit(x);
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
		 if (pos < 0 || pos >= 16){
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
        //for(int i=0;i<16;i++){
            //System.out.println(ob.player1[i]);
        //}
    }
    int winner(byte [] x){
        for (int i=0;i<10;i++){
            int c=wincombos[i][0];
            if(x[c]!=0){
                boolean win = true;
                for(int j=1;j<4;j++){
                    if(x[wincombos[i][j]]!=x[c]){
                        win=false;
                        break;
                    }
                }
                if(win){
                    return x[c];
                }
            }
        }
        boolean k=true;
        for(int i=0;i<16;i++){
            if(x[i]==0){
                k=false;
                break;
            }
        }
        if(k){
            return 4;
        }
        return 0;
        /*for(int i=0;i<4;i++){
            int m = x[i*4];
            boolean s = true;
            if(m!=0){
                for(int j=1;j<4;j++){
                    if (x[(i*4)+j]!=m){
                        s=false;
                        break;
                    }
                }
                if(s){
                    return m;
                }
            }
            m = x[i*4];
            s = true;
            if(m!=0){
                for(int j=1;j<4;j++){
                    if (x[i+j*4]!=m){
                        s=false;
                        break;
                    }
                }
                if(s){
                    return m;
                }
            }
        }
        int m=x[0];
        boolean s=true;
        if(m!=0){
            for(int i=1;i<4;i++){
                if(x[i+(i*4)]!=m){
                    s=false;
                    break;
                }
            }
            if(s){
                return m;
            }
        }
        m=x[3];
        s=true;
        if(m!=0){
            for(int i=1;i<4;i++){
                if(x[(3+(i*4)-i)]!=m){
                    s=false;
                    break;
                }
            }
            if(s){
                return m;
            }
        }
        boolean k=true;
        for(int i=0;i<16;i++){
            if(x[i]==0){
                k=false;
                break;
            }
        }
        if(k){
            return 4;
        }
        return 0;*/
    }
    void display(byte [] h){
        System.out.println();
        for(int i=0;i<16;i++){
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
            if(i==3||i==7||i==11){
                System.out.println();
                System.out.println("__________________");
            }
        }
        System.out.println();
    }
    int check1(byte b[]){
        byte [] a = new byte [16];
        for(int i=0;i<16;i++){
            a[i]=b[i];
        }
        for(int i=0;i<16;i++){
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
        byte [] a = new byte [16];
        for(int i=0;i<16;i++){
            a[i]=b[i];
        }
        for(int i=0;i<16;i++){
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
    /*int chooserand(int [] x){
        int a = (int)(Math.random()*(x.length));
        return x[a];
    }*/
    void decidelimit(byte [] x){
        int y = countmove(x);
        if(y==0||y==1||y==2||y==3){
            limit = 1;
        }
        else if(y==4||y==5){
            limit = 6;
        }
        else if(y==6||y==7){
            limit = 7;
        }
        else{
            limit = 100;
        }
    }
    /*int findtwox(byte [] x){
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                if(x[(i*4)+j]==1&&x[(i*4)+j+1]==1){
                    if(j<2){
                        if(x[(i*4)+j+2]==0){
                            return ((i*4)+j+2);
                        }
                    }
                    if(j>0){
                        if(x[(i*4)+j-1]==0){
                            return ((i*4)+j-1);
                        }
                    }
                }
                if(x[i+(j*4)]==1&&x[i+(j+1)*4]==1){
                    if(j<2){
                        if(x[i+(j+2)*4]==0){
                            return (i+(j+2)*4);
                        }
                    }
                    if(j>0){
                        if(x[i+(j-1)*4]==0){
                            return (i+(j-1)*4);
                        }
                    }
                }
            }
        }
        if(x[0]==1&&x[5]==1&&x[10]==0){
            return 10;
        }
        else if(x[5]==1&&x[10]==1&&x[15]==0){
            return 15;
        }
        else if(x[5]==1&&x[10]==1&&x[0]==0){
            return 0;
        }
        else if(x[3]==1&&x[6]==1&&x[9]==0){
            return 9;
        }
        else if(x[9]==1&&x[6]==1&&x[3]==0){
            return 3;
        }
        else if(x[9]==1&&x[6]==1&&x[12]==0){
            return 12;
        }
        else if(x[9]==1&&x[12]==1&&x[6]==0){
            return 6;
        }
        return 99;
    }
    int findtwoo(byte [] x){
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                if(x[(i*4)+j]==2&&x[(i*4)+j+1]==2){
                    if(j<2){
                        if(x[(i*4)+j+2]==0){
                            return ((i*4)+j+2);
                        }
                    }
                    if(j>0){
                        if(x[(i*4)+j-1]==0){
                            return ((i*4)+j-1);
                        }
                    }
                }
                if(x[i+(j*4)]==2&&x[i+(j+1)*4]==2){
                    if(j<2){
                        if(x[i+(j+2)*4]==0){
                            return (i+(j+2)*4);
                        }
                    }
                    if(j>0){
                        if(x[i+(j-1)*4]==0){
                            return (i+(j-1)*4);
                        }
                    }
                }
            }
        }
        if(x[0]==2&&x[5]==2&&x[10]==0){
            return 10;
        }
        else if(x[5]==2&&x[10]==2&&x[15]==0){
            return 15;
        }
        else if(x[5]==2&&x[10]==2&&x[0]==0){
            return 0;
        }
        else if(x[3]==2&&x[6]==2&&x[9]==0){
            return 9;
        }
        else if(x[9]==2&&x[6]==2&&x[3]==0){
            return 3;
        }
        else if(x[9]==2&&x[6]==2&&x[12]==0){
            return 12;
        }
        else if(x[9]==2&&x[12]==2&&x[6]==0){
            return 6;
        }
        return 99;
    }
    */
    int choose1(byte[]x){
        //System.out.println("Choose 1");
        //display2(x);
        int numwin=0;
        int numdraw=0;
        int numloss=0;
        for(int i=0;i<16;i++){
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
            for(int i=0;i<16;i++){
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
            for(int i=0;i<16;i++){
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
            for(int i=0;i<16;i++){
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
        for(int i=0;i<16;i++){
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
            for(int i=0;i<16;i++){
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
            for(int i=0;i<16;i++){
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
            for(int i=0;i<16;i++){
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
        for(int i =0;i<16;i++){
            if(x[i]!=0){
                n++;
            }
        }
        return n;
    }
}
