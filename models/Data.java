class Data {
	private static LinkedList<Guppy> guppies = new LinkedList<Guppy>();
	private static LinkedList<Piranha> piranhas = new LinkedList<Piranha>();
	private static LinkedList<Food> foods = new LinkedList<Food>();
	private static LinkedList<Coin> coins = new LinkedList<Coin>();
	private static Snail snail = null;
	private static int money;
	private static int egg;
	private static int eggPrice;
	public static int maxHeight = 768;
	public static int maxHeight = 1366;

	/**
	 * Do nothing
	 */
	public Data() {}

	/**
	 * @return LinkedList of guppies
	 */
	public static LinkedList<Guppy> getGuppies() {
		return this.guppies;	
	}
	
	/**
	 * @return LinkedList of piranhas
	 */
	public static LinkedList<Piranha> getPiranhas() {
		return this.piranhas;
	}

	/**
	 * @return LinkedList of foods
	 */
	public static LinkedList<Food> getFoods() {
		return this.foods;	
	}

	/**
	 * @return LinkedList of coins
	 */
	public static LinkedList<Coin> getCoins() {
		return this.coins;	
	}

	/**
	 * @return Snail
	 */
	public static Snail getSnail() {
		return this.snail;
	}
	
	/**
	 * @return current number of egg
	 */
	public static int getEgg() {
		return this.egg;	
	}
	
	/**
	 * @return cuurent egg price
	 */
	public static int getEggPrice() {
		return this.eggPrice;	
	}

	/**
	 * @return current amount of money
	 */
	public static int getMoney() {
		return this.money;	
	}

	/**
	 * Set new egg price
	 * @param eggPrice new egg price
	 */
	public static void setEggPrice(int eggPrice) {
		this.eggPrice = eggPrice;
	}

	/**
	 * Set new amount of money
	 * @param money new amount of money
	 */
	public static void setMoney(int money) {
		this.money = money;	
	}

	/**
	 * Set new amount of egg
	 * @param egg new amount of egg
	 */
	public static void setEgg(int egg) {
		this.egg = egg;	
	}

	/**
	 * @param Snail new snail
	 */
	public static void setSnail(Snail snail) {
		this.snail = snail;
	}

	/**
	 * Destruct all data and reinitiate
	 */
	public static void clear() {
		guppies = new LinkedList<Guppy>();
		piranhas = new LinkedList<Piranha>();
		foods = new LinkedList<Food>();
		coins = new LinkedList<Coin>();
		snail = null;
	}
}
