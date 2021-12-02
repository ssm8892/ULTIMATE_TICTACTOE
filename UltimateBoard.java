public class UltimateBoard {
    //I created a instances of Board as boards
    Board boards[];
    //This num_Won variable tracks the number of boards already won by the player
    int num_Won;
    //This match_Over variable keeps track of the match
    boolean match_Over;
    //it keeps track of who won if there is a 1 it indicates player 1 won if there is a 2 it indicates player 2 won and if there is a 0 it means no one won
    int winners[];
    //The final Winner keeps track of who won the entire thing
    int finalWinner;

    //this function is a constructor and its creating new 9 boards as the ultimate board and keeps track of board won if the match is over and who won the entire game
    UltimateBoard(){
        boards = new Board[9];
        winners = new int[9];
        for(int i = 0; i < 9; i++){
            boards[i] = new Board();
            winners[i] = 0;
        }
        num_Won = 0;
        match_Over = false;
        finalWinner = 0;
    }

    //add play function allows the player to pick the board and the square and calls the other functions and checks them

    public boolean addPlay(int board, int square, int player){
        if((boards[board]).addMove(square, player)){
            printGame();
            System.out.println(" ");
            if(boards[board].winner != 0){
                (boards[board]).game_Over = true;
                winners[board] = boards[board].winner;
                System.out.println("Winner of board " + board + " is Player " + boards[board].winner + "!");
                System.out.println(" ");
                num_Won++;

            }
            if(num_Won > 2){
                int temp = checkBoards();
                if(temp != 0){
                    finalWinner = temp;
                    match_Over = true;
                }
            }
            return true;
        } else {
            System.out.println(" ");
            return false;
        }
    }

    //check board function keeps track of the boards and sees who won based off the player that won the board
    public int checkBoards(){
        if(checkWon(1)){
            return 1;
        } else if(checkWon(2)){
            return 2;
        } else {
            return 0;
        }
    }

    //checkWon function keeps track of the possible ways a player can win rows columns and diagonals
    public boolean checkWon(int player){
        if(winners[0] == player && winners[1] == player && winners[2] == player){
            return true;
        } else if (winners[3] == player && winners[4] == player && winners[5] == player){
            return true;
        } else if (winners[6] == player && winners[7] == player && winners[8] == player){
            return true;
        } else if (winners[0] == player && winners[3] == player && winners[6] == player){
            return true;
        } else if (winners[1] == player && winners[4] == player && winners[7] == player){
            return true;
        } else if (winners[2] == player && winners[5] == player && winners[8] == player){
            return true;
        } else if (winners[0] == player && winners[4] == player && winners[8] == player){
            return true;
        } else if (winners[2] == player && winners[4] == player && winners[6] == player){
            return true;
        }
        return false;
    }

    //PrintGame function prints the board
    public void printGame(){
        char[] b0 = boards[0].getBoard();
        char[] b1 = boards[1].getBoard();
        char[] b2 = boards[2].getBoard();
        char[] b3 = boards[3].getBoard();
        char[] b4 = boards[4].getBoard();
        char[] b5 = boards[5].getBoard();
        char[] b6 = boards[6].getBoard();
        char[] b7 = boards[7].getBoard();
        char[] b8 = boards[8].getBoard();


        System.out.println("-----------------");
        System.out.println(b0[0] + " " + b0[1] + " " + b0[2] + "|" + b1[0] + " " + b1[1] + " " + b1[2] + "|" + b2[0] + " " + b2[1] + " " + b2[2]);
        System.out.println(b0[3] + " " + b0[4] + " " + b0[5] + "|" + b1[3] + " " + b1[4] + " " + b1[5] + "|" + b2[3] + " " + b2[4] + " " + b2[5]);
        System.out.println(b0[6] + " " + b0[7] + " " + b0[8] + "|" + b1[6] + " " + b1[7] + " " + b1[8] + "|" + b2[6] + " " + b2[7] + " " + b2[8]);
        System.out.println("-----------------");
        System.out.println(b3[0] + " " + b3[1] + " " + b3[2] + "|" + b4[0] + " " + b4[1] + " " + b4[2] + "|" + b5[0] + " " + b5[1] + " " + b5[2]);
        System.out.println(b3[3] + " " + b3[4] + " " + b3[5] + "|" + b4[3] + " " + b4[4] + " " + b4[5] + "|" + b5[3] + " " + b5[4] + " " + b5[5]);
        System.out.println(b3[6] + " " + b3[7] + " " + b3[8] + "|" + b4[6] + " " + b4[7] + " " + b4[8] + "|" + b5[6] + " " + b5[7] + " " + b5[8]);
        System.out.println("-----------------");
        System.out.println(b6[0] + " " + b6[1] + " " + b6[2] + "|" + b7[0] + " " + b7[1] + " " + b7[2] + "|" + b8[0] + " " + b8[1] + " " + b8[2]);
        System.out.println(b6[3] + " " + b6[4] + " " + b6[5] + "|" + b7[3] + " " + b7[4] + " " + b7[5] + "|" + b8[3] + " " + b8[4] + " " + b8[5]);
        System.out.println(b6[6] + " " + b6[7] + " " + b6[8] + "|" + b7[6] + " " + b7[7] + " " + b7[8] + "|" + b8[6] + " " + b8[7] + " " + b8[8]);
        System.out.println("-----------------");
    }

}
