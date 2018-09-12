
public class Body {
	private double myXVel;
	private double myYVel;
	private double myX;
	private double myY;
	private double myMass;
	private String myName;
	public Body(double x, double y, double xv, double yv, double mass, String filename){
		myXVel = xv;
		myYVel = yv;
		myX = x;
		myY = y;
		myMass = mass;
		myName = filename;
	}

	public Body(Body b) {
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myX = b.getX();
		myY = b.getY();
		myMass = b.getMass();
		myName = b.getName();
	}
	
	/**
	 * Return the X-coordinate of the Body
	 * @return Current X position
	 */
	public double getX() {
		return myX;
	}
	
	/**
	 * Return the Y-coordinate of the Body
	 * @return Current Y position
	 */
	public double getY() {
		return myY;
	}
	
	/**
	 * Return the X component of the Body's velocity
	 * @return X component of velocity
	 */
	public double getXVel() {
		return myXVel;
	}
	
	/**
	 * Returns Y component of Body's velocity
	 * @return Y component of velocity
	 */
	public double getYVel() {
		return myYVel;
	}
	
	/**
	 * Returns mass of the Body
	 * @return Mass of Stellar Body
	 */
	public double getMass() {
		return myMass;
	}
	
	/**
	 * Returns Body's name
	 * @return Name of Stellar Body
	 */
	public String getName() {
		return myName;
	}
	
	/**
	 * Calculates the distance between Body and an other body "b"
	 * @param b the other Body object to which distance is calculated
	 * @return Distance between Body and b
	 */
	public double calcDistance(Body b) {
		double deltaX = myX - b.getX();
		double deltaY = myY - b.getY();
		return Math.sqrt(deltaY * deltaY + deltaX * deltaX);
	}
	
	/**
	 * Calculates the scalar magnitude of force between Body and an other body "b"
	 * @param b the other Body object which causes the force to be exerted
	 * @return Force between Body and b
	 */
	public double calcForceExertedBy(Body b) {
		final double G = 6.67*1e-11;
		return (myMass * b.getMass()) * G / Math.pow(calcDistance(b),2);
	}
	
	/**
	 * Calculates the X component of the gravitational force caused by b
	 * @param b the other Body object which causes the force to be exerted
	 * @return X component of force between Body and b
	 */
	public double calcForceExertedByX(Body b) {
		return calcForceExertedBy(b) * (b.getX() - myX) / calcDistance(b);
	}
	
	/**
	 *  Calculates the Y component of the gravitational force caused by b
	 * @param b the other Body object which causes the force to be exerted
	 * @return Y component of force between Body and b
	 */
	public double calcForceExertedByY(Body b) {
		return calcForceExertedBy(b) * (b.getY() - myY) / calcDistance(b);
	}
	
	/**
	 * Calculates the sum of all X forces by all bodies (other than the current one)
	 * @param bodies the array containing information on all the bodies
	 * @return Net X force from all bodies
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double totalX = 0.0;
		for(Body b : bodies) {
			if(! b.equals(this)) {
				totalX = totalX + this.calcForceExertedByX(b);
			}
		}
		return totalX;
	}
	
	/**
	 * Calculates the sum of all Y forces by all bodies (other than the current one)
	 * @param bodies the array containing information on all the bodies
	 * @return  Net Y force from all bodies
	 */
	public double calcNetForceExertedByY(Body[] bodies) {
		double totalY = 0.0;
		for(Body b : bodies) {
			if(! b.equals(this)) {
				totalY = totalY + calcForceExertedByY(b);
			}
		}
		return totalY;
	}
	
	/**
	 * Updates the X and Y velocity and position for a given increment of time
	 * @param deltaT time increment to update variables
	 * @param xforce Net Y force acting on a Body
	 * @param yforce Net X force acting on a Body
	 */
	public void update(double deltaT, double xforce, double yforce) {
		double ax = xforce / myMass;
		double ay = yforce / myMass;
		double nvx = myXVel + deltaT *ax;
		double nvy = myYVel + deltaT *ay;
		double nx = myX + deltaT * nvx;
		double ny = myY + deltaT * nvy;
		myXVel = nvx;
		myYVel = nvy;
		myX = nx;
		myY = ny;
	}
	/**
	 * Draws the motion of a Body
	 */
	public void draw() {
		StdDraw.picture(myX, myY, "images/"+myName);
	}
}

