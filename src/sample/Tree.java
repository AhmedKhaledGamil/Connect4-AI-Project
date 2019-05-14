package sample;

public class Tree {
    Board board = new Board(6,7);
    Tree[] children;

    public Board getBoard() {
        return board;
    }

    public Tree[] getChildren() {
        return children;
    }

    public Tree(Board board) {
        this.board.setBoard(board);
        this.children = new Tree[7];
    }
}
