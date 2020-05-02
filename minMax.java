import java.util.ArrayList;
import java.util.*;

public class minMax{

//representing values for human and computer
static int human = -1;
static int computer = +1;

//return the result of the game
static int win(int[][] state,int player){
int position[][] = {
{state[0][0],state[0][1],state[0][2]},
{state[1][0],state[1][1],state[1][2]},
{state[2][0],state[2][1],state[2][2]},
{state[0][0],state[1][0],state[2][0]},
{state[0][1],state[1][1],state[2][1]},
{state[0][2],state[1][2],state[2][2]},
{state[0][0],state[1][1],state[2][2]},
{state[2][0],state[1][1],state[0][2]}
};
for(int i=0;i<=7;i++){
int count = 0;
for(int j=0;j<=2;j++){
if(position[i][j] == player){count += 1;}
}
if(count == 3){return 1;}
} 
return 0;
}

//returns scores according to the board state
static int evaluate(int[][] state){
int score = 0;
if(win(state,computer) == 1){
score = +1;
}else if(win(state,human) == 1){
score = -1;
}else{
score = 0;
}
return score;
}

//returns result of game whether x or o wins
static int doom(int[][] state){
if(win(state,human) == 1){
return 1;
}else if(win(state,computer) == 1){return 1;}
return 0;
}

//returns position of empty cell
static ArrayList<ArrayList<Integer>> emptyCell(int[][] state){
ArrayList<ArrayList<Integer>> cell = new ArrayList<ArrayList<Integer>>();
for(int i = 0;i<=2;i++){
for(int j = 0;j<=2;j++){
if(state[i][j] == 0){
ArrayList<Integer> cell2 = new ArrayList<Integer>();
cell2.add(i);
cell2.add(j);
cell.add(cell2);
}
}
}
return cell;
}

//MINMAX FUNCTION 
static ArrayList<Integer> MINMAX(int[][] state,int depth,int player){
ArrayList<Integer> best = new ArrayList<Integer>();
Integer positiveInfinity = Integer.MAX_VALUE;
Integer negativeInfinity = Integer.MIN_VALUE;
if(player == computer){
best.add(-1);
best.add(-1);
best.add(negativeInfinity);
}else{
best.add(-1);
best.add(-1);
best.add(positiveInfinity);
}
if(depth == 0 || doom(state) == 1){
ArrayList<Integer> retScore = new ArrayList<Integer>();
int score;
score = evaluate(state);
retScore.add(-1);
retScore.add(-1);
retScore.add(score); 
return retScore;
}
ArrayList<ArrayList<Integer>> eCell = new ArrayList<ArrayList<Integer>>();
eCell = emptyCell(state);
for(int i = 0;i<eCell.size();i++){
int x,y;
ArrayList<Integer> Ascore = new ArrayList<Integer>();
x = eCell.get(i).get(0);
y = eCell.get(i).get(1);
//System.out.println(state[x][y]);
state[x][y] = player;
Ascore = MINMAX(state,depth - 1, -1 * player);
//System.out.println(Ascore);
state[x][y] = 0;
//System.out.println(state[x][y]);
Ascore.set(0,x);
Ascore.set(1,y);
//System.out.println(Ascore);
if(player == computer){
if(Ascore.get(2) > best.get(2)){
best = Ascore;
}}else{
if(Ascore.get(2) < best.get(2)){ 
best = Ascore;
}}

}

return best; 
}






//main 
public static void main(String []args){
int depth;
int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
for(int i = 0;i<=2;i++){
for(int j = 0;j<=2;j++){
System.out.print(board[i][j]);
}
System.out.print("\n");
}
System.out.print("--------------------------\n");
while(true){
System.out.println("HUMAN TURN");
Scanner sc = new Scanner(System.in);
System.out.println("POSITION 0 : ");
int p1 = sc.nextInt();
System.out.println("POSITION 1 : ");
int p2 = sc.nextInt();
board[p1][p2] = -1;
for(int i = 0;i<=2;i++){
for(int j = 0;j<=2;j++){
System.out.print(board[i][j]);
}
System.out.print("\n");
}
System.out.println("HUMAN WINS : " + String.valueOf(evaluate(board)));
System.out.print("--------------------------\n");
System.out.print("MACHINE's TURN \n");
ArrayList<ArrayList<Integer>> eCell = new ArrayList<ArrayList<Integer>>();
eCell = emptyCell(board);
depth = eCell.size();
ArrayList<Integer> pos = new ArrayList<Integer>();
pos = MINMAX(board,depth,computer);
board[pos.get(0)][pos.get(1)] = 1;
for(int i = 0;i<=2;i++){
for(int j = 0;j<=2;j++){
System.out.print(board[i][j]);
}
System.out.print("\n");
}
System.out.println("MACHINE WINS : " + String.valueOf(evaluate(board)));
}
}
}

