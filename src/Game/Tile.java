package Game;

public class Tile implements Comparable<Tile> {

    private int leftNum;
    private int rightNum;

    public Tile(int leftNum, int rightNum) {
        this.leftNum = leftNum;
        this.rightNum = rightNum;
    }

    public int getLeftNum() {
        return leftNum;
    }

    public int getRightNum() {
        return rightNum;
    }

    public int getSumTile() {
        return (leftNum + rightNum);
    }

    public void reverseTile() {
        int temp = leftNum;
        leftNum = rightNum;
        rightNum = temp;
    }

    @Override
    public String toString() {
        return "[ " + leftNum + " | " + rightNum + " ]";
    }

    @Override
    public int compareTo(Tile o) {

        if( leftNum == o.getLeftNum() )
            if (rightNum > o.getRightNum()) {
                return 1;
            } else return -1;

        if (leftNum > o.getLeftNum()) {
            return 1;
        }

        return -1;
    }
}
