import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Minesweeper {
	
	public static void print(String[][] a){
		for(int r=0; r<a.length; r++){
			for(int c=0; c<a[0].length; c++)
				System.out.print(a[r][c]);
		System.out.println();
		}
	}
	
	public static void print(int[][] a){
		for(int r=0; r<a.length; r++){
			for(int c=0; c<a[0].length; c++)
				System.out.print(a[r][c]);
		System.out.println();
		}
	}
	
	public static int[][] turnInt(String[][] a){
		int[][] ret = new int[a.length][a[0].length];
		for(int r=0; r<a.length; r++){
			for(int c=0; c<a[0].length; c++)
				if(a[r][c].equals("*"))
					ret[r][c]=-1;			
		}
		return ret;			
	}
	
	public static String[][] turnString(int[][] a){
		String[][] ret = new String[a.length][a[0].length];
		for(int r=0; r<a.length; r++)
			for(int c=0; c<a[0].length; c++){
				if(a[r][c]==-1)
					ret[r][c]="*";
				else
					ret[r][c]= "" + a[r][c];
			}
		return ret;			
	}
	
	public static ArrayList<int[]> inBounds(int[][] nums, ArrayList<int[]> pos, int maxRow, int maxCol){
		//clear out of bounds
		for(int i=pos.size()-1; i>-1; i--)
				if(pos.get(i)[0]<0 || pos.get(i)[1]<0 || pos.get(i)[0]>=maxRow || pos.get(i)[1]>=maxCol)
					pos.remove(pos.get(i));
		
		//clear potential -1
		for(int i=pos.size()-1; i>-1; i--)
			if(nums[pos.get(i)[0]][pos.get(i)[1]]==-1)
				pos.remove(pos.get(i));
		
		return pos;
	}
	

	public static void main(String[] args) throws IOException{
		 BufferedReader r=new BufferedReader(new InputStreamReader(System.in));
			String s=r.readLine();
			String[] dimensions = s.split(" ");
			int row = Integer.parseInt(dimensions[0]);
			int col = Integer.parseInt(dimensions[1]);
			int field = 0; //counter
			
			while(row!=0 && col!=0){
				field++;
				String[][] retString = new String[row][col];
				//process input
				for(int i=0; i<row; i++){
					String str=r.readLine();
					for(int j=0; j<col; j++)
						retString[i][j] = str.substring(j,j+1);
				}
				
				//input to int
				int[][] retInt = turnInt(retString);
				//check each square
				for(int k=0; k<row; k++)
					for(int l=0; l<col; l++)
						if(retInt[k][l]==-1){
							//adjacents
							ArrayList<int[]> bounds = new ArrayList<int[]>();
							int[] temp1 = {k,l-1}; bounds.add(temp1);
							int[] temp2 = {k-1,l}; bounds.add(temp2);
							int[] temp3 = {k+1,l}; bounds.add(temp3);
							int[] temp4 = {k,l+1}; bounds.add(temp4);
							//diagonals
							int[] temp5 = {k-1,l+1}; bounds.add(temp5);
							int[] temp6 = {k+1,l+1}; bounds.add(temp6);
							int[] temp7 = {k-1,l-1}; bounds.add(temp7);
							int[] temp8 = {k+1,l-1}; bounds.add(temp8);
							
							//increment bounded adjacents
							bounds = inBounds(retInt,bounds,row,col);
							for(int m=0; m<bounds.size(); m++)
								retInt[bounds.get(m)[0]][bounds.get(m)[1]]++;
						
						
				}
				
				//turn into String[][]
				retString = turnString(retInt);	
				System.out.println();
				System.out.println("Field #" + field + ":");
				print(retString);
				
				//restart
				s=r.readLine();
				dimensions = s.split(" ");
				row = Integer.parseInt(dimensions[0]);
				col = Integer.parseInt(dimensions[1]);
			}
			
	}
}
