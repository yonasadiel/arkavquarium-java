package com.arkavquarium.models;

public class Guppy extends Fish {    
    private final static int MAX_VELOCITY = 50;
    /**
     * Period time for Guppy producing coin
     */
    private final static double PRODUCE_COIN_PERIOD = 8 ;
    private final static int COIN_VALUE_STEP_ONE = 10;
    private final static int COIN_VALUE_STEP_TWO = 20;
    private final static int COIN_VALUE_STEP_THREE = 40;
    private final static int PRICE = 100;
    private final static String assetPath = "src/main/resources/img/guppy";
    /**
     * Timer for producing coin
     * Updated every update method is called
     */
    private double produceCoinTimer;
    private GrowthStep growthStep;

    /**
     * Construct Guppy
     * Set produceCoinTimer to 0
     */
    public Guppy(){
        super();
        this.produceCoinTimer = 0;
        this.growthStep = GrowthStep.STEP_ONE;
        this.maxVelocity = MAX_VELOCITY;
    }
        
    /**
     * @return growthStep
     */
    public GrowthStep getGrowthStep(){
        return this.growthStep;
    }
        
    /**
     * @return growthStep in int
     */
    public int getGrowthStepInt(){
        if ( this.growthStep == GrowthStep.STEP_ONE) { return 1; }
        else if ( this.growthStep == GrowthStep.STEP_TWO ) { return 2;}
        else { return 3;}
    }
        
    /**
     * @param growthStep new growthStep
     */
    public void setGrowthStep(GrowthStep growthStep){
        this.growthStep = growthStep;
    }
        
    /**
     * Reduce produceCoinTimer with PRODUCE_COIN_PERIOD
     * @return 0 if PRODUCE_COIN_PERIOD == produceCoinTimer before edited
     *         this growthStep value if not
     */
    public int isProduceCoin(){
        if (this.produceCoinTimer < PRODUCE_COIN_PERIOD) {
            return 0;
        } else {
            this.produceCoinTimer -= PRODUCE_COIN_PERIOD;
            if (this.getGrowthStep() == GrowthStep.STEP_ONE) { return COIN_VALUE_STEP_ONE; }
            else if (this.getGrowthStep() == GrowthStep.STEP_TWO) { return COIN_VALUE_STEP_TWO; }
            else { return COIN_VALUE_STEP_THREE; }
        }
    }
        
    /**
     * Increment produceCoinTimer by elapsedSeconds
     * @param elapsedSeconds elapsed seconds since previous loop
     */
    public void update(double elapsedSeconds){
        this.produceCoinTimer += elapsedSeconds;
    }

    /**
     * Override parent eat(). After parent eat(),
     * if exceed minimum eatCounter, upgrade growthStep to next step
     */
    public void eat(){
        super.eat();
        if (this.eatCounter == FIRST_GROWTH_EAT_COUNTER)
            this.growthStep = GrowthStep.STEP_TWO;
        else if(this.eatCounter == SECOND_GROWTH_EAT_COUNTER)
            this.growthStep = GrowthStep.STEP_THREE;
    }

    /**
     * @return asset path, depends on this orientation, growth step, and status
     */
    public String getAssetPath(){
        String path = assetPath;

        if (this.getOrientation() == Orientation.LEFT) { path += "_left"; }
        else { path += "_right"; }

        if (this.getGrowthStep() == GrowthStep.STEP_ONE) { path += "_small"; }
        else if (this.getGrowthStep() == GrowthStep.STEP_TWO) { path += "_medium"; }
        else { path += "_big"; }

        if (this.isStarving()) { path += "_hungry"; }

        path += ".png";
        return path;
    }

    /**
     * @return guppy price
     */
    public static Integer getPrice(){
        return PRICE;
    }
}