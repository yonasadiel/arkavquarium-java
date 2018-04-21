enum tGrowthStep { stepOne, stepTwo, stepThree ; }



public class Guppy extends Fish {
    
    private final static int MAX_VELOCITY = 40;
    /**
     * Period time for Guppy producing coin
     */
    private final static double PRODUCE_COIN_PERIOD = 8 ;
    private final static int COIN_VALUE_STEP_ONE = 10;
    private final static int COIN_VALUE_STEP_TWO = 20;
    private final static int COIN_VALUE_STEP_THREE = 40;
    private final static int PRICE = 100;
    private final static String assetPath = "assets/img/guppy";
    /**
     * Timer for producing coin
     * Updated every update method is called
     */
    private double produceCoinTimer;
    private tGrowthStep growthStep;
        /**
         * Construct Guppy
         * Set produceCoinTimer to 0
         */
    public Guppy(int maxWidth, int maxHeight){
        super();
        this.produceCoinTimer = 0;
        this.growthStep = tGrowthStep.stepOne;
        this.maxVelocity = MAX_VELOCITY;
    }
        
        /**
         * @return {tGrowthStep} this growthStep
         */
    public tGrowthStep getGrowthStep(){
        return this.growthStep;
    }
        
        /**
         * @return {int} this growthStep in int
         */
    public int getGrowthStepInt(){
        if ( this.growthStep == tGrowthStep.stepOne) { return 1; }
        else if ( this.growthStep == tGrowthStep.stepTwo ) { return 2;}
        else { return 3;}
    }
        
        /**
         * @param {tGrowthStep} new growthStep
         */
    public void setGrowthStep(tGrowthStep growthStep){
        this.growthStep = growthStep;
    }
        
        /**
         * Reduce produceCoinTimer with PRODUCE_COIN_PERIOD
         * @return {int} 0 if PRODUCE_COIN_PERIOD == produceCoinTimer before edited
         *               this growthStep value if not
         */
    public int isProduceCoin(){
        if (this.produceCoinTimer < PRODUCE_COIN_PERIOD) {
            return 0;
        } else {
            this.produceCoinTimer -= PRODUCE_COIN_PERIOD;
            if (this.getGrowthStep() == tGrowthStep.stepOne) { return COIN_VALUE_STEP_ONE; }
            else if (this.getGrowthStep() == tGrowthStep.stepTwo) { return COIN_VALUE_STEP_TWO; }
            else { return COIN_VALUE_STEP_THREE; }
        }
    }
        
        /**
         * Increment produceCoinTimer by elapsedSeconds
         * @param {double} elapsed seconds since previous loop
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
            this.growthStep = tGrowthStep.stepTwo;
        else if(this.eatCounter == SECOND_GROWTH_EAT_COUNTER)
            this.growthStep = tGrowthStep.stepThree;
    }

        /**
         * @return {std::string} asset path
         */
    public String getAssetPath(){
        String path = assetPath;

        if (this.getOrientation() == tOrientation.left) { path += "_left"; }
        else { path += "_right"; }

        if (this.getGrowthStep() == tGrowthStep.stepOne) { path += "_small"; }
        else if (this.getGrowthStep() == tGrowthStep.stepTwo) { path += "_medium"; }
        else { path += "_big"; }

        if (this.isStarving()) { path += "_hungry"; }

        path += ".png";
        return path;
    }

        /**
         * @return {int} guppy price
         */
    public static Integer getPrice(){
        return PRICE;
    }
}