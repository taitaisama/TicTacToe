

import java.util.*;
public class Tictac6 {
    int [] winstreak;
    int [] winstreak5;
    double [] player1;
    double [] player2;
    int depth=0;
    int limit = 8;
    Tictac6(){
        winstreak = new int[4];
        winstreak5 = new int[5];
        player1 = new double[49];
        player2 = new double[49];
    }
    public static void main (String[]args){
        Tictac6 ob= new Tictac6();
        Scanner R = new Scanner(System.in);
        int [] x = {0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0
                    };
        System.out.println(ob.recurse(x));
        int [] moves = new int [49];
        for(int i=0;i<49;i++){
            moves[i]=100;
        }
        //ob.run5(x);
        int win1=0;
        int win2=0;
        int tie=0;
        int y=0;
        int n=0;
	int c;
	int l;
	/*y=0;
	n=0;
	while(y==0){
	    //ob.display(x);
	    y=ob.winner5(x);
	    if(y==1){
		win1++;
		break;
	    }
	    else if(y==2){
		win2++;
		break;
	    }
	    else if(y==4){
		tie++;
		break;
	    }
	    int[] h = ob.run5(x);
	    //System.out.println(h.length);
	    //ob.displayGoodMoves(h);
	    x[h[(int)(Math.random()*h.length)]]=1;
	    //moves[n]=h;
	    n++;
	    y=ob.winner5(x);
	    if(y==1){
		win1++;
		break;
	    }
	    else if(y==2){
		win2++;
		break;
	    }
	    else if(y==4){
		tie++;
		break;
	    }
	    ob.display(x);
	    //int f = R.nextInt();
	    h = ob.run5(x);
	    //System.out.println(h.length);
	    //ob.displayGoodMoves(h);
	    x[h[(int)(Math.random()*h.length)]]=2;
	    //moves[n]=h;
	    n++;
	    }*/

	System.out.println("Choose 1 for player 1 or 2 for player 2");
	int pos = R.nextInt();
        if (pos == 1){
	    while(true){
		//ob.recurse(x);
		System.out.println("enter position to play");
	        pos = R.nextInt();
		pos --;
		if (pos < 0 || pos >= 49){
		    System.out.println("invalid position, try again");
		    continue;
		}
		else if (x[pos] != 0){
		    System.out.println("whoops someone has already played here");
		    continue;
		}
		//l = ob.choose1(ob.player1);
		x[pos]=1;
		c = ob.winner5(x);
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
		l = ob.choose(ob.player2, 2);
		System.out.println("computer played at :" + (l+1));
		System.out.println();
		x[l]=2;
		c = ob.winner5(x);
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
	    l = ob.choose(ob.player1, 1);
	    System.out.println("computer played at :" + (l+1));
	    System.out.println();
	    x[l]=1;
	    c = ob.winner5(x);
	    ob.display(x);
	     while(true){
		 //ob.recurse(x);
		 System.out.println("enter position to play");
		 pos = R.nextInt();
		 pos --;
		 if (pos < 0 || pos >= 49){
		     System.out.println("invalid position, try again");
		     continue;
		 }
		 else if (x[pos] != 0){
		     System.out.println("whoops someone has already played here");
		     continue;
		 }
		 //l = ob.choose1(ob.player1);
		 x[pos]=2;
		 c = ob.winner5(x);
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
		 l = ob.choose(ob.player1, 1);
		 System.out.println("computer played at :" + (l+1));
		 System.out.println();
		 x[l]=1;
		 c = ob.winner5(x);
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
    double recurse(int [] b){
        //display(b);
        int y = winner5(b);
        if(y==1)
            return 1;
        else if(y==2)
            return -1;
        else if(y==4)
            return 0;
        if(depth>=limit){
            double n = decideAdvantage(b);
            //System.out.println(n);
            return n;
        }
        int countx=0;
        int counto=0;
        for(int i=0;i<49;i++){
            if(b[i]==1)
                countx++;
            else if (b[i]==2)
                counto++;
        }
        boolean player2turn=false;
        double num[] = new double[49];
        int n=1;
        if(countx>counto){
            player2turn=true;
            n=2;
        }
        int [] d = new int[49];
        for(int j=0;j<49;j++){
            d[j]=b[j];
        }
        int [] goodMoves = run5(b);
        //displayGoodMoves(goodMoves);
        int [] z = new int[49];
        for(int i=0;i<49;i++){
            z[i]=0;
        }
        for(int i=0;i<goodMoves.length;i++){
            z[goodMoves[i]]=100;
        }
        boolean solved = false;
        for(int i=0;i<49;i++){
            if(b[i]==0&&z[i]==100&&(!solved)){
                d[i]=n;
                depth ++;
                num[i]= recurse(d);
                depth--;
                d[i]=0;
                if((num[i]==1&&(!player2turn))||(num[i]==-1&&player2turn)){
                    solved = true;
                }
            }
            else if(b[i]!=0){
                num [i] = 100;
            }
            else{
                if(player2turn){
                    num[i]=1;
                }
                else{
                    num[i]=-1;
                }
            }
        }
        double min = 100;
        double max = -100;
        for(int i=0;i<49;i++){
            if(num[i]<min&&num[i]!=100)
                min = num[i];
            if(num[i]>max&&num[i]!=100)
                max = num[i];
        }
        if(depth==0){
            for(int i=0;i<49;i++){
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

    double decideAdvantage(int b[]){
        int [] attacksplayer1 = attack5(b,1);
        int [] attacksplayer2 = attack5(b,2);
        double sum=0;
        sum+=attacksplayer1[4]*0.1;
        sum-=attacksplayer2[4]*0.1;
        sum+=attacksplayer1[3]*0.025;
        sum-=attacksplayer2[3]*0.025;
        sum+=attacksplayer1[2]*0.00625;
        sum-=attacksplayer2[2]*0.00625;
        sum+=attacksplayer1[1]*(0.00625/4);
        sum-=attacksplayer2[1]*(0.00625/4);
        sum+=attacksplayer1[0]*(0.00625/16);
        sum-=attacksplayer2[0]*(0.00625/16);
        if(sum>1){
            sum=0.99;
        }
        else if(sum<-1){
            sum=-0.99;
        }
        return sum;
    }


    int randomchoice(int []b){
        int [] p;
        int n=0;
        for(int i=0;i<49;i++){
            if(b[i]==0){
                n++;
            }
        }
        p=new int[n];
        int a=0;
        for(int i=0;i<49;i++){
            if(b[i]==0){
                p[a]=i;
                a++;
            }
        }
        n = (int)(Math.random()*a);
        return p[n];
    }
    /*int run(int[]b){
        //int y = winner(b);
        //if(y==1)
            //System.out.println("error");
            //return 1;
        //else if(y==2)
            //System.out.println("error");
            //return -1;
        //else if(y==4)
            //System.out.println("error");
            //return 0;
        int countx=0;
        int counto=0;
        for(int i=0;i<42;i++){
            if(b[i]==1)
                countx++;
            else if (b[i]==2)
                counto++;
        }
        int n1=0;
        int n2=0;
        if(countx==counto){//player1turn
            n1=1;
            n2=2;
        }
        else {//player2turn
            //System.out.println("player 2 turn");
            n1=2;
            n2=1;
        }
        int [][] attacklist = new int [42][4];
        int [][] blocklist = new int [42][4];
        int [] d = new int[42];
        for(int i=0;i<42;i++){
            d[i]=b[i];
        }
        for(int i=0;i<42;i++){
            if(b[i]==0){
                d[i]=n1;
                int [] c = attack(d,n1);
                int [] g = block(d,n1);
                attacklist[i] = c;
                blocklist[i] = g;
                d[i]=0;
            }
        }
        /*for(int i=0;i<42;i++){
            System.out.println("attacks "+ i);
            for(int j=0;j<4;j++){
                System.out.print(attacklist[i][j]+" , ");
            }
            System.out.println();
        }
        for(int i =0;i<42;i++){//finds 4 in a line option
            if(b[i]==0){
                if(attacklist[i][3]>0){
                //System.out.println("flag1");
                return i;
                }
            }
        }
        int v;
        for(v=0;v<42;v++){
            if(b[v]==0){
                break;
            }
        }
        int m = blocklist[v][3];
        for(int i=(v+1);i<42;i++){//finds blocking winning move option
            if(b[i]==0){
                if(blocklist[i][3]>m){
                    //System.out.println("flag2");
                    return i;
                }
                else if(blocklist[i][3]<m){
                    //System.out.println(m);
                    //System.out.println("i = "+i);
                    //System.out.println("flag3");
                    return v;
                }
            }
        }
        for(int i =0;i<42;i++){//finds double attack option
            if(b[i]==0){
                if(attacklist[i][2]>=2){
                    //System.out.println("flag4");
                    return i;
                }
            }
        }
        int [][] attacklistenemy = new int [42][4];
        for(int i=0;i<42;i++){
            if(b[i]==0){
                d[i]=n2;
                int [] c = attack(d,n2);
                attacklistenemy[i] = c;
                d[i]=0;
            }
        }
        /*for(int i =0;i<42;i++){//finds double attacks of enemy option
            if(b[i]==0){
                if(attacklistenemy[i][2]>=2){
                    System.out.println("flag5");
                    
                    //return i;
                }
            }
        }
        attacklist = correct(attacklist,b);
        blocklist = correct(blocklist,b);
        int [] moveValue = new int [42];
        for(int i=0;i<42;i++){
            int sum=0;
            if(attacklistenemy[i][2]>=2){
                sum=sum+(attacklistenemy[i][2]*300000);
            }
            sum = sum+(attacklist[i][2]*1000000);
            sum = sum+(blocklist[i][2]*50000);
            sum = sum+(attacklist[i][1]*5000);
            sum = sum+(blocklist[i][1]*40);
            sum = sum+(attacklist[i][0]*50);
            sum = sum+(blocklist[i][0]*5);
            moveValue[i] = sum;
        }
        int max = -1;
        int pos=0;
        //int pos=choose(moveValue);
        for(int i=0;i<42;i++){
            //System.out.println(moveValue[i]);
            if(moveValue[i]>max){
                max=moveValue[i];
                pos=i;
            }
        }
        if(b[pos]!=0){
            System.out.println("error");
        }
        //System.out.println("flag6");
        return pos;
    }*/
    int[] run5(int[]b){
        int countx=0;
        int counto=0;
        int [] l;
        for(int i=0;i<49;i++){
            if(b[i]==1)
                countx++;
            else if (b[i]==2)
                counto++;
        }
        int n1=0;
        int n2=0;
        if(countx==counto){//player1turn
            n1=1;
            n2=2;
        }
        else {
            n1=2;
            n2=1;
        }
        if(countx==0&&counto==0){
            int [] f = new int[1];
            f[0]=24;
            return f;
        }
        int [][] attacklist = new int [49][5];
        int [][] blocklist = new int [49][5];
        int [] d = new int[49];
        for(int i=0;i<49;i++){
            d[i]=b[i];
        }
        for(int i=0;i<49;i++){
            if(b[i]==0){
                d[i]=n1;
                int [] c = attack5(d,n1);
                int [] g = block5(d,n1);
                attacklist[i] = c;
                blocklist[i] = g;
                d[i]=0;
            }
        }
        for(int i =0;i<49;i++){//finds 4 in a line option
            if(b[i]==0){
                if(attacklist[i][4]>0){
                    l = new int[1];
                    l[0]=i;
                    return l;
                }
            }
        }
        int v;
        for(v=0;v<49;v++){
            if(b[v]==0){
                break;
            }
        }
        int m = blocklist[v][4];
        for(int i=(v+1);i<49;i++){//finds blocking winning move option
            if(b[i]==0){
                if(blocklist[i][4]>m){
                    l = new int[1];
                    l[0]=i;
                    return l;
                }
                else if(blocklist[i][4]<m){
                    l = new int[1];
                    l[0]=v;
                    return l;
                }
            }
        }
        for(int i =0;i<49;i++){//finds double attack option
            if(b[i]==0){
                if(attacklist[i][3]>=2){
                    //System.out.println("flag4");
                    l = new int[1];
                    l[0]=i;
                    return l;
                }
            }
        }
        int [][] attacklistenemy = new int [49][5];
        for(int i=0;i<49;i++){
            if(b[i]==0){
                d[i]=n2;
                int [] c = attack5(d,n2);
                attacklistenemy[i] = c;
                d[i]=0;
            }
        }
        attacklist = correct5(attacklist,b);
        blocklist = correct5(blocklist,b);
        int [] moveValue = new int [49];
        for(int i=0;i<49 ;i++){
            int sum=0;
            /*if(attacklistenemy[i][3]>=2){
                sum=sum+(attacklistenemy[i][3]*500000);
            }
            sum = sum+(attacklist[i][3]*1000000);
            sum = sum+(blocklist[i][3]*100000);
            sum = sum+(attacklist[i][2]*10000);
            sum = sum+(blocklist[i][2]*800);
            sum = sum+(attacklist[i][1]*1000);
            sum = sum+(blocklist[i][1]*100);
            sum = sum+(attacklist[i][0]*10);
            sum = sum+(blocklist[i][0]);
            */
            if(attacklistenemy[i][3]>=2){
                sum=sum+(attacklistenemy[i][3]*500000);
            }
            sum+= decideValue(attacklist[i],blocklist[i]);
            moveValue[i] = sum;
        }
        //displayMoveValue(moveValue);
        int [] z = bestMoves(moveValue);
        if(z[0]==99){
            z[0]=randomchoice(b);
        }
        return z;
    }
    int decideValue(int [] a, int [] b){
        int sum=0;
        sum = sum+(a[3]*1000000);
        sum = sum+(b[3]*100000);
        sum = sum+(a[2]*10000);
        sum = sum+(b[2]*800);
        sum = sum+(a[1]*1000);
        sum = sum+(b[1]*100);
        sum = sum+(a[0]*10);
        sum = sum+(b[0]);
        return sum;
    }
    /*int [][] correct(int [][] x, int[] b){
        int[][] a = new int [42][4];
        
        for(int i=0;i<42;i++){
            for(int j=0;j<4;j++){
                a[i][j] = x[i][j];
            }
        }
        for(int j=0;j<4;j++){
            int min = 100000;
            for(int i=0;i<42;i++){
                if(b[i]==0){
                    if(min>x[i][j]){
                        min=x[i][j];
                    }
                }
            }
            for(int i=0;i<42;i++){
                if(b[i]==0){
                    a[i][j] = x[i][j]-min;
                }
            }
        }
        return a;
    }*/
    int [][] correct5(int [][] x, int[] b){
        int[][] a = new int [49][5];
        
        for(int i=0;i<49;i++){
            for(int j=0;j<5;j++){
                a[i][j] = x[i][j];
            }
        }
        for(int j=0;j<5;j++){
            int min = 100000;
            for(int i=0;i<49;i++){
                if(b[i]==0){
                    if(min>x[i][j]){
                        min=x[i][j];
                    }
                }
            }
            for(int i=0;i<49;i++){
                if(b[i]==0){
                    a[i][j] = x[i][j]-min;
                }
            }
        }
        return a;
    }
    /*int choose(int [] x){
        int max=0;
        for(int i=0;i<49;i++){
            if(max<x[i]){
                max=x[i];
            }
        }
        int [] p = new int[49];
        int sum=0;
        if(max==0){
            return 99;
        }
        for(int i=0;i<49;i++){
            if(x[i]/max>0.5){
                p[i]=x[i];
            }
            else{
                p[i]=0;
            }
            sum+=p[i];
        }
        int y = (int)(Math.random()*sum);
        sum=0;
        int i=0;
        for(i=0;i<49;i++){
            sum+=p[i];
            if(sum>y){
                break;
            }
        }
        return i;
    }*/
    /*int[] bestMoves(int [] x){
        int max=0;
        for(int i=0;i<49;i++){
            if(max<x[i]){
                max=x[i];
            }
        }
        int []p = new int[49];
        int n=0;
        if(max==0){
            int [] t = new int[1];
            t[0]=99;
            return t;
        }
        for(int i=0;i<49;i++){
            double w = (((double)x[i])/((double)max)); 
            if(w>0.5){
                p[i]=x[i];
                n++;
            }
            else{
                p[i]=0;
            }
        }
        int[] t=new int[n];
        n=0;
        for(int i=0;i<49;i++){
            if(p[i]!=0){
                t[n]=i;
                n++;
            }
        }
        return t;
    }*/
    void decidelimit(int [] x){
	int n = countmoves(x);
	if(n<6){
	    limit = 7;
	}
	else if(n<30){
	    limit = 8;	
	}
	else {
	    limit=9;	
	}
    }
    int countmoves(int [] x){
	int n=0;	
	for(int i=0;i<49;i++){
	    if(x[i]!=0){
		n++;		
	    }	
	}
	return n;
    }
    int choose(double [] x,int h){
	int n1=1;
	if(h==2){
	    n1=-1;
	}
	int numwin=0;
	int numwin2=0;
	int numdraw=0;
	int numadvantage=0;
	int numdisadvantage=0;
	int numloss=0;
	int numloss2=0;
	for(int i=0;i<49;i++){
	    if(x[i]==n1){
		numwin++;
	    }
	    else if(x[i]==(n1*2)){
		numwin2++;
	    }
	    else if(x[i]==0){
		numdraw++;
	    }
	    else if(x[i]==-n1){
		numloss++;
	    }
	    else if(x[i]==(-n1*2)){
		numloss2++;
	    }
	    else if(x[i]>0&&x[i]<1){
		if(h==1){
		    numadvantage++;			
		}
		else{
		    numdisadvantage++;			
		}
	    }
	    else if(x[i]<0&&x[i]>-1){
		if(h==1){
		    numdisadvantage++;			
		}
		else{
		    numadvantage++;			
		}	
	    }
	}
	if(numwin!=0){
	    int t = (int)(Math.random()*numwin);
	    int c=0;
	    for(int i=0;i<49;i++){
		if(x[i]==n1){
		    c++;
		}
		if(c>t){
		    return i;
		}
	    }
	}
	if(numwin2!=0){
	    int t = (int)(Math.random()*numwin2);
	    int c=0;
	    for(int i=0;i<49;i++){
		if(x[i]==(n1*2)){
		    c++;
		}
		if(c>t){
		    return i;
		}
	    }
	}
	if(numadvantage!=0){
	    int t = (int)(Math.random()*numadvantage);
	    int c=0;
	    for(int i=0;i<49;i++){
		if(h==1&&x[i]>0&&x[i]<1){
		    c++;
		}
		if(h==2&&x[i]<0&&x[i]>-1){
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
	    for(int i=0;i<49;i++){
		if(x[i]==0){
		    c++;
		}
		if(c>t){
		    return i;
		}
	    }
	}
	if(numdisadvantage!=0){
	    int t = (int)(Math.random()*numdisadvantage);
	    int c=0;
	    for(int i=0;i<49;i++){
		if(h==1&&x[i]<0&&x[i]>-1){
		    c++;
		}
		if(h==2&&x[i]>0&&x[i]<1){
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
	    for(int i=0;i<49;i++){
		if(x[i]==-n1){
		    c++;
		}
		if(c>t){
		    return i;
		}
	    }
	}
	if(numloss2!=0){
	    int t = (int)(Math.random()*numloss2);
	    int c=0;
	    for(int i=0;i<49;i++){
		if(x[i]==(-n1*2)){
		    c++;
		}
		if(c>t){
		    return i;
		}
	    }
	}
	return 0;
    }
    int[] bestMoves(int [] x){
        int max=0;
        int [] y = new int [49];
        for(int i=0;i<49;i++){
            y[i]=x[i];
        }
        Arrays.sort(y);
        max = y[48];
        int n;
        for(n=48;n>=0;n--){
            double w = (((double)y[n])/((double)max)); 
            if(w<=0.5){
                break;
            }
        }
        n = 48-n;
        int q = 8;
        int l=8;
        if(n>q){
            int m;
            if(y[48-q]<y[49-q]){
                m=y[49-q];//all integers >=m
            }
            else{
                m = y[48-q];
                if(y[48]!=m){
                    int i;
                    for(i=49-q;i<49;i++){
                        if(y[i]>m){
                            break;
                        }
                    }
                    l=49-i;
                    m=y[i];
                }
            }
            int [] p = new int[l];
            int g=0;
            for(int i=0;i<49&&g<l;i++){
                if(x[i]>=m){
                    p[g]=i;
                    g++;
                }
            }
            return p;
        }
        else{
            int [] p = new int[n];
            int g=0;
            for(int i=0;i<49&&g<n;i++){
                double w = (((double)x[i])/((double)max)); 
                if(w>0.5){
                    p[g]=i;
                    g++;
                }
            }
            return p;
        }
    }
    /*int[] block(int [] A, int q){
        int[] blocks= new int [4];
        //blocks strores the number of 0 blocks at position 0, 1 blocks at 
        //position 1 and so on
        for(int i=0;i<4;i++){
            blocks[i]=0;
        }
        for(int i=0;i<6;i++){
            for(int j=0;j<=3;j++){
                int u = i*7+j;
                boolean isblocked = false;
                for(int k=0;k<4;k++){
                    if(A[u+k]==q){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(isblocked){
                    for(int k=0;k<4;k++){
                        if(A[u+k]!=q&&A[u+k]!=0){
                            h++;
                        }
                    }
                }
                blocks[h]++;
            }
        }
        for(int i=0;i<7;i++){
            for(int j=0;j<=2;j++){
                int u = j*7+i;
                boolean isblocked = false;
                for(int k=0;k<4;k++){
                    if(A[u+(k*7)]==q){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(isblocked){
                    for(int k=0;k<4;k++){
                        if(A[u+(k*7)]!=q&&A[u+(k*7)]!=0){
                            h++;
                        }
                    }
                }
                blocks[h]++;
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                int u = (i*7+j);
                boolean isblocked = false;
                for(int k=0;k<4;k++){
                    if(A[u+(k*8)]==q){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(isblocked){
                    for(int k=0;k<4;k++){
                        if(A[u+(k*8)]!=q&&A[u+(k*8)]!=0){
                            h++;
                        }
                    }
                }
                blocks[h]++;
            }
        }
        for(int i=0;i<3;i++){
            for(int j=3;j<7;j++){
                int u = (i*7+j);
                boolean isblocked = false;
                for(int k=0;k<4;k++){
                    if(A[u+(k*6)]==q){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(isblocked){
                    for(int k=0;k<4;k++){
                        if(A[u+(k*6)]!=q&&A[u+(k*6)]!=0){
                            h++;
                        }
                    }
                }
                blocks[h]++;
            }
        }
        return blocks;
    }*/
    int[] block5(int [] A, int q){
        int[] blocks= new int [5];
        for(int i=0;i<5;i++){
            blocks[i]=0;
        }
        for(int i=0;i<7;i++){
            for(int j=0;j<3;j++){
                int u = i*7+j;
                boolean isblocked = false;
                for(int k=0;k<5;k++){
                    if(A[u+k]==q){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(isblocked){
                    for(int k=0;k<5;k++){
                        if(A[u+k]!=q&&A[u+k]!=0){
                            h++;
                        }
                    }
                }
                blocks[h]++;
            }
        }
        for(int i=0;i<7;i++){
            for(int j=0;j<3;j++){
                int u = j*7+i;
                boolean isblocked = false;
                for(int k=0;k<5;k++){
                    if(A[u+(k*7)]==q){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(isblocked){
                    for(int k=0;k<5;k++){
                        if(A[u+(k*7)]!=q&&A[u+(k*7)]!=0){
                            h++;
                        }
                    }
                }
                blocks[h]++;
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int u = (i*7+j);
                boolean isblocked = false;
                for(int k=0;k<5;k++){
                    if(A[u+(k*8)]==q){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(isblocked){
                    for(int k=0;k<5;k++){
                        if(A[u+(k*8)]!=q&&A[u+(k*8)]!=0){
                            h++;
                        }
                    }
                }
                blocks[h]++;
            }
        }
        for(int i=0;i<3;i++){
            for(int j=4;j<7;j++){
                int u = (i*7+j);
                boolean isblocked = false;
                for(int k=0;k<5;k++){
                    if(A[u+(k*6)]==q){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(isblocked){
                    for(int k=0;k<5;k++){
                        if(A[u+(k*6)]!=q&&A[u+(k*6)]!=0){
                            h++;
                        }
                    }
                }
                blocks[h]++;
            }
        }
        return blocks;
    }
    /*int[] attack(int [] A, int q){
        int[] attacks = new int [4];
        for(int i=0;i<4;i++){
            attacks[i]=0;
        }
        for(int i=0;i<6;i++){
            for(int j=0;j<=3;j++){
                int u = i*7+j;
                boolean isblocked = false;
                for(int k=0;k<4;k++){
                    if(A[u+k]!=q&&A[u+k]!=0){//enemy occupied
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(!isblocked){
                    for(int k=0;k<4;k++){
                        if(A[u+k]==q){
                            h++;
                        }
                    }
                }
                
                if(h!=0){
                    attacks[h-1]++;
                }
            }
        }
        for(int i=0;i<7;i++){
            for(int j=0;j<=2;j++){
                int u = j*7+i;
                boolean isblocked = false;
                for(int k=0;k<4;k++){
                    if(A[u+(k*7)]!=q&&A[u+(k*7)]!=0){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(!isblocked){
                    for(int k=0;k<4;k++){
                        if(A[u+(k*7)]==q){
                            h++;
                        }
                    }
                }
                if(h!=0){
                    attacks[h-1]++;
                }
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                int u = (i*7+j);
                boolean isblocked = false;
                for(int k=0;k<4;k++){
                    if(A[u+(k*8)]!=q&&A[u+(k*8)]!=0){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(!isblocked){
                    for(int k=0;k<4;k++){
                        if(A[u+(k*8)]==q){
                            h++;
                        }
                    }
                }
                if(h!=0){
                    attacks[h-1]++;
                }
            }
        }
        for(int i=0;i<3;i++){
            for(int j=3;j<7;j++){
                int u = (i*7+j);
                boolean isblocked = false;
                for(int k=0;k<4;k++){
                    if(A[u+(k*6)]!=q&&A[u+(k*6)]!=0){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(!isblocked){
                    for(int k=0;k<4;k++){
                        if(A[u+(k*6)]==q){
                            h++;
                        }
                    }
                }
                if(h!=0){
                    attacks[h-1]++;
                }
            }
        }
        return attacks;
    }*/
    int[] attack5(int [] A, int q){
        int[] attacks = new int [5];
        for(int i=0;i<5;i++){
            attacks[i]=0;
        }
        for(int i=0;i<7;i++){
            for(int j=0;j<3;j++){
                int u = i*7+j;
                boolean isblocked = false;
                for(int k=0;k<5;k++){
                    if(A[u+k]!=q&&A[u+k]!=0){//enemy occupied
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(!isblocked){
                    for(int k=0;k<5;k++){
                        if(A[u+k]==q){
                            h++;
                        }
                    }
                }
                
                if(h!=0){
                    attacks[h-1]++;
                }
            }
        }
        for(int i=0;i<7;i++){
            for(int j=0;j<3;j++){
                int u = j*7+i;
                boolean isblocked = false;
                for(int k=0;k<5;k++){
                    if(A[u+(k*7)]!=q&&A[u+(k*7)]!=0){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(!isblocked){
                    for(int k=0;k<5;k++){
                        if(A[u+(k*7)]==q){
                            h++;
                        }
                    }
                }
                if(h!=0){
                    attacks[h-1]++;
                }
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int u = (i*7+j);
                boolean isblocked = false;
                for(int k=0;k<5;k++){
                    if(A[u+(k*8)]!=q&&A[u+(k*8)]!=0){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(!isblocked){
                    for(int k=0;k<5;k++){
                        if(A[u+(k*8)]==q){
                            h++;
                        }
                    }
                }
                if(h!=0){
                    attacks[h-1]++;
                }
            }
        }
        for(int i=0;i<3;i++){
            for(int j=4;j<7;j++){
                int u = (i*7+j);
                boolean isblocked = false;
                for(int k=0;k<5;k++){
                    if(A[u+(k*6)]!=q&&A[u+(k*6)]!=0){
                        isblocked = true;
                        break;
                    }
                }
                int h =0;
                if(!isblocked){
                    for(int k=0;k<5;k++){
                        if(A[u+(k*6)]==q){
                            h++;
                        }
                    }
                }
                if(h!=0){
                    attacks[h-1]++;
                }
            }
        }
        return attacks;
    }
    /*int winner(int [] A){
        for(int i=0;i<6;i++){
            for(int j=0;j<=3;j++){
                int u = i*7+j;
                if(A[u]==A[u+1]&&A[u+2]==A[u+3]&&A[u]==A[u+3]&&A[u]!=0){
                    winstreak [0] = (u);
                    winstreak [1] = (u+1);
                    winstreak [2] = (u+2);
                    winstreak [3] = (u+3);
                    return A[u];
                }
            }
        }
        for(int i=0;i<7;i++){
            for(int j=0;j<=2;j++){
                int u = j*7+i;
                if(A[u]==A[u+7]&&A[u+14]==A[u+21]&&A[u]==A[u+14]&&A[u]!=0){
                    winstreak [0] = (u);
                    winstreak [1] = (u+7);
                    winstreak [2] = (u+14);
                    winstreak [3] = (u+21);
                    return A[u];
                }
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                int u = (i*7+j);
                if(A[u+8]==A[u+16]&&A[u]==A[u+24]&&A[u]==A[u+8]&&A[u]!=0){
                    winstreak [0] = (u);
                    winstreak [1] = (u+8);
                    winstreak [2] = (u+16);
                    winstreak [3] = (u+24);
                    return A[u];
                }
            }
        }
        for(int i=0;i<3;i++){
            for(int j=3;j<7;j++){
                int u = (i*7+j);
                if(A[u]==A[u+6]&&A[u+12]==A[u+18]&&A[u]==A[u+12]&&A[u]!=0){
                    winstreak [0] = (u);
                    winstreak [1] = (u+6);
                    winstreak [2] = (u+12);
                    winstreak [3] = (u+18);
                    return A[u];
                }
            }
        }
        boolean k = false;
        for(int i=0;i<42;i++){
            if(A[i]==0){
                k=true;
                break;
            }
        }
        if(!k){
            return 4;
        }
        return 0;
    }*/
    int winner5(int [] A){
        for(int i=0;i<7;i++){
            for(int j=0;j<3;j++){
                int u = i*7+j;
                int y = A[u];
                if(y!=0){
                    boolean g = true;
                    for(int k=1;k<5;k++){
                        if(A[u+k]!=y){
                            g=false;
                            break;
                        }
                    }
                    if(g){
                        for(int k=0;k<5;k++){
                            winstreak5[i]=(u+k);
                        }
                        return A[u];
                    }
                }
            }
        }
        for(int i=0;i<7;i++){
            for(int j=0;j<3;j++){
                int u = j*7+i;
                int y = A[u];
                if(y!=0){
                    boolean g = true;
                    for(int k=1;k<5;k++){
                        if(A[u+(k*7)]!=y){
                            g=false;
                            break;
                        }
                    }
                    if(g){
                        for(int k=0;k<5;k++){
                            winstreak5[i]=(u+(k*7));
                        }
                        return A[u];
                    }
                }
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int u = (i*7+j);
                int y = A[u];
                if(y!=0){
                    boolean g = true;
                    for(int k=1;k<5;k++){
                        if(A[u+(k*8)]!=y){
                            g=false;
                            break;
                        }
                    }
                    if(g){
                        for(int k=0;k<5;k++){
                            winstreak5[i]=(u+(k*8));
                        }
                        return A[u];
                    }
                }
            }
        }
        for(int i=0;i<3;i++){
            for(int j=4;j<7;j++){
                int u = (i*7+j);
                int y = A[u];
                if(y!=0){
                    boolean g = true;
                    for(int k=1;k<5;k++){
                        if(A[u+(k*6)]!=y){
                            g=false;
                            break;
                        }
                    }
                    if(g){
                        for(int k=0;k<5;k++){
                            winstreak5[i]=(u+(k*6));
                        }
                        return A[u];
                    }
                }
            }
        }
        boolean k = false;
        for(int i=0;i<49;i++){
            if(A[i]==0){
                k=true;
                break;
            }
        }
        if(!k){
            return 4;
        }
        return 0;
    }
    void display(int [] h){
        System.out.println();
            for(int i=0;i<49;i++){
            if(h[i]==0){
                System.out.print("   ");
            }
            else if(h[i]==1){
                System.out.print(" X ");
            }
            else if(h[i]==2){
                System.out.print(" O ");
            }
            if(i%7!=6){
                System.out.print("|");
            }
            else{
                System.out.println();
                if(i!=48){
                    System.out.println("___________________________");
                }
            }
        }
        System.out.println();
        System.out.println();
    }
    void displayGoodMoves(int [] h){
        System.out.println();
        int[] b = new int[49];
        for(int i=0;i<h.length;i++){
            b[h[i]]=1;
        }
        for(int i=0;i<49;i++){
            if(b[i]==0){
                System.out.print("   ");
            }
            else if(b[i]==1){
                System.out.print(" @ ");
            }
            if(i%7!=6){
                System.out.print("|");
            }
            else{
                System.out.println();
                if(i!=48){
                    System.out.println("___________________________");
                }
            }
        }
        System.out.println();
        System.out.println();
    }
    void displayMoveValue(int [] h){
        System.out.println();
        for(int i=0;i<49;i++){
            System.out.print("\t"+h[i]+"\t");
            if(i%7!=6){
                System.out.print("|");
            }
            else{
                System.out.println();
                if(i!=48){
                    System.out.println("___________________________");
                }
            }
        }
        System.out.println();
        System.out.println();
    }
}
