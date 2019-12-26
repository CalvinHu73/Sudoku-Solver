public class Sudoku
{
	private int[][] board = new int[9][9];
	private final int[][] boardToSolve;
	
	/**
	 * @param boardToSolve must be a 9x9 int array
	 */
	public Sudoku(int[][] boardToSolve)
	{
		if (boardToSolve.length != 9)
		{
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < 9; i++)
		{
			if (boardToSolve[i].length != 9)
			{
				throw new IllegalArgumentException();
			}
		}
		this.boardToSolve = boardToSolve;
		this.board = this.boardToSolve;
	}
	
	/**
	 * @param number int between 1 and 9  inclusive
	 * @param row int between 0-8 inclusive
	 * @return
	 */
	public boolean isInRow(int number, int row)
	{
		for (int i = 0; i < 9; i++)
		{
			if (this.board[row][i] == number)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param number int between 1 and 9  inclusive
	 * @param column int between 0-8 inclusive
	 * @return
	 */
	public boolean isInColumn(int number, int column)
	{
		for (int i = 0; i < 9; i++)
		{
			if (this.board[i][column] == number)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param number int between 1 and 9  inclusive
	 * @param row int between 0-8 inclusive
	 * @param column int between 0-8 inclusive
	 * @return
	 */
	public boolean isInBox(int number, int row, int column)
	{
		int boxStartRow = row / 3 * 3;
		int boxStartColumn = column / 3 * 3;
		for (int i = boxStartRow; i < boxStartRow + 3; i++)
		{
			for (int j = boxStartColumn; j < boxStartColumn + 3; j++)
			{
				if (this.board[i][j] == number)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param number int between 1 and 9  inclusive
	 * @param row int between 0-8 inclusive
	 * @param column int between 0-8 inclusive
	 * @return
	 */
	public boolean isOk(int number, int row, int column)
	{
		return !isInRow(number, row) && !isInColumn(number, column) && !isInBox(number, row, column);
	}
	
	public boolean solve()
	{
		for (int r = 0; r < 9; r++)
		{
			for (int c = 0; c < 9; c++)
			{
				if (this.board[r][c] == 0)
				{
					for (int n = 1; n <= 9; n++)
					{
						if (isOk(n, r, c))
						{
							this.board[r][c] = n;
							if (solve())
							{
								return true;
							}
							else
							{
								this.board[r][c] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	public void display()
	{
		for (int i = 0; i < 9; i++)
		{
			System.out.print("[" + this.board[i][0]);
			for (int j = 1; j < 9; j++)
			{
				System.out.print(", " + this.board[i][j]);
			}
			System.out.println("]");
		}
	}
	
	public static void main(String[] args)
	{
		int[][] board = {{0,0,7,0,5,9,2,1,0}
						,{2,0,0,0,6,0,7,8,0}
						,{0,0,0,0,3,0,0,9,0}
						,{5,0,0,0,1,3,6,0,0}
						,{3,2,1,0,0,0,8,4,7}
						,{0,0,4,7,2,0,0,0,1}
						,{0,5,0,0,4,0,0,0,0}
						,{0,4,3,0,8,0,0,0,2}
						,{0,1,9,5,7,0,3,0,0}};
		Sudoku s = new Sudoku(board);
		s.solve();
		s.display();
	}
}