public class Planet{
	static final double GRAVITATIONAL_CONSTANT = 6.67e-11;
	
	public double xxPos;//Its current x position
	public double yyPos;// Its current y position
	public double xxVel;// Its current velocity in the x direction
	public double yyVel;// Its current velocity in the y direction
	public double mass;// Its mass
	/** The name of the file that corresponds to the image that depicts the planet (for example, jupiter.gif)*/
	public String imgFileName;
	
	//constructor
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
				  xxPos = xP;
				  yyPos = yP;
				  xxVel = xV;
				  yyVel = yV;
				  mass = m;
				  imgFileName = img;
			  }
	//constructor		  
	public Planet(Planet p){
				  xxPos = p.xxPos;
				  yyPos = p.yyPos;
				  xxVel = p.xxVel;
				  yyVel = p.yyVel;
				  mass = p.mass;
				  imgFileName = p.imgFileName;		
	}		  
	
	//calculates the distance between two Planets
	public double calcDistance(Planet p){
		//xxdis is the difference between thisplanet and the given one on x-axis
		//rrdis is the difference between thisplanet and the given one
		double xxdis, yydis, rrdis;
		xxdis = xxPos-p.xxPos;
		yydis = yyPos-p.yyPos;
		rrdis = Math.sqrt(xxdis*xxdis + yydis*yydis);
		return rrdis;
	}
	
	//returns a double describing the force exerted on this planet by the given planet
	public double calcForceExertedBy(Planet p){
		double rrdis = this.calcDistance(p); //rrdis is the difference between thisplanet and the given one
		double f; //f is the force exerted on this planet by the given planet
		f = GRAVITATIONAL_CONSTANT*mass*p.mass/(rrdis*rrdis);
		return f;
	}
	//force exerted in the X directions
	public double calcForceExertedByX(Planet p){
		//rrdis is the difference between thisplanet and the given one
		double rrdis = this.calcDistance(p); 
		//f is the force exerted on this planet by the given planet
		double f = this.calcForceExertedBy(p); 
		//xxdis is the difference between the given planet and thisplanet on x-axis
		double xxdis = p.xxPos - xxPos;
		double xxforce = xxdis*f/rrdis;
		return xxforce;
	} 
	//force exerted in the Y directions
	public double calcForceExertedByY(Planet p){
		double yyforce = (p.yyPos - yyPos)*this.calcForceExertedBy(p)/this.calcDistance(p);
		return yyforce;
	}

	/** take in an array of Planets and calculate the net X force exerted by all planets in that array upon
	 * the current Planet */
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double result = 0;
		int i = 0;
		for(i=0; i<allPlanets.length;++i){
			if(!(this.equals(allPlanets[i]))){
				result = result + this.calcForceExertedByX(allPlanets[i]);
			}
		}
		return result;
	} 
	/** take in an array of Planets and calculate the net Y force exerted by all planets in that array upon
	 * the current Planet */
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double result = 0;
		int i = 0;
		for(i=0; i<allPlanets.length;++i){
			if(!(this.equals(allPlanets[i]))){
				result = result + this.calcForceExertedByY(allPlanets[i]);
			}
		}
		return result;
	} 	
	
	/** update the planet’s position and velocity instance variables (this method does not need to return
	 * anything). dt means a small period of time, fX means net force on x direction, fY means net force
	 * on Y direction */
	public void update(double dt, double fX, double fY){
		double xxAcceleration = fX/this.mass;
		double yyAcceleration = fY/this.mass;
		this.xxVel = this.xxVel + dt*xxAcceleration;
		this.yyVel = this.yyVel + dt*yyAcceleration;
		this.xxPos = this.xxPos + dt*this.xxVel;
		this.yyPos = this.yyPos + dt*this.yyVel;
	}
	//???
	/** a planet to be able to draw itself at its appropriate position*/
	public void draw(){
		String imageToDraw = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imageToDraw);
	}
}