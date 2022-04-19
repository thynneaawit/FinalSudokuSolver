package application;

import javafx.scene.paint.Color;

public class SudokuSolver {
	private static int SUDOKU_SIZE = 9;


	   
	  


	public  boolean Solve(int[][] sudoku, int counter) {
	for(int row = 0; row < SUDOKU_SIZE; row++) {
		for(int column = 0; column < SUDOKU_SIZE; column++) {
	if(sudoku[row][column]==0) {
		for(int q = 1; q <= 9; q++) {
			if(workingSudoku(sudoku, q, row, column)) {
			sudoku[row][column]=q;
			setMain(false, counter, q);
			if(Solve(sudoku, counter+1)) {
				return true;
			}
			else {
		sudoku[row][column]=0;
		setMain(true, counter, q);
			}
	}
	}
	return false;
	}
		}
}
	return true;
	}
		
	private static boolean workingSudoku(int[][] sudoku, int num, int row, int column) {
	return !checkRow(sudoku, num, row) && !checkColumn(sudoku, num, column) && !checkSudoku(sudoku, num, row, column);
	}
	
	private static boolean checkRow(int[][] sudoku, int num, int row) {
	for(int i = 0; i < SUDOKU_SIZE; i++) {
		if(sudoku[row][i]== num) {
			return true;
		}
	}
	return false;
	}
	private static boolean checkColumn(int[][] sudoku, int num, int column) {
	for(int i = 0; i < SUDOKU_SIZE; i++) {
		if(sudoku[i][column]== num) {
			return true;
		}
	}
	return false;
	}
	private static boolean checkSudoku(int[][] sudoku, int num, int row, int column) {
	int subsectionrowone = row - row%3;
	int subsectioncolumnone = column - column%3;
	for(int i = subsectionrowone; i < subsectionrowone; i++) {
		for(int j = subsectioncolumnone; j < subsectioncolumnone; j++) {
			if(sudoku[i][j]== num) {
				return true;
			}
		}
	}
	return false;
	}
	
	public boolean startSolving(int[][] sudoku) {
		if(Solve(sudoku, 0)) {
			return true;
		}
		else {
			return false;
		}
	}

	public void showSudoku(int [][] sudoku) {
		for(int i = 0; i < SUDOKU_SIZE; i++) {
			if(i%3 == 0 && i!= 0) {
				System.out.println("----------------------------------\n");
			}
			for(int j = 0; j < 9; j++) {
				if(j%3 == 0 && j!= 0) {
					System.out.println(" | ");
				}
				System.out.print("" + sudoku[i][j] + "");
			}
			System.out.println();
		}
		System.out.println("\n\n__________________________________________\n\n");
	}

private void setMain(boolean one, int position, int num) {
	String s = Integer.toString(num);
	Color color = Color.GREEN;
	if(one) {
		color = Color.RED;
	}
	Main.setColorOfRect(color, position);
	Main.setTextOfRect(s, position);

}

}
