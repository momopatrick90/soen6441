package eclipse_project;

import java.util.Random;

public class StartTile {
	
	private TileDirection left,right,up,down;
	
	public void randomizeTheTile()
	{
		Random randomColor = new Random();
	
		this.left = new TileDirection(1, randomColor.nextInt(6) + 1);
		this.right = new TileDirection(2, randomColor.nextInt(5) + 2);
		this.up = new TileDirection(3, randomColor.nextInt(6) + 3);
		this.down = new TileDirection(4, randomColor.nextInt(5) + 4);
	}
	
	public TileDirection getLeft() {
		return left;
	}

	public void setLeft(TileDirection left) {
		this.left = left;
	}

	public TileDirection getRight() {
		return right;
	}

	public void setRight(TileDirection right) {
		this.right = right;
	}

	public TileDirection getUp() {
		return up;
	}

	public void setUp(TileDirection up) {
		this.up = up;
	}

	public TileDirection getDown() {
		return down;
	}

	public void setDown(TileDirection down) {
		this.down = down;
	}
}
