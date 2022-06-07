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

    public void displayTile() {
        if (leftNum == rightNum) {
            displayDoubleTile();
            return;
        }

        displayNormalTile();
    }

    //Moure a un altre clase
    public void displayNormalTile() {
        System.out.println("  *****");
        System.out.println("  * " + leftNum + " *");
        System.out.println("  *---*");
        System.out.println("  * " + rightNum + " *");
        System.out.println("  *****");
    }

    public void displayReverseNormalTile() {
        System.out.println("  *****");
        System.out.println("  * " + rightNum + " *");
        System.out.println("  *---*");
        System.out.println("  * " + leftNum + " *");
        System.out.println("  *****");
    }

    public void displayDoubleTile() {
        System.out.println("*********");
        System.out.println("* " + leftNum + " | " + rightNum + " *");
        System.out.println("*********");
    }

    @Override
    public String toString() {
        return "[ " + leftNum + " | " + rightNum + " ]";
    }

    @Override
    public int compareTo(Tile o) {
        // 1,0,-1
        //return (getSumTile() > o.getSumTile())? 1 : -1 ;
        //reverseTile();
        //o.reverseTile();
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
