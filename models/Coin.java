class Coin extends Drawable{
	private static final String assetPathCoin = "assets/img/coin_shnine.png";
	private static final String assetPathRuby = "assets/img/ruby.png";
	private static final int MAX_VELOCITY = 40;

	/**
	 * Constuctor, initiate position and value
	 * @param value coin value
	 * @param position coin position
	 */
	public Coin(int value, Position position) {
		this.position = new Position(position.getAbsis(), position.getOrdinate());	
		this.value = value;
	}

	public void move(double elapsedSeconds) {
		Position bottom = new Position(0, Data.maxHeight-(Data.maxHeight/10));
		this.getPosition().moveVertical(bottom, elapsedSeconds);
	}

	public Position getPosition() {
		return this.Position;
	}

	public int getValue() {
		return this.Value;
	}

	public String getAssetPath() {
		if (this.getValue < 50)
			return this.assetPathCoin;
		else
			return this.assetPathRuby;
	}
}
