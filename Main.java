import java.util.Scanner;
import java.math.*;

//The main objective of the code is to implement an ultimate tictactoe game with three modes pvp pvc cvc where p stands for player and c stands for computer


//In the main class the console prints instructions to pick between three modes
//Then there's a function that implements the AI's moves which ensures the moves are within the range 0-8
// and are random and that they dont use a square that's already filled
//Then there's a function that implements user's moves which ensures the user doesnt cross the range and keeps
// running till its a valid input and if the user has a board choice from the move that the board is a valid one
//In my Board i have constructer and data fields class i have an addMove function which fills the - which gets replaced with X and O
//the getBoard function returns the grid to be accessed from
//The  check winner checks if the game is complete and looks for x or o and sets the winner as 1 or 2
//The  check symbol checks within the board and all the possible combinations of being a winner
//In my UltimateBoard class i have constructer and data fields and
// i have an addPlay function which lets the player pick the board and the square and keeps track
// if they are winning a certain board or not
//I created a CheckBoard function which checks which player won and returns their respective number\
//the function checkWon tracks all the possible ways of winning
//In my ultimate Board class i made the print game function which prints out the entire
// layout for the 9 boxes and prints the boxes in their respective positions


public class Main {
    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        Scanner kb = new Scanner(System.in);
        int gameMode = 0;

        //Beginning instruction that prints when the game starts and lists all the available options
        System.out.println("-------------------------------");
        System.out.println("WELCOME TO ULTIMATE TIC-TAC-TOE");
        System.out.println("MODE 1: PLAYER VS. PLAYER");
        System.out.println("MODE 2: PLAYER VS. AI");
        System.out.println("MODE 3: AI VS AI");
        System.out.println("-------------------------------");

        //This lets you pick between the game modes
        System.out.print("PICK A GAME MODE (1-3) : ");
        gameMode = kb.nextInt();//takes the user input
        while (gameMode < 1 || gameMode > 3) {//Fail statments when the values entered is lower or higher than 1 or 3
            System.out.print("Enter a valid game mode: ");
            gameMode = kb.nextInt();
        }

        UltimateBoard UB = new UltimateBoard();
        boolean boardChoice = true;//Board choice represents the board a user can pick
        int userBoard = 9;
        int playerNum = 1;

        while (!UB.match_Over) {
            //This is game mode 1 player vs player so theres two players
            if (gameMode == 1) {
                userBoard = userMove(UB, userBoard, boardChoice, playerNum, kb);
                //If the move given by the user is invalid, the loop will just continue and the same user will get another turn
                //Instead of moving to the next user's turn
                if(userBoard == -1){
                    System.out.println("INVALID MOVE");
                    continue;
                }
                boardChoice = false;
                if (playerNum == 1) {
                    playerNum = 2;
                } else {
                    playerNum = 1;
                }
                //THis is the game mode 2 player vs ai which takes account for users move as a function and Ai's move as a fucntion
            } else if (gameMode == 2) {
                userBoard = userMove(UB, userBoard, boardChoice, playerNum, kb);
                //If the user's move is invalid, then redo the loop iteration instead of going through with the AI's turm
                if(userBoard == -1){
                    System.out.println("INVALID MOVE");
                    continue;
                }
                boardChoice = false;

                userBoard = aiMove(UB, userBoard, 2, boardChoice);
                //Game mode 3 which is AI vs Ai
            } else {
                userBoard = aiMove(UB, userBoard, playerNum, boardChoice);
                boardChoice = false;
                //This logic is to switch between the two AI's
                if (playerNum == 1) {
                    playerNum = 2;
                } else {
                    playerNum = 1;
                }
            }
        }
        System.out.println("--------------------------------------");
        if (UB.finalWinner == 1) {
            System.out.println("THE WINNER OF THE MATCH IS PLAYER 1");
        } else {
            System.out.println("THE WINNER OF THE MATCH IS THE PLAYER 2");
        }
        System.out.println("--------------------------------------");
    }

    //This is the ai move function
    public static int aiMove(UltimateBoard UB, int userBoard, int playerNum, boolean boardChoice) {
        int board, square;
        int min = 0, max = 8;
        char symbol;
        //Allocating X and O to players
        if (playerNum == 1) {
            symbol = 'X';
        } else {
            symbol = 'O';
        }
        System.out.println("Player " + symbol + "'s move: ");

        //Random number as a board in case the AI has a board choice
        board = (int) Math.floor(Math.random() * (max - min + 1) + min);
        while (UB.boards[board].num_Filled >= 9) {
            board = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        //if there is no board choice thr board is the given board number
        if(!boardChoice){
            board = userBoard;
        }

        //if the board we got is filled thrn we get a new random board and a new random square
        if (UB.boards[board].num_Filled >= 9) {
            //THis is getting new eandom board
            board = (int) Math.floor(Math.random() * (max - min + 1) + min);
            while (UB.boards[board].num_Filled >= 9) {
                board = (int) Math.floor(Math.random() * (max - min + 1) + min);
            }
            //This is getting new random square
            square = (int) Math.floor(Math.random() * (max - min + 1) + min);
            while (UB.boards[board].grid[square] != '-') {
                square = (int) Math.floor(Math.random() * (max - min + 1) + min);
            }
            //if the board is not full it picks a random sqaure within 1 and 8
        } else {
            square = (int) Math.floor(Math.random() * (max - min + 1) + min);
            while (UB.boards[board].grid[square] != '-') {
                square = (int) Math.floor(Math.random() * (max - min + 1) + min);
            }
        }
        //Add the right symbol in the right board and square
        UB.addPlay(board, square, playerNum);
        //Return the square to pick the board for the next player
        return square;
    }

    //usermove function which tracks the objectives for users move
    public static int userMove(UltimateBoard UB, int userBoard, boolean boardChoice, int playerNum, Scanner kb) {
        char symbol;
        int userSquare;
        boolean valid = false;
        //Select the symbol to be used based on the player number
        if (playerNum == 1) {
            symbol = 'X';
        } else {
            symbol = 'O';
        }

        //If the player has the choice to pick a board
        if (boardChoice) {
            //Ask the user for board number and the square number
            System.out.println(" ");
            System.out.println("Player " + symbol + "'s move: ");

            System.out.print("Board#: ");
            userBoard = kb.nextInt();
            while (userBoard > 8 || userBoard < 0) {
                System.out.print("Enter valid board#: ");
                userBoard = kb.nextInt();
            }

            //Right after you ask the user for a board number, print out all the empty squares in that board
            System.out.print("VALID SQUARES: ");
            for (int i = 0; i < 9; i++) {
                if (UB.boards[userBoard].grid[i] == '-') {
                    System.out.print(i + " ");
                }
            }
            System.out.println(" ");

            //Then let the user pick one of the valid squares you listed above
            System.out.print("Square#: ");
            userSquare = kb.nextInt();
            while (userSquare > 8 || userSquare < 0) {
                System.out.print("Enter valid square#: ");
                userSquare = kb.nextInt();
            }

            //Add the play to the ultimate board, and the return value will tell us if it was a valid play or no
            valid = UB.addPlay(userBoard, userSquare, playerNum);

            //If it was a valid move, return the square that was filled in this turn
            if (valid) {
                return userSquare;
            } else {
                //If it was invalid, return -1 to symbolize that
                return -1;
            }
        } else {
            //If the user doesn't have the choice to pick a board
            System.out.println("Player " + symbol + "'s move: ");

            //Tell the user which board is valid
            System.out.println("Only valid board is Board#" + userBoard);

            //Print out all the empty squares in that board
            System.out.print("VALID SQUARES: ");
            for (int i = 0; i < 9; i++) {
                if (UB.boards[userBoard].grid[i] == '-') {
                    System.out.print(i + " ");
                }
            }
            System.out.println(" ");

            //Ask the user to pick one of the valid squares
            System.out.print("Square#: ");
            userSquare = kb.nextInt();
            while (userSquare > 8 || userSquare < 0) {
                System.out.print("Enter valid square#: ");
                userSquare = kb.nextInt();
            }

            //Add the play to the ultimate board, and the return value will tell us if it was a valid play or no
            valid = UB.addPlay(userBoard, userSquare, playerNum);

            //If it was a valid move, return the square that was filled in this turn
            if (valid) {
                return userSquare;
            } else {
                //If it was invalid, return -1 to symbolize that
                return -1;
            }
        }
    }
}
