public class Snail implements Drawable {
    private static final String assetPath = "assets/img/snail";
    private final int MAX_VELOCITY = 40;
    private Orientation orientation;
    private Position position;

    /**
    * Construct Snail with random position
    */
    public Snail(){
        int maxWidth = Data.getMaxWidth();
        int maxHeight = Data.getMaxHeight();
        this.position = new Position();
        this.position.setOrdinate(maxHeight-(maxHeight/10));
        this.orientation = Orientation.LEFT;
    }

    /**
     * @return the snail position
     */
    public Position getPosition(){
        return this.position;
    }

    /**
     * Move position to dest not exceeding MAX_VELOCITY
     * @param position destination
     * @param elapsedSeconds elapsed seconds
     */
    public void moveToDestination(Position position, double elapsedSeconds){
        this.position.moveHorizontal(position, elapsedSeconds * MAX_VELOCITY);
        if (this.position.getAbsis() < position.getAbsis()) {
            this.orientation = Orientation.RIGHT;
        } else {
            this.orientation = Orientation.LEFT;
        }
    }

    /**
     * @return asset path
     */
    public String getAssetPath(){
        String path = assetPath;

        if (this.orientation == Orientation.LEFT) { path += "_left"; }
        else { path += "_right"; }

        path += ".png";

        return path;
    }
}