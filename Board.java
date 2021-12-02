public class Board {

    //i created of a char as a grid
    char grid[];
    //THis keeps track of the squares filled
    int num_Filled;
    //The game_over keeps track of the game if its done or not
    boolean game_Over;
    //The winner keeps track of the player who won the board
    int winner;

    //this function is a constructor and its creating new board and keeps track of match is over and who won
    Board(){
        grid = new char[9];
        num_Filled = 0;
        for(int i = 0; i < 9; i++){
            grid[i] = '-';
        }
        game_Over = false;
        winner = 0;
    }

    //add move function allows the player to pick the sqaure
    public boolean addMove(int square, int player){
        if(grid[square] == '-'){
            if(player == 1){
                grid[square] = 'X';
            } else {
                grid[square] = 'O';
            }
            num_Filled++;
            if(num_Filled > 2){
                if(winner == 0){
                    checkWinner();
                }
            }
            return true;
        } else {
            return false;
        }
    }

    //This function calls the grid which refers to the board
    public char[] getBoard(){
        return grid;
    }


    //The function check winner if the game is not over it checks symbol x or o and sets the winner as 1 or 2
    public int checkWinner(){
        if(!game_Over){
            if(checkSymbol('X')){
                winner = 1;
            } else if (checkSymbol('O')){
                winner = 2;
            }
        }
        return winner;
    }

    //The function check symbol checks within the board and all the possible combinations and sees the winner
    public boolean checkSymbol(char symb){
        if(grid[0] == symb && grid[1] == symb && grid[2] == symb){
            return true;
        } else if (grid[3] == symb && grid[4] == symb && grid[5] == symb){
            return true;
        } else if (grid[6] == symb && grid[7] == symb && grid[8] == symb){
            return true;
        } else if (grid[0] == symb && grid[3] == symb && grid[6] == symb){
            return true;
        } else if (grid[1] == symb && grid[4] == symb && grid[7] == symb){
            return true;
        } else if (grid[2] == symb && grid[5] == symb && grid[8] == symb){
            return true;
        } else if (grid[0] == symb && grid[4] == symb && grid[8] == symb){
            return true;
        } else if (grid[2] == symb && grid[4] == symb && grid[6] == symb){
            return true;
        }
        return false;
    }

}
