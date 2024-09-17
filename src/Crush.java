public class Crush {
    char[][] game;
    private int numRows;
    private int numCols;

    public Crush(int rows, int cols){
        numRows = rows;
        numCols = cols;
        game = new char[numRows][numCols];
        startGame();
    }

    public int getSize(){
        return numRows;
    }

    public void printGame(){
        for(int i = 0; i < game.length; i++){
            for(int j = 0; j < game[0].length; j++){
                System.out.print(game[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void startGame(){
        addPieces();
        //combine pieces until good
        combine();
        //then the game is able to start
    }

    public void fillArr(){
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                game[i][j] = getRanChar();
            }
        }
    }

    private char getRanChar(){
        int ran = (int)(Math.random() * 3);
        if(ran == 0){
            return 'r';
        } else if(ran==1){
            return  'g';
        } else{
            return 'b';
        }
    }

    public void addPieces(){
        fillArr();
        boolean playable = insurePlayable();
        while(!playable){
            fillArr();
            playable = insurePlayable();
        }
    }

    public void combine(){
        //combinePieces();
        //movePiecesDown();
        /* 
        boolean canCombine = checkIfCanCombine();
        while(canCombine){
            combinePieces();
            movedown
            canCombine = checkIfCanCombine();
        }
        */
    }

    public char[][] returnGame(){
        return game;
    }

    public boolean insurePlayable(){
        for(int i = 0; i <numRows; i++){
            for(int j = 0; j < numCols; j++){
                if(topPlayable(i, j) || botPlayable(i, j) || rightPlayable(j, i) || leftPlayable(j, i)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean topPlayable(int posR, int posC){
        char type = game[posR][posC];
        if(posR > 1 && posC > 0 && posC < numCols - 2){
            if(game[posR - 1][posC] == type || game[posR - 1][posC - 1] == type || game[posR-1][posC + 1] == type){
                if(game[posR - 2][posC] == type || game[posR - 2][posC - 1] == type || game[posR-2][posC + 1] == type){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean botPlayable(int posR, int posC){
        char type = game[posR][posC];
        if(posR < 2 && posC > 0 && posC < numCols - 2){
            if(game[posR + 1][posC] == type || game[posR + 1][posC - 1] == type || game[posR + 1][posC + 1] == type){
                if(game[posR + 2][posC] == type || game[posR + 2][posC - 1] == type || game[posR + 2][posC + 1] == type){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean rightPlayable(int posR, int posC){
        char type = game[posR][posC];
        if(posC < numCols - 2 && posR > 0 && posR < numRows - 2){
            if(game[posR][posC + 1] == type || game[posR - 1][posC + 1] == type || game[posR + 1][posC + 1] == type){
                if(game[posR][posC + 2] == type || game[posR + 1][posC + 2] == type || game[posR - 1][posC + 2] == type){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean leftPlayable(int posR, int posC){
        char type = game[posR][posC];
        if(posC > 1 && posR > 0 && posR < numRows - 2){
            if(game[posR][posC - 1] == type || game[posR - 1][posC - 1] == type || game[posR + 1][posC - 1] == type){
                if(game[posR][posC - 2] == type || game[posR + 1][posC - 2] == type || game[posR - 1][posC - 2] == type){
                    return true;
                }
            }
        }
        return false;
    }

    public void combinePieces(){
        //down
        for(int col = 0; col < numCols; col++){
            for(int row = 0; row < numRows - 2; row++){
                char firstType = game[row][col];
                if(firstType != '0' && game[row + 1][col] == firstType && game[row + 2][col] == firstType){
                    game[row][col] = '0';
                    game[row+1][col] = '0';
                    game[row+2][col] = '0';
                }
            }
        }
        //right 
        for(int row = 0; row < numRows; row++){
            for(int col = 0; col < numCols - 2; col++){
                char firstType = game[row][col];
                if(firstType != 0 && game[row][col + 1] == firstType && game[row][col + 2] == firstType){
                    game[row][col] = '0';
                    game[row][col + 1] = '0';
                    game[row][col + 2] = '0';
                }
            }
        }
    }

    public boolean checkIfCanCombine(){
        //down
        for(int col = 0; col < numCols; col++){
            for(int row = 0; row < numRows - 2; row++){
                char firstType = game[row][col];
                if(firstType != '0' && game[row + 1][col] == firstType && game[row + 2][col] == firstType){
                    return true;
                }
            }
        }
        //right 
        for(int row = 0; row < numRows; row++){
            for(int col = 0; col < numCols - 2; col++){
                char firstType = game[row][col];
                if(firstType != 0 && game[row][col + 1] == firstType && game[row][col + 2] == firstType){
                    return true;
                }
            }
        }
        return false;
    }

    public void movePiecesDown(){
        for(int row = 0; row < game.length; row++){
            for(int col = 0; col < game[0].length; col ++){
                char cur = game[row][col];
                if(cur == '0'){
                    for(int i = 1; i < row; i++){
                        game[row][col] = game[row-i][col];
                    }
                    game[0][col] = getRanChar();
                }
            }
        }
    }

    public void movePieces(int fromRow, int fromCol, int toRow, int toCol){
        try{
            char temp = game[fromRow][fromCol];
            game[fromRow][fromCol] = game[toRow][toCol];
            game[toRow][toCol] = temp; 
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid spot");
        }
        
    }

}
