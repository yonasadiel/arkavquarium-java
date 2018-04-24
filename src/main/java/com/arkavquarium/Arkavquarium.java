package com.arkavquarium;

import com.arkavquarium.models.*;
import java.awt.*;
import javax.swing.JFrame;


/**
 * Hello world!
 *
 * 
 */
public class Arkavquarium extends Canvas  {
    public static final int WIN_CONDITION = 3;
    private final int INIT_EGG = 0;
    private final int INIT_MONEY = 200; 

    private int width;
    private int height;
    /**
     * Construct with some guppies, one piranha and one snail 
     * with random position
     * @param {int} height of aquarium
     * @param {int} width of aquarium
     */
    public Arkavquarium() {
        this.width = Data.getMaxHeight();
        this.height = Data.getMaxHeight();
        Data.setEgg(this.INIT_EGG);
        Data.setMoney(this.INIT_MONEY);
    
        Guppy guppy1 = new Guppy();
        Data.getGuppies().add(guppy1);


        Guppy guppy2 = new Guppy();
        Data.getGuppies().add(guppy2);

        Snail snail = new Snail();;
        Data.setSnail(snail);
    }

    /**
         * @return {int} width of Aquarium
         */
    public final int getWidth(){
        return this.width;
    }

    /**
         * @return {int} height of Aquarium
         */
    public final int getHeight(){
        return this.height;
    }

    /**
     * Move Guppies, Piranhas, Foods, Coins, and Snail
     * Create new Guppy randomly
     * @param {double} seconds elapsed since this method called last time
     * @return {bool} is the program will be still running
     */
// public boolean main(double elapsedSecods);

    /**
     * For every Food find Foods with minimum distance to Guppy
     * @param {Guppy*} Guppy to find nearest Food
     * @return {Food*} Food with minimum distance to Guppy
     */
    public Food findNearestFood(Guppy guppy){
        LinkedList<Food> liFood = Data.getFoods();

        if(!(liFood.isEmpty())) {
            double minDistance,tempDistance;
            Food nearestFood, tempFood;

            LinkedListIterator<Food> liIteratorFood = liFood.getFirstIterator();
            nearestFood = liIteratorFood.getContent();
            minDistance = guppy.getPosition().magnitude(nearestFood.getPosition());
            
            while (liIteratorFood.getNext() != liFood.getLastIterator()) {
                tempFood = liIteratorFood.getContent();
                tempDistance = guppy.getPosition().magnitude(tempFood.getPosition());
                if (tempDistance < minDistance) {
                    minDistance = tempDistance ;
                    nearestFood = tempFood;
                }
                liIteratorFood = liIteratorFood.getNext();
            }
            
            return nearestFood;

        } else {
            return null;
        }
    }
        
        /**
         * For every Coin find Coin with minimum distance to Snail
         * @param {Snail*} Snail to find nearest coin
         * @return {Coin*} Coin with minimum distance to snail
         */
    public Coin findNearestCoin(Snail snail){
        LinkedList <Coin> liCoin = Data.getCoins();

        if(!(liCoin.isEmpty())){

            double minDistance, tempDistance;
            Coin nearestCoin, tempCoin;

            LinkedListIterator<Coin> liIteratorCoin = liCoin.getFirstIterator();
            nearestCoin = liIteratorCoin.getContent();
            minDistance = snail.getPosition().magnitude(nearestCoin.getPosition());

            while (liIteratorCoin.getNext() != liCoin.getLastIterator()) {
                tempCoin = liIteratorCoin.getContent();
                tempDistance = snail.getPosition().magnitude(tempCoin.getPosition());
                if (tempDistance < minDistance) {
                    minDistance = tempDistance;
                    nearestCoin = tempCoin;
                }
                liIteratorCoin = liIteratorCoin.getNext();
            }

            return nearestCoin;
        } else {
            return null;
        }
    }
        
        /**
         * For every Guppy find Guppy with minimum distance to Piranha
         * @param {Piranha*} Piranha to find nearest Guppy
         * @return {Guppy*} Guppy with minimum distance to Piranha
         */
    public Guppy findNearestGuppy(Piranha piranha) {
        LinkedList<Guppy> liGuppy = Data.getGuppies();

        if(!(liGuppy.isEmpty())) {
            double minDistance, tempDistance;
            Guppy nearestGuppy, tempGuppy;

            LinkedListIterator<Guppy> liIteratorGuppy = liGuppy.getFirstIterator();
            nearestGuppy = liIteratorGuppy.getContent();
            minDistance = piranha.getPosition().magnitude(nearestGuppy.getPosition());

            while (liIteratorGuppy != liGuppy.getLastIterator()) {
                tempGuppy = liIteratorGuppy.getContent();
                tempDistance = piranha.getPosition().magnitude(tempGuppy.getPosition());
                if (tempDistance < minDistance) {
                    minDistance = tempDistance;
                    nearestGuppy = tempGuppy;
                }
                liIteratorGuppy = liIteratorGuppy.getNext();
            }

            return nearestGuppy;
        } else {
            return null;
        }    
    }

    /**
     * Create new Coin for every fish if the fish will produce coin
     */
    public void produceCoin(){
        LinkedListIterator <Piranha> currentPiranha = Data.getPiranhas().getFirstIterator();
        while (currentPiranha != null) {
            int coinValue = currentPiranha.getContent().isProduceCoin();
            if (coinValue > 0) {
                Data.getCoins().add(
                    new Coin(coinValue * Guppy.getPrice(),  currentPiranha.getContent().getPosition())
                );
            }
            currentPiranha = currentPiranha.getNext();
        }

        LinkedListIterator<Guppy> currentGuppy = Data.getGuppies().getFirstIterator();
        while (currentGuppy != null) {
            int coinValue = currentGuppy.getContent().isProduceCoin();
            if (coinValue > 0) {
                Data.getCoins().add(
                    new Coin(coinValue,currentGuppy.getContent().getPosition())
                );
            }
            currentGuppy = currentGuppy.getNext();
        }        
    }

    /**
     * For every fish, move fish to nearest food if hungry, or to dest
     * For every coin, move coin to ground
     * For every food, move food to food
     * Move snail to nearest coin
     * @param {double} elapsed seconds
     */
    public void moveObjects(double elapsedSeconds) {
        LinkedListIterator<Coin> currentCoin = Data.getCoins().getFirstIterator();

        while (currentCoin != null) {
            currentCoin.getContent().move(elapsedSeconds);
            currentCoin = currentCoin.getNext();
        }

        LinkedListIterator<Food> currentFood = Data.getFoods().getFirstIterator();
        while(currentFood != null){
            currentFood.getContent().move(this.height-(this.height/10), elapsedSeconds);
            if (currentFood.getContent().getPosition().getOrdinate() >= 9*this.height/10) {
                Food droppedFood = currentFood.getContent();
                Data.getFoods().remove(droppedFood);
            }
            currentFood = currentFood.getNext();
        }

        LinkedListIterator<Guppy> currentGuppy = Data.getGuppies().getFirstIterator();
        while (currentGuppy != null) {
            currentGuppy
                .getContent()
                .update(elapsedSeconds);
            currentGuppy
                .getContent()
                .setStarvingTimer(currentGuppy.getContent().getStarvingTimer() + elapsedSeconds);

            if (currentGuppy.getContent().isDie()){
                LinkedListIterator<Guppy> dropedGuppy = currentGuppy;
                currentGuppy = currentGuppy.getNext();
                Data.getGuppies().remove(dropedGuppy.getContent());
            } else {    
                if (currentGuppy.getContent().isStarving()) {
                    Food nearestFood = this.findNearestFood(currentGuppy.getContent());
                    if (nearestFood == null) {
                        currentGuppy
                            .getContent()
                            .moveToDestination(elapsedSeconds);
                    } else {
                        currentGuppy
                            .getContent()
                            .moveToDestination(nearestFood.getPosition(), elapsedSeconds);
                        if (currentGuppy.getContent().getPosition() == nearestFood.getPosition()) {
                            currentGuppy.getContent().eat();
                            Data.getFoods().remove(nearestFood);
                        }
                    }
                } else {
                    currentGuppy
                        .getContent()
                        .moveToDestination(elapsedSeconds);
                }
                currentGuppy = currentGuppy.getNext();
            }

        }

        LinkedListIterator<Piranha > currentPiranha = Data.getPiranhas().getFirstIterator();
        while (currentPiranha != null) {
            currentPiranha
                .getContent()
                .setStarvingTimer(currentPiranha.getContent().getStarvingTimer() + elapsedSeconds);
            if (currentPiranha.getContent().isDie()){
                LinkedListIterator<Piranha> dropedPiranha = currentPiranha;
                currentPiranha = currentPiranha.getNext();
                Data.getPiranhas().remove(dropedPiranha.getContent());
            } else {
                if (currentPiranha.getContent().isStarving()) {
                    Guppy nearestGuppy = findNearestGuppy(currentPiranha.getContent());
                    if (nearestGuppy == null) {
                        currentPiranha
                            .getContent()
                            .moveToDestination(elapsedSeconds);
                    } else {
                        currentPiranha
                            .getContent()
                            .moveToDestination(nearestGuppy.getPosition(), elapsedSeconds);
                        if (currentPiranha.getContent().getPosition() == nearestGuppy.getPosition()) {
                            currentPiranha.getContent().eat(nearestGuppy.getGrowthStepInt());
                            Data.getGuppies().remove(nearestGuppy);
                        }
                    }
                } else {
                    currentPiranha
                        .getContent()
                        .moveToDestination(elapsedSeconds);
                }
                currentPiranha = currentPiranha.getNext();
            }
            
        }

        Snail currentSnail = Data.getSnail();
        if(!(Data.getCoins().isEmpty())){
            Coin nearestcoin = findNearestCoin(currentSnail);
            currentSnail.moveToDestination(nearestcoin.getPosition(), elapsedSeconds);
            if (currentSnail.getPosition() == nearestcoin.getPosition()) {
                Data.getCoins().remove(nearestcoin);
                Data.setMoney(Data.getMoney()+nearestcoin.getValue());
            }
        }
    
    }

    /**
     * Draw all entity
     */
    public void draw() {
        //
    }

    /**
         * Draw a drawable
         * @param {Drawable*} drawable object to draw
         */
    public void drawDrawable(Drawable  drawable){

    }

    public void buyGuppy() {
        if (Data.getMoney() >= Guppy.getPrice()){
            Data.setMoney(Data.getMoney() - Guppy.getPrice());
            Guppy g = new Guppy();
            Data.getGuppies().add(g);
	    }
    }

    public void buyEgg() {
        if(Data.getMoney() >= Data.getEggPrice()){
            Data.setMoney(Data.getMoney() - Data.getEggPrice());
            Data.setEgg(Data.getEgg()+1);
	    }
    }

    public void buyPiranha() {
        if(Data.getMoney() >= Piranha.getPrice()) {
            Data.setMoney(Data.getMoney()-Piranha.getPrice());
            Piranha p = new Piranha();
            Data.getPiranhas().add(p);
        }
    }

    /**
     * The game has been finished and the player wins
     * return {bool} Data.getEgg == WinCondition
     */
    public boolean winState() {
        return Data.getEgg() == this.WIN_CONDITION;
    }

    /**
     * The game has been finished and the player lose
     * return {bool} there is no fish, coin, and money < 100
     */
    public boolean loseState() {
        return 
            Data.getGuppies().isEmpty() &&
            Data.getPiranhas().isEmpty() &&
            Data.getCoins().isEmpty() &&
            Data.getMoney() < Guppy.getPrice();

    }

    public static void main( String[] args ) {
        System.out.println( "Hello World!" );

        Data.setMoney(200);
        System.out.println(Data.getMoney());
    }
}
