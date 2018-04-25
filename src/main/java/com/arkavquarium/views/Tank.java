package com.arkavquarium.views;

import com.arkavquarium.models.Coin;
import com.arkavquarium.models.Data;
import com.arkavquarium.models.Drawable;
import com.arkavquarium.models.Food;
import com.arkavquarium.models.Guppy;
import com.arkavquarium.models.LinkedListIterator;
import com.arkavquarium.models.Piranha;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Tank extends JPanel  {
  private static final String backgroundAssetPath = "src/main/resources/img/background.png";
  private static final String eggAssetPath = "src/main/resources/img/egg.png";
  private static final int margin = 20;
  private static final int fontSize = 20;
  private Font fontFamily;
  private JFrame frame;
  private boolean isWin;
  private boolean isLose;

  /**
   * Create frame with title "Arkavquarium",
   *  size according to data maxwidth and maxheight,
   *  visible to true,
   *  end exit on close.
   * Set isWin to false.
   * Set isLose to false.
   * Set fontFamily to VCR OSD MONO
   */
  public Tank() {
    this.frame = new JFrame("Arkavquarium");
    this.frame.add(this);
    this.frame.setSize(Data.getMaxWidth(), Data.getMaxHeight());
    this.frame.setVisible(true);
    this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.isWin = false;
    this.isLose = false;
    try {
      this.fontFamily =
        Font
          .createFont(Font.TRUETYPE_FONT, new File("src/main/resources/ttf/VCR_OSD_MONO_1.001.ttf"))
          .deriveFont((float) Tank.fontSize);
    } catch (Exception e) {
      this.fontFamily = new Font("Monospaced", Font.PLAIN, Tank.fontSize);
    }
  }

  public void addMouseListenerToFrame(MouseAdapter mouseAdapter) {
    frame.addMouseListener(mouseAdapter);
  }

  public void addKeyListenerToPanel(KeyListener keyListener) {
    frame.addKeyListener(keyListener);
  }

  /**
   * Draw all entity.
   */
  public void paint(Graphics g) {
    Toolkit t = Toolkit.getDefaultToolkit();
    Image image;

    g.setFont(this.fontFamily);
    image = t.getImage(Tank.backgroundAssetPath);
    g.drawImage(
        image,
        getWidth() / 2 - image.getWidth(this) / 2,
        getHeight() / 2 - image.getHeight(this) / 2,
        this
    );

    LinkedListIterator<Guppy> guppyIt = Data.getGuppies().getFirstIterator();
    while (guppyIt != null) {
      drawDrawable(g, guppyIt.getContent());
      guppyIt = guppyIt.getNext();
    }

    LinkedListIterator<Piranha> piranhaIt = Data.getPiranhas().getFirstIterator();
    while (piranhaIt != null) {
      drawDrawable(g, piranhaIt.getContent());
      piranhaIt = piranhaIt.getNext();
    }

    LinkedListIterator<Coin> coinIt = Data.getCoins().getFirstIterator();
    while (coinIt != null) {
      drawDrawable(g, coinIt.getContent());
      coinIt = coinIt.getNext();
    }

    LinkedListIterator<Food> foodIt = Data.getFoods().getFirstIterator();
    while (foodIt != null) {
      drawDrawable(g, foodIt.getContent());
      foodIt = foodIt.getNext();
    }

    drawDrawable(g, Data.getSnail());

    /* Draw Money */
    Image money = t.getImage(Coin.getCoinAssetPath());
    g.drawImage(money, Tank.margin, Tank.margin, this);
    try {
      g.setFont(this.fontFamily.deriveFont((float) money.getHeight(this)));
    } catch(Exception e) {
      // sometimes NullPointerException
    }
    g.drawString(
        new Integer(Data.getMoney()).toString(),
        Tank.margin + money.getWidth(this),
        Tank.margin + money.getHeight(this)
    );
    /* Draw Egg */
    Image egg = t.getImage(Tank.eggAssetPath);
    g.drawImage(egg, Tank.margin, Tank.margin + money.getHeight(this),this);
    g.setFont(this.fontFamily.deriveFont((float) egg.getHeight(this)));
    g.drawString(
        new Integer(Data.getEgg()).toString(),
        Tank.margin + egg.getWidth(this),
        Tank.margin + egg.getHeight(this) + money.getHeight(this)
    );

    g.setFont(this.fontFamily);
    g.drawString(
        "Press G to buy Guppy (100 coins)",
        Tank.margin,
        Tank.margin + egg.getHeight(this) + money.getHeight(this) + Tank.fontSize
    );
    g.drawString(
        "Press P to buy Piranha (1000 coins)",
        Tank.margin,
        Tank.margin + egg.getHeight(this) + money.getHeight(this) + 2 * Tank.fontSize
    );
    g.drawString(
        "Press E to buy Egg (1000 coins)\n",
        Tank.margin,
        Tank.margin + egg.getHeight(this) + money.getHeight(this) + 3 * Tank.fontSize
    );

    if (this.isWin) {
      Image congrats = t.getImage("src/main/resources/img/congratulation.png");
      g.drawImage(congrats, (this.getWidth() - congrats.getWidth(this)) / 2, Tank.margin * 2, this);
    } else if (this.isLose) {
      Image lose = t.getImage("src/main/resources/img/lose.png");
      g.drawImage(lose, (this.getWidth() - lose.getWidth(this)) / 2, Tank.margin * 2, this);
    }
  }

  /**
   * Draw a drawable.
   * @param g surface to draw to
   * @param drawable object to draw
   */
  public void drawDrawable(Graphics g, Drawable drawable) {
    Image image;
    Toolkit t = Toolkit.getDefaultToolkit();

    try {
      image = t.getImage(drawable.getAssetPath());
      g.drawImage(t.getImage(drawable.getAssetPath()),
          (int) (drawable.getPosition().getAbsis() - image.getWidth(this) / 2),
          (int) (drawable.getPosition().getOrdinate() - image.getHeight(this)), this);
    } catch (Exception e) {
      // the object that are going to be drawn may be destructed before drawn
      // this exception is necessary to prevent stack trace shown.
    }
  }

  /**
   * Set state to win.
   */
  public void setWin() {
    this.isWin = true;
  }

  /**
   * Set state to lose.
   */
  public void setLose() {
    this.isLose = true;
  }
}
