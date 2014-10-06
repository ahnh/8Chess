package Model;

public abstract class Variant {
	public Variant() {
	}

	public void draw() {
	}

	public abstract boolean move(String Origin, String Destination);

	protected abstract boolean validateMove();

	protected abstract boolean validateRules();

	protected abstract boolean checkWin();
}
