package Model;

public abstract class Variant {
	public Variant() {
	}

	public void draw() {
	}

	public abstract boolean move(String origin, String destination);

	protected abstract boolean validateMove();

	protected abstract boolean validateRules();

	protected abstract boolean checkWin();
}
