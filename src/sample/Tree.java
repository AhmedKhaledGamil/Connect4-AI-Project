package sample;

public class Tree {
    int index;
    int depth;
    int data;
    Tree children[] = new Tree[7];

    public int getIndex() {
        return index;
    }

    public int getDepth() {
        return depth;
    }

    public int getData() {
        return data;
    }

    public Tree[] getChildren() {
        return children;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setChildren(Tree[] children) {
        this.children = children;
    }
}
