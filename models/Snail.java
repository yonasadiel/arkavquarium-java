public class Snail implements Drawable {
    private static final String assetPath = "assets/img/snail";
    private final int MAX_VELOCITY = 40;
    
    enum TOrientation {
        left, right;
    };
    private TOrientation orientation;

    /**
    * Construct Snail with random position
    * @param {int} screen width
    * @param {int} screen height
    */
    public Snail(int maxWidth, int maxHeight){
        this.position = new Position(maxWidth, maxHeight, true);
        this.position.setOrdinate(maxHeight-(maxHeight/10));
        this.orientation = left;
    }

    /**
     * Returns the snail position
     */
    public Position getPosition(){
        return this.position;
    }

    /**
     * Move position to dest not exceeding MAX_VELOCITY
     * @param {Position} destination
     */
    public void moveToDestination(Position position, double elapsedSeconds){
        this.position.moveHorizontal(position, elapsedSeconds * MAX_VELOCITY);
        if (this.position.getAbsis() < position.getAbsis()) {
            this.orientation = right;
        } else {
            this.orientation = left;
        }
    }

    /**
     * @return {std::string} asset path
     */
    public String getAssetPath(){
        String path = assetPath;

        if (this.orientation == left) { path += "_left"; }
        else { path += "_right"; }

        path += ".png";

        return path;
    }
}