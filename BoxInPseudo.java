import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BoxInPseudo {

	static char[][] grid;
	static int row;
	static int c;
	
	public static Pair findChar(int a, int b, char c){
		for(int i=0; i<row; i++)
			for(int j=0; j<c; j++)
				if(grid[i][j]==c && i!=a && j!=b)
					return new Pair(i,j);
		return new Pair(-1,-1);
	}
	
	public static int getSolution(Pair p){
		int[][] dist = new int[row][c];
		for(int i=0; i<row; i++)
			for(int j=0; j<c; j++){
				dist[i][j]=-1;
				if(grid[i][j]==66)
					dist[i][j]=0;
			}
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(p);
		while(queue.size()>0){
			Pair p1 = queue.remove();
			char c1 = grid[p1.getR()][p1.getC()];
			if(c1==88)
				return dist[p1.getR()][p1.getC()];
			ArrayList<Pair> possible = p1.findMoves(grid, dist);
			for(Pair p2: possible){
				queue.add(p2);
				char c2 = grid[p2.getR()][p2.getC()];
				if(p1.isTeleport(grid) && c2==c1)
					dist[p2.getR()][p2.getC()]=dist[p1.getR()][p1.getC()];
				else
					dist[p2.getR()][p2.getC()]=dist[p1.getR()][p1.getC()]+1;
			}
		}
		return -1;
		
	}
	
	public static void print(char[][] s){
		for(char[] r: s){
			for(char c: r)
				System.out.print(c);
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String[] size = r.readLine().split(" ");
		row = Integer.parseInt(size[0]);
		c = Integer.parseInt(size[1]);
		while(row!=0){
			grid = new char[row][c];
			for(int i=0; i<row; i++){
				String str = r.readLine();
				grid[i] = str.toCharArray();
			}
			Pair bob = findChar(-1,-1, 'B');
			int solution = getSolution(bob);
			System.out.println(solution);
			size = r.readLine().split(" ");
			row = Integer.parseInt(size[0]);
			c = Integer.parseInt(size[1]);
		}
	}
	
	
}

class Pair {
	private int r;
	private int c;
	
	public Pair(int x, int y){
		this.r=x;
		this.c=y;
	}
	
	public int getR(){ return r; }
	public int getC(){ return c; }
	
	public boolean equals(Pair p){
		return (r==p.getR() && c==p.getC());
	}
	
	public boolean isValid(int a, int b, char[][] c){
		if(c[a][b]==46 && !(c[a][b]==87))
			return false;
		return true; 
	}
	
	public boolean isTeleport(char[][] c){
		for(int i=0; i<c.length; i++)
			for(int j=0; j<c[0].length; j++)
				if(c[i][j]>47 && c[i][j]<58)
					return true;
		return false;
	}
	
	public ArrayList<Pair> findMoves(char[][] grid, int[][] d){
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		if(inBounds(r,c+1, grid.length, grid[0].length) && d[r][c+1]==-1 && isValid(r,c+1,grid)){
			Pair p = new Pair(r,c+1);
			pairs.add(p);
		}
		if(inBounds(r,c-1, grid.length, grid[0].length) && d[r][c-1]==-1 && isValid(r,c-1,grid)){
			Pair p = new Pair(r,c-1);
			pairs.add(p);
		}
		if(inBounds(r+1,c, grid.length, grid[0].length) && d[r+1][c]==-1 && isValid(r+1,c,grid)){
			Pair p = new Pair(r+1,c);
			pairs.add(p);
		}
		if(inBounds(r-1,c, grid.length, grid[0].length) && d[r-1][c]==-1 && isValid(r-1,c,grid)){
			Pair p = new Pair(r-1,c);
			pairs.add(p);
		}
		return pairs;
	}
	
	public boolean inBounds(int r, int c, int w, int l){
		return (r>-1 && r<l) && (c>-1 && c<w);
	}
	
	public String toString() { return "" + r + "," + c; }
}
