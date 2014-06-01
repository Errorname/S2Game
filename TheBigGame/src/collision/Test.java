package collision;
import collision.*;
public class Test{
	public static void main(String[] args) {
		Box b1 = new Box(0,0,2,3);
		Box b2 = new Box(1,3,3,3);
		Box b3 = new Box(0,3,2,2);
		Box b4 = new Box(0,4,3,3);
		Box b5 = new Box(6,2,3,3);

		System.out.println("b1 et b2  + : " + b1.collisionB(b2));
		System.out.println("b1 et b3  + : " + b1.collisionB(b3));
		System.out.println("b1 et b4  + : " + b1.collisionB(b4));
		System.out.println("b1 et b5  + : " + b1.collisionB(b5));

	}
}