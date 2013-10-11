import java.util.Date;
import java.util.Random;

public class Maps {
	public Maps(){
		bombs = 1;
		score = 0;
		sprits = 3;
		levelnum = 0;
		int counter1=0;
		int counter2=0;
		int counter3=0;
		boolean [] m1=new boolean[9]; 
		boolean [] m2=new boolean[9]; 
		boolean [] m3=new boolean[9]; 
		Random rand=new Random();
		rand.setSeed(new Date().getTime());
		int temp=0;
		for(int i=0;i<num1.length;i++){
			for(int j=0;j<num1[0].length;j++){
				num1[i][j]=0;
				num2[i][j]=0;
				num3[i][j]=0;
				if(map[i][j]==2){
					temp=((rand.nextInt()%9)+9)%9;
					while(counter1<9&&m1[temp]){
						
						temp=((rand.nextInt()%9)+9)%9;
					}
					if(counter1<9){
						num1[i][j]=temp+1;
						m1[temp]=true;
						counter1++;
					}
				}
				if(map1[i][j]==2){
					temp=((rand.nextInt()%9)+9)%9;
					while(counter2<9&&m2[temp]){
						
						temp=((rand.nextInt()%9)+9)%9;
					}
					if(counter2<9){
						num2[i][j]=temp+1;
						m2[temp]=true;
						counter2++;
					}
				}
				if(map2[i][j]==2){
					temp=((rand.nextInt()%9)+9)%9;
					while(counter3<9&&m3[temp]){
						
						temp=((rand.nextInt()%9)+9)%9;
					}
					if(counter3<9){
						num3[i][j]=temp+1;
						m3[temp]=true;
						counter3++;
					}
				}
			}
		}
		int num1;
		int num2;
		for(int co=0;co<3;co++){
		do{
		num1=(((((rand.nextInt()%9)+9)%9)+1)*10)+((((rand.nextInt()%9)+9)%9)+1);
		num2=(((((rand.nextInt()%9)+9)%9)+1)*10)+((((rand.nextInt()%9)+9)%9)+1);
		}while(num1==num2);
		int oprt=(((rand.nextInt()%3)+3)%3);
		int res=0;
		switch(oprt){
		case 0:
			res=num1+num2 ;
			all_fun[co]=num1+"+"+num2+"="+res;
			break;
		case 1:
			if(num1>num2){
				res=num1-num2;
				all_fun[co]=num1+"-"+num2+"="+res;
			}
			else{
				res=num2-num1;
				all_fun[co]=num2+"-"+num1+"="+res;
			}
			break;
		case 2:
				res=num1*num2;
				all_fun[co]=num1+"*"+num2+"="+res;
			break;
		}
		
		
		int ch=(((rand.nextInt()%all_fun[co].length())+all_fun[co].length())%all_fun[co].length());
		while(all_fun[co].charAt(ch)=='0'||all_fun[co].charAt(ch)=='+'||all_fun[co].charAt(ch)=='-'||all_fun[co].charAt(ch)=='*'||all_fun[co].charAt(ch)=='='){
			ch=(((rand.nextInt()%all_fun[co].length())+all_fun[co].length())%all_fun[co].length());}
		solution[co]=Integer.parseInt((all_fun[co].charAt(ch)+""));
		all_fun[co]=all_fun[co].substring(0,ch)+"?"+all_fun[co].substring(ch+1,all_fun[co].length());
		}
		
	}
	
	static int w = 12;
	static int h = 10;
	static int icell = 40;
	static int board = 40;
	static int bombs = 1;
	static String fun1 = "33+?8=101";
	static String fun2 = "33+?8=101";
	static String fun3 = "33+?8=101";
	static int[] solution = { 1, 2, 3 };
	static int score = 0;
	static int sprits = 3;
	static int playerx = 1;
	static int playery = 1;
	static int[][] ghost = { { 5, 1 }, { 1, 7 }, { 8, 7}, { 7, 2 }, { 5, 8 },
			{ 2, 2 }, { 2, 2 }, { 2, 2 }, { 2, 2 }, { 2, 2 }, { 2, 2 } };
	static int[][] ghost1 = { { 8, 9 }, { 8, 6 }, { 7, 2 }, { 3, 1}, { 1, 7 },
		{ 3, 9 }, { 6, 10 }, { 2, 2 }, { 2, 2 }, { 2, 2 }, { 2, 2 } };
	static int[][] ghost2 = { { 1, 8 }, { 1, 5 }, { 5, 10 }, { 8, 1 }, { 8, 9 },
			{ 8, 6 }, { 5, 3 }, { 4, 5 }, { 4, 8 }, { 5, 4 }, { 2, 3 } };
	static int[] numofghost = { 5, 7, 10 };
	static int levelnum = 0;
	static int[] ghost_move = { 0, 1, 2, 3, 0, 1, 2, 3, 0, 1 };

	static int[][] map = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
							{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
							{ 1, 2, 0, 0, 0, 2, 1, 0, 1, 0, 2, 1 },
							{ 1, 2, 0, 1, 0, 1, 0, 0, 2, 0, 0, 1 },
							{ 1, 0, 1, 0, 2, 0, 1, 2, 0, 0, 2, 1 },
							{ 1, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 1 },
							{ 1, 2, 0, 1, 2, 0, 0, 0, 2, 0, 0, 1 },
							{ 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 2, 1 },
							{ 1, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 1 },
							{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	static int[][] map1 = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
							{ 1, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 1 },
							{ 1, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1 },
							{ 1, 0, 0, 2, 1, 0, 2, 0, 2, 0, 0, 1 },
							{ 1, 2, 1, 0, 2, 0, 0, 0, 0, 2, 0, 1 },
							{ 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1 },
							{ 1, 2, 2, 2, 0, 1, 0, 0, 0, 0, 0, 1 },
							{ 1, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 1 },
							{ 1, 2, 0, 0, 1, 2, 0, 0, 0, 0, 2, 1 },
							{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
	
	static int[][] map2 = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
							{ 1, 0, 2, 0, 0, 0, 0, 0, 0, 1, 2, 1 },
							{ 1, 0, 0, 0, 1, 0, 2, 0, 0, 1, 0, 1 },
							{ 1, 2, 0, 1, 2, 1, 2, 2, 0, 2, 0, 1 },
							{ 1, 1, 0, 1, 0, 0, 0, 2, 0, 2, 1, 1 },
							{ 1, 0, 2, 0, 2, 1, 0, 1, 0, 0, 0, 1 },
							{ 1, 2, 2, 0, 0, 0, 2, 0, 2, 0, 0, 1 },
							{ 1, 0, 2, 1, 0, 0, 0, 1, 0, 1, 1, 1 },
							{ 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 2, 1 },
							{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	static int[][][] all_map = { map, map1, map2 };

	static int[][] num1 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	static int[][] num2 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	static int[][] num3 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	static int[][][] all_num = { num1, num2, num3 };
	static int[][][] all_ghost = { ghost, ghost1, ghost2 };
	static String[] all_fun = { fun1, fun2, fun3 };
}
