package com.arkavquarium.models;

public class Piranha extends Fish {
    private final static int PRICE = 1000;
    private final static int MAX_VELOCITY = 50;
    private final static String assetPath = "src/main/resources/img/piranha";
    /**
     * Piranha has just eat a guppy
     * 0 if false
     * x+1 if just eat guppy with growth step x
     */
    private int isJustEatGuppy;

    /**
     * Construct Piranha with isJustEatGuppy as False
     */
    public Piranha() {
        super();
        this.isJustEatGuppy = 0;
        this.maxVelocity = MAX_VELOCITY; 
    }

    /**
     * Set isJustEatGuppy to 0
     * @return isJustEatGuppy
     */
    public int isProduceCoin() {
        int Temp = this.isJustEatGuppy;
        this.isJustEatGuppy = 0;
        return Temp;
    }

    /**
     * Set isJustEatGuppy to True
     * @param x growth step of fish eaten
     */
    public void eat(int x) {
        super.eat();
        this.isJustEatGuppy = x + 1 ;
    }

    /**
     * @return asset path, depends on orientation and status
     */
    public String getAssetPath(){
        String path = assetPath;

        if (this.getOrientation() == Orientation.LEFT) { path += "_left_big"; }
        else { path += "_right_big"; }

        if (this.isStarving()) { path += "_hungry"; }

        path += ".png";
        return path;
    }

    public static int getPrice(){
        return PRICE;
    }

}