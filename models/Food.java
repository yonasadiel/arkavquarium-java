public class Food implements Drawable {
    private static final String assetPath = "assets/img/food.png";
    private static final int PRICE = 10;
    /**
    * Max velocity of the food movement
    */
    private static int MAX_VELOCITY = 30;
    private Position position;
    /**
     * Construct Food at destined position
     * @param {double} absis of food
     * @param {double} ordinate of food
     */
    public Food(double x, double y){
        this.position = new Position(x, y, false);
    }

    /**
     * Move Food to bottom
     * @param {int} minimum height
     * @param {double} elapsed seconds
     */
    public void move(int y, double elapsedSeconds){
        Position bottom = new Position(0, y, false);
        this.position.moveVertical(bottom, elapsedSeconds * MAX_VELOCITY);
    }

    /**
    * @return {Position} Food Position 
    **/
    Position getPosition(){
        return this.position;
    }

    /**
     * @return {std::string} asset path
     */
    String getAssetPath(){
        return assetPath;
    }

    /**
     * @return {int} food price
     */
    final int getPrice(){
        return PRICE;
    }
}