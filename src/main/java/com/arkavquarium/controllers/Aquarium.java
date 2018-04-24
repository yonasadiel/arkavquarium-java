package com.arkavquarium.controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import com.arkavquarium.models.*;

public class Aquarium extends JPanel  {
    private static final int WIN_CONDITION = 3;
    private static final String backgroundAssetPath = "src/main/resources/img/background.png";
    private Timer timer;
    private JFrame f;
    
    /**
     * Construct with some guppies, one piranha and one snail 
     * with random position
     */
    public Aquarium() {
        f = new JFrame("Arkavquarium");

        Guppy guppy1 = new Guppy();
        Data.getGuppies().add(guppy1);


        Guppy guppy2 = new Guppy();
        Data.getGuppies().add(guppy2);

        Snail snail = new Snail();
        Data.setSnail(snail);
    }

    /**
     * Move Guppies, Piranhas, Foods, Coins, and Snail
     * Create new Guppy randomly
     * @param elapsedSeconds seconds elapsed since this method called last time
     * @return is the program will be still running
     */
    public void main() {
        f.add(this);
        f.addMouseListener(new MouseAdapter() {               
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (e.getButton() == MouseEvent.BUTTON1) {   
                    Position clickPos = new Position(e.getX(), e.getY());

                    LinkedListIterator<Coin> coinIt = Data.getCoins().getFirstIterator();
                    Coin clickedCoin = null;
                    while (coinIt != null && clickedCoin == null) {
                        if (coinIt.getContent().getPosition().equals(clickPos)) {
                            clickedCoin = coinIt.getContent();
                        }
                        coinIt = coinIt.getNext();
                    }

                    if (clickedCoin != null) {
                        Data.setMoney(Data.getMoney()+ clickedCoin.getValue());
                        Data.getCoins().remove(clickedCoin);
                    } else {
                        if ( Data.getMoney() > Food.getPrice() ) {
                            Food newFood = new Food(clickPos);
                            Data.getFoods().add(newFood);
                            Data.setMoney(Data.getMoney() - Food.getPrice());
                        }
                    }
                }
            }
        });
        
        f.setSize(Data.getMaxWidth(), Data.getMaxHeight());
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        timer = new Timer(1, new ActionListener(){ 
            public void actionPerformed(ActionEvent e1) {
                repaint();
            }
        });
        timer.start();
    }

    /**
     * For every Food find Foods with minimum distance to Guppy
     * @param guppy to find nearest Food
     * @return Food with minimum distance to Guppy
     */
    public Food findNearestFood(Guppy guppy){
        if (!(Data.getFoods().isEmpty())) {
            double minDistance,tempDistance;
            Food nearestFood, tempFood;

            LinkedListIterator<Food> itFood = Data.getFoods().getFirstIterator();
            nearestFood = itFood.getContent();
            minDistance = guppy.getPosition().magnitude(nearestFood.getPosition());
            
            while (itFood != null) {
                tempFood = itFood.getContent();
                tempDistance = guppy.getPosition().magnitude(tempFood.getPosition());
                if (tempDistance < minDistance) {
                    minDistance = tempDistance ;
                    nearestFood = tempFood;
                }
                itFood = itFood.getNext();
            }
            
            return nearestFood;
        } else {
            return null;
        }
    }
        
    /**
     * For every Coin find Coin with minimum distance to Snail
     * @param snail to find nearest coin
     * @return Coin with minimum distance to snail
     */
    public Coin findNearestCoin(Snail snail){
        LinkedList <Coin> liCoin = Data.getCoins();

        if(!(liCoin.isEmpty())){

            double minDistance, tempDistance;
            Coin nearestCoin, tempCoin;

            LinkedListIterator<Coin> itCoin = liCoin.getFirstIterator();
            nearestCoin = itCoin.getContent();
            minDistance = snail.getPosition().magnitude(nearestCoin.getPosition());

            while (itCoin != null) {
                tempCoin = itCoin.getContent();
                tempDistance = snail.getPosition().magnitude(tempCoin.getPosition());
                if (tempDistance < minDistance) {
                    minDistance = tempDistance;
                    nearestCoin = tempCoin;
                }
                itCoin = itCoin.getNext();
            }

            return nearestCoin;
        } else {
            return null;
        }
    }
        
    /**
     * For every Guppy find Guppy with minimum distance to Piranha
     * @param piranha Piranha to find nearest Guppy
     * @return Guppy with minimum distance to Piranha
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
     * @param elapsedSeconds since last invocation
     */
    public void moveObjects(double elapsedSeconds) {
        LinkedListIterator<Coin> currentCoin = Data.getCoins().getFirstIterator();

        while (currentCoin != null) {
            currentCoin.getContent().move(elapsedSeconds);
            currentCoin = currentCoin.getNext();
        }

        LinkedListIterator<Food> currentFood = Data.getFoods().getFirstIterator();
        while(currentFood != null){
            currentFood.getContent().move(Data.getMaxHeight()-(Data.getMaxHeight()/10), elapsedSeconds);
            if (currentFood.getContent().getPosition().getOrdinate() >= 9*Data.getMaxHeight()/10) {
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
                        if (currentGuppy.getContent().getPosition().equals(nearestFood.getPosition())) {
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
                        if (currentPiranha.getContent().getPosition().equals(nearestGuppy.getPosition())) {
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
            if (currentSnail.getPosition().equals(nearestcoin.getPosition())) {
                Data.getCoins().remove(nearestcoin);
                Data.setMoney(Data.getMoney()+nearestcoin.getValue());
            }
        }
    
    }

    /**
     * Draw all entity
     */
    public void paint(Graphics g) {
        this.moveObjects(0.001);
        this.produceCoin();
        //this.tank.draw_text("Panah untuk bergerak, r untuk reset, x untuk keluar", 18, 10, 10, 0, 0, 0);
        Toolkit t = Toolkit.getDefaultToolkit();
        Image image;
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        image = t.getImage(Aquarium.backgroundAssetPath);
        g.drawImage(image, getWidth()/2 - image.getWidth(this)/2, getHeight()/2 - image.getHeight(this)/2, this);

        LinkedListIterator<Guppy> guppyIt = Data.getGuppies().getFirstIterator();
        while (guppyIt != null) {
            image = t.getImage(guppyIt.getContent().getAssetPath());
            g.drawImage(image, 
                        (int) (guppyIt.getContent().getPosition().getAbsis() - image.getWidth(this) / 2), 
                        (int) (guppyIt.getContent().getPosition().getOrdinate() - image.getHeight(this)), this);
            guppyIt = guppyIt.getNext();
        }

        LinkedListIterator<Piranha> piranhaIt = Data.getPiranhas().getFirstIterator();
        while (piranhaIt != null) {
            image = t.getImage(piranhaIt.getContent().getAssetPath());
            g.drawImage(image,
                        (int) (piranhaIt.getContent().getPosition().getAbsis() - image.getWidth(this) / 2),
                        (int) (piranhaIt.getContent().getPosition().getOrdinate() - image.getHeight(this)), this);
            piranhaIt = piranhaIt.getNext();
        }

        LinkedListIterator<Coin> coinIt = Data.getCoins().getFirstIterator();
        while (coinIt != null) {
            image = t.getImage(coinIt.getContent().getAssetPath());
            g.drawImage(image,
                    (int) (coinIt.getContent().getPosition().getAbsis() - image.getWidth(this) / 2),
                    (int) (coinIt.getContent().getPosition().getOrdinate() - image.getHeight(this)), this);
            coinIt = coinIt.getNext();
        }

        LinkedListIterator<Food> foodIt = Data.getFoods().getFirstIterator();
        while (foodIt != null) {
            image = t.getImage(foodIt.getContent().getAssetPath());
            g.drawImage(t.getImage(foodIt.getContent().getAssetPath()),
                    (int) (foodIt.getContent().getPosition().getAbsis() - image.getWidth(this) / 2),
                    (int) (foodIt.getContent().getPosition().getOrdinate() - image.getHeight(this)), this);
            foodIt = foodIt.getNext();
        }

        Snail currentSnail;
        currentSnail = Data.getSnail();
        image = t.getImage(currentSnail.getAssetPath());
        g.drawImage(image,
                    (int) (currentSnail.getPosition().getAbsis() - image.getWidth(this) / 2),
                    (int) (currentSnail.getPosition().getOrdinate() - image.getHeight(this)), this);
        
        // //Draw Money
        // this.tank.draw_image("assets/img/coin_shine.png", 15, 50);
        // this.tank.draw_text(std.to_string(Data.getMoney()), 35, 30, 35, 0, 0, 0);
        // //Draw Egg
        // this.tank.draw_image("assets/img/egg.png", this.getWidth() - 30, 55);
        // this.tank.draw_text(std.to_string(Data.getEgg()), 35, this.getWidth()-70, 35, 0, 0, 0);
        
        // this.tank.draw_text("Press G to buy Guppy (100 coins)        Press P to buy Piranha (1000 coins)          Press E to buy Egg (1000 coins)", 20, 15, 77, 0, 0, 0);
        
        // if (this.winState()) {
        //     this.tank.draw_text("CONGRATULATIONS!!!", 25, (int)this.width/2, (int)this.height/2, 0, 0, 0);
        // } else if (this.loseState()) {
        //     this.tank.draw_text("YOU LOSE!!!", 25, (int)this.width/2, (int)this.height/2, 0, 0, 0);
        // } 
        
        // this.tank.update_screen();
        // //
    }

    /**
     * Draw a drawable
     * @param drawable object to draw
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
     * @return Data.getEgg == WinCondition
     */
    public boolean winState() {
        return Data.getEgg() == Aquarium.WIN_CONDITION;
    }

    /**
     * The game has been finished and the player lose
     * @return there is no fish, coin, and money {@literal <} 100
     */
    public boolean loseState() {
        return 
            Data.getGuppies().isEmpty() &&
            Data.getPiranhas().isEmpty() &&
            Data.getCoins().isEmpty() &&
            Data.getMoney() < Guppy.getPrice();
    }
}
