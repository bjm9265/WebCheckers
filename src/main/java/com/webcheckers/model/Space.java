package com.webcheckers.model;

/**
 * Class that represents the spaces on the checker board
 * @ Kyle McCoy
 */
public class Space {
    // the column of the space
    private int cellIdx ;
    // the color of the space on the board
    private spaceColor color ;
    // the piece on that space, null if empty
    private Piece piece ;
    // enum represents the color of the spaces on the board
    public enum spaceColor{
        DARK, LIGHT
    }

    /**
     * Constructor for Space
     * @param row the row number to determine the space color
     * @param cellIdx the column number to determine space color
     */
    public Space(int row, int cellIdx){
        this.cellIdx = cellIdx ;
        this.piece = null ;
        if(row%2==0){
            if(cellIdx%2==0){
                this.color = spaceColor.LIGHT ;
            }else{
                this.color = spaceColor.DARK ;
            }
        }else{
            if(cellIdx%2==0){
                this.color = spaceColor.DARK ;
            }else{
                this.color = spaceColor.LIGHT ;
            }
        }
    }

    /**
     * is the space Dark and empty?
     * @return boolean
     */
    public boolean isValid(){
        return this.color.equals(spaceColor.DARK) && (this.piece==null) ;
    }

    /**
     * places a piece on the space
     * @param piece piece to be placed
     */
    public void placePiece(Piece piece) {
        if(this.isValid()){
            this.piece = piece ;
        }
    }

    /**
     * get the space column number
     * @return int
     */
    public int getCellIdx() {
        return this.cellIdx ;
    }

    /**
     * get the piece occupying the space, null if empty
     * @return Piece if occupied, null if empty
     */
    public Piece getPiece(){
        return this.piece ;
    }
}
