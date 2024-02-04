
public class obstacle {
	
	private int number;
    private int X;
    private int Y;
    private int Bx;
    private int By;
  
    public obstacle(int number, int X,int Y,int Bx,int By) {
        this.number = number;
        this.X = X;
        this.Y = Y;
        this.Bx = Bx;
        this.By = By;
    }

	public int getBx() {
		return Bx;
	}

	public void setBx(int bx) {
		Bx = bx;
	}

	public int getBy() {
		return By;
	}

	public void setBy(int by) {
		By = by;
	}

	@Override
	public String toString() {
		return "obstacle [number=" + number + ", X=" + X + ", Y=" + Y + ", Bx=" + Bx + ", By=" + By + "]";
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
}
