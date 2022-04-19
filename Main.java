package application;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextBoundsType;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Main extends Application {
	
	static ArrayList<Rectangle> rects = new ArrayList<>();
	
	static ArrayList<Text> texts = new ArrayList<>();
	
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 297, 297);
		
		createBoard();
		
		for(int i =0; i < rects.size(); i++) {
			pane.getChildren().addAll(rects.get(i), texts.get(i));
		}
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				System.out.println("event activated");
				handleEvent(keyEvent);
			}
		});
		
		primaryStage.setTitle("Sudoku Solver");
		primaryStage.setScene(scene);
		primaryStage.show();
		}
	
	public void handleEvent(KeyEvent key) {
		System.out.println("event activated");
		callSudokuSolver();
	}
	
	public void createBoard() {
		for(int x = 0; x < 288; x+= 32) {
			for(int y = 0; y < 288; y+=32) {
				Rectangle r = new Rectangle(x, y, 32, 32);
					Text  text 		= createText("0");
					text.setX(x);
					text.setY(y+32);
					r.setFill(Color.WHITE);
					r.setStroke(Color.BLACK);
					r.setOpacity(0.5);
					
					rects.add(r);
					texts.add(text);
				}
			}
		}
		
		public static void setTextOfRect(String s, int position) {
			texts.get(position).setText(s);
		}
		
		public static void setColorOfRect(Color col, int position) {
			rects.get(position).setFill(col);
		}
		
		private Text createText(String string) {
			Text text = new Text(string);
			text.setBoundsType(TextBoundsType.VISUAL);
			text.setStyle("-fx-font-family: \"Times New Roman\";" + "-fx-font-size: 16px;");
			return text;
		}
		
		private void callSudokuSolver() {
			int[][] board =
		        {{2,0,5,0,0,0,0,0,0},
		        {3,0,8,6,0,0,9,0,0},
		        {0,0,0,1,0,0,4,0,0},
		        {0,0,0,0,5,0,0,1,0},
		        {0,0,0,0,9,0,0,2,0},
		        {8,7,0,0,2,0,0,0,0},
		        {0,0,0,0,8,9,0,0,3},
		        {0,0,6,0,0,3,0,0,5},
		        {5,0,4,0,0,0,0,0,1}};
			
SudokuSolver sudokuSolver = new SudokuSolver();

if(!sudokuSolver.startSolving(board)) {
	System.out.println("No solution");
}
else {
	System.out.println("Solved");
}
		
		new Thread() {
	        public void run() {
	            SudokuSolver sudokuSolver = new SudokuSolver();
	            sudokuSolver.startSolving(board);
	        }
	    }.start();
}

	public static void main(String[] args) {
		launch(args);
	}
}
