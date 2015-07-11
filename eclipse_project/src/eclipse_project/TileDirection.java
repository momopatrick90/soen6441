package eclipse_project;

/**
 *	This class is used an object inside tile class to encapsulate the direction and color attribute.
 *	For direction attribute possible values can be as follow: {1: Left,2: Right,3: Up,4: Down}.
 *  For color attribute possible values can be as follow: {1:White,2:Red,3:Black,4:Green,5,Purple,6:Blue,7:Orange}.
 */
public class TileDirection {

	public int direction;
	public int color;
	
	public TileDirection(){
		
	}
	
	public TileDirection(int direction, int color){
		this.direction =  direction;
		this.color = color;
	}
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
	
}
