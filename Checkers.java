
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;




public class Checkers extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
  
    
    CheckersBoard board; 
    private Button newGameButton;  
    
    private Button resignButton;  
                                   
    private Label message;  

    public void start(Stage stage) {

       
        
        message = new Label("Click \"New Game\" to begin.");
        message.setTextFill( Color.rgb(100,255,100) ); // Light green.
        message.setFont( Font.font(null, FontWeight.BOLD, 18) );
      
        newGameButton = new Button("New Game");
        resignButton = new Button("Resign");

        board = new CheckersBoard();
        board.drawBoard();  
        

        board.relocate(20,20);
        newGameButton.relocate(370, 120);
        resignButton.relocate(370, 200);
        message.relocate(20, 370);
        
      
        
        resignButton.setManaged(false);
        resignButton.resize(100,30);
        newGameButton.setManaged(false);
        newGameButton.resize(100,30);
        
      
        
        Pane root = new Pane();
        
        root.setPrefWidth(500);
        root.setPrefHeight(420);
        
      
        root.getChildren().addAll(board, newGameButton, resignButton, message);
        root.setStyle("-fx-background-color: darkgreen; "
                           + "-fx-border-color: darkred; -fx-border-width:3");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Checkers!");
        stage.show();

    } 
    private class CheckersBoard extends Canvas {

        CheckersData board; 

     

        CheckersBoard() {
            super(324,324); 
            board = new CheckersData();
       
        }

      
        public void drawBoard() {
            
            GraphicsContext g = getGraphicsContext2D();
            g.setFont( Font.font(18) );

          
            g.setStroke(Color.DARKRED);
            g.setLineWidth(2);
            g.strokeRect(1, 1, 322, 322);

          

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if ( row % 2 == col % 2 )
                    {
                        g.setFill(Color.LIGHTGRAY);
                    }
                    else
                    {
                        g.setFill(Color.GRAY);
                    }
                    g.fillRect(2 + col*40, 2 + row*40, 40, 40);
                    switch (board.pieceAt(row,col)) {
                    case CheckersData.RED:
                        g.setFill(Color.RED);
                        g.fillOval(8 + col*40, 8 + row*40, 28, 28);
                        break;
                    case CheckersData.BLACK:
                        g.setFill(Color.BLACK);
                        g.fillOval(8 + col*40, 8 + row*40, 28, 28);
                        break;
                    case CheckersData.RED_KING:
                        g.setFill(Color.RED);
                        g.fillOval(8 + col*40, 8 + row*40, 28, 28);
                        g.setFill(Color.WHITE);
                        g.fillText("K", 15 + col*40, 29 + row*40);
                        break;
                    case CheckersData.BLACK_KING:
                        g.setFill(Color.BLACK);
                        g.fillOval(8 + col*40, 8 + row*40, 28, 28);
                        g.setFill(Color.WHITE);
                        g.fillText("K", 15 + col*40, 29 + row*40);
                        break;
                    }
                }
            }

         
           
        }  

   
    } 

    private static class CheckersData {

   

        static final int
                    EMPTY = 0,
                    RED = 1,
                    RED_KING = 2,
                    BLACK = 3,
                    BLACK_KING = 4;

        int[][] board;  
        CheckersData() {
            board = new int[8][8];
            setUpGame();
        }

        void setUpGame() {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if ( row % 2 == col % 2 ) {
                        if (row < 3)
                            board[row][col] = BLACK;
                        else if (row > 4)
                            board[row][col] = RED;
                        else
                            board[row][col] = EMPTY;
                    }
                    else {
                        board[row][col] = EMPTY;
                    }
                }
            }
        }  

        int pieceAt(int row, int col) {
            return board[row][col];
        }

    } 

    /*
     * 
     private boolean canMove(int player, int r1, int c1, int r2, int c2) {

            if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
                return false; 

            if (board[r2][c2] != EMPTY)
                return false;  

            if (player == RED) {
                if (board[r1][c1] == RED && r2 > r1)
                    return false;  
                return true;  
            }
            else {
                if (board[r1][c1] == BLACK && r2 < r1)
                    return false;  
                return true;  
                
            }

        }  
     */

} 

