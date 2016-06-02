package pratoFiorito;

import java.io.Serializable;

public class Matrice implements Serializable
{	
	private Block [][]matrice;
	private int rows,columns;
	private boolean empty;
	
	public Matrice(int n, int m)
	{
		rows=n;
		columns=m;
		
		matrice=new Block[rows][columns];
	}
	
	public void fillMatrice(Block obj, int i, int j)
	{
			matrice[i][j]=obj;
	}
	
	public void clearMatrice()
	{
		matrice=new Block[rows][columns];
	}
	
	public Block getElement(int x,int y)
	{
		if(x >= 0 && x < rows && y >= 0 && y < columns)
			return matrice[x][y];
		else
			return null;
	}

	
	/*public void showMatrice()
	{
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
				System.out.print(matrice[i][j]);
				System.out.println();
		}
	}*/
	
	
	public Boolean isEmptyAtXY(int x, int y)
	{	
		Object blocco=new Object();
		blocco=getElement(x,y);
		
		if(blocco==null)
			empty=true;
		else 
			empty=false;
		return empty;	
	}
	
	public int getRows()
	{
		return rows;
	}
	
	public int getColumns()
	{
		return columns;
	
	}
}


