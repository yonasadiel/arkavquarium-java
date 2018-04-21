
enum Status {
    STARVING, IDLE;
}

enum Orientation {
    LEFT, RIGHT ;
}


public abstract class Fish implements Drawable {
    protected static final int MAX_VELOCITY = 35;
    /**
     * Time from Fish eat until starving
     * @todo set constant
     */
    protected static final int REPLETE_TIME = 12;
    /**
     * Time from Fish starving until die
     * @todo set constant
     */
    protected static final int STARVATION_TIME = 24;
    protected static final int FIRST_GROWTH_EAT_COUNTER = 7;
    protected static final int SECOND_GROWTH_EAT_COUNTER = 15;
    
    protected Status status;
    protected double maxVelocity;
    protected Position position;
    /**
     * Destination of Fish when Fish is idle
     */
    protected Position destination;
    protected Orientation orientation;
    protected double starvingTimer;
    protected int eatCounter;

    /**
    * Construct Fish
    * Set status to STATUS_IDLE
    * Set position to random new position
    * Set destination to random new position
    * Set growthStep to GROWTH_STEP_ONE
    * Set starvingTimer to zero
    * Set orientation according to destination
    * Set eatCounter to zero
    */
    public Fish(){
        this.status = Status.idle;
        this.position = new Position();
        this.destination = new Position();
        this.starvingTimer = 0;
        this.orientation =
            (this.destination.getAbsis() > this.position.getAbsis()) ?
               Orientation.RIGHT : Orientation.LEFT;
        this.eatCounter = 0;
    }

    /**
     * Getter  
     */
    public final Status getStatus() {
        return this.status;
    }

    public final Position getPosition() {
        return this.position;
    }
    public final Position getDestination() {
        return this.destination;
    }

    public final double getStarvingTimer() {
        return this.starvingTimer;
    }

    public final Orientation getOrientation(){
    return this.orientation;
    }

    public final int getEatCounter(){
        return this.eatCounter;
    }

    /**
     * Setter
     */
    public void setStatus(Status status){
        this.status = status;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public void setDestination(Position position){
        this.destination = destination;
    }

    public void setStarvingTimer(double starvingTimer){
        this.starvingTimer = starvingTimer;
    }

    public void setOrientation(Orientation orientation){
        this.orientation = orientation ;
    }


    public abstract int isProduceCoin();

    /**
     * @return {bool} REPLETE TIME <= starvingTimer <= STARVING TIME 
     */
    public boolean isStarving(){
        return (REPLETE_TIME <= this.starvingTimer) && (this.starvingTimer <= STARVATION_TIME);
    }

    /**
         * @return {bool} starvingTimer > STARVING TIME
         */
    public boolean isDie(){
        return this.getStarvingTimer() > STARVATION_TIME;
    }

    /**
     * Increment eatCounter
     * If exceed minimum eatCounter, upgrade growthStep to next step
     */
    public void eat(){
        this.eatCounter++;
        this.starvingTimer = 0;
    }

    /**
     * Move Fish to the dest
     * @param {Position} is the nearest Food for Guppy
     *   or nearest Guppy for Piranha
     */
    public void moveToDestination(Position position, double elapsedSeconds){
        this.position.move(position, elapsedSeconds * this.maxVelocity); 
        if (this.position.getAbsis() < position.getAbsis()) {
            this.orientation = Orientation.RIGHT;
        } else {
            this.orientation = Orientation.LEFT;
        }
    }

    /**
     * Move Fish to their default destination
     * If Fish Position equal to default destination
     * random new destination 
     */
    public void moveToDestination(int maxWidth, int maxHeight, double elapsedSeconds){
        if (this.position.equals(this.destination)){
            this.destination = new Position();
        }
        this.position.move(this.destination, elapsedSeconds * this.maxVelocity);
        if (this.position.getAbsis() < this.destination.getAbsis()) {
            this.orientation = Orientation.RIGHT;
        } else {
            this.orientation = Orientation.LEFT;
        }
    }
}