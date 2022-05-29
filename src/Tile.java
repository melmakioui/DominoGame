public class Tile {

    private int leftNum;
    private int rightNum;

    public Tile (int leftNum, int rightNum) {
        this.leftNum = leftNum;
        this.rightNum = rightNum;
    }

    public int getLeftNum() {
        return leftNum;
    }

    public int getRightNum() {
        return rightNum;
    }

    public void displayNormalTile() {
        System.out.println("  *****");
        System.out.println("  * " + leftNum + " *");
        System.out.println("  *---*");
        System.out.println("  * "  + rightNum + " *");
        System.out.println("  *****");
    }

    public void displayReverseNormalTile() {
        System.out.println("  *****");
        System.out.println("  * " + rightNum + " *");
        System.out.println("  *---*");
        System.out.println("  * "  + leftNum + " *");
        System.out.println("  *****");
    }

    public void displayDoubleTile(){
        System.out.println("*********");
        System.out.println("* " + leftNum + " | " + rightNum + " *");
        System.out.println("*********");
    }

    @Override
    public String toString() {
        return "[ " + leftNum + " | " + rightNum + " ]" ;
    }
}