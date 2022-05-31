public class Tile {

    private int leftNum;
    private int rightNum;
    private boolean initialTile;

    public Tile (int leftNum, int rightNum) {
        this.leftNum = leftNum;
        this.rightNum = rightNum;
        this.initialTile = false;
    }

    public int getLeftNum() {
        return leftNum;
    }

    public int getRightNum() {
        return rightNum;
    }

    public int getSumTile(){
        return (leftNum + rightNum);
    }

    public void setInitialTile(boolean initialTile) {
        this.initialTile = initialTile;
    }


    //Output

    public void displayTile(){
        if (leftNum == rightNum || initialTile) {
            displayDoubleTile();
            return;
        }

        displayNormalTile();

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
