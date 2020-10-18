public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** calculate the distance from this body to the given body*/
    public double calcDistance(Body b){
        return Math.sqrt(Math.pow((xxPos-b.xxPos),2)+Math.pow(yyPos-b.yyPos,2));
    }

    // calculate the force exerted by the given body
    public double calcForceExertedBy(Body b){

        return G*mass*b.mass/Math.pow(calcDistance(b),2);
    }

    // force x
    public double calcForceExertedByX(Body b){
        return (b.xxPos-xxPos)/calcDistance(b)*calcForceExertedBy(b);
    }

    // force y
    public double calcForceExertedByY(Body b){
        return (b.yyPos-yyPos)/calcDistance(b)*calcForceExertedBy(b);
    }

    // net force x
    public double calcNetForceExertedByX(Body[] bodies){
        double netForce = 0;
        for (Body body : bodies) {
            if (body.equals(this)) {
                continue;
            }
            netForce += calcForceExertedByX(body);
        }
        return netForce;
    }

    // net force y
    public double calcNetForceExertedByY(Body[] bodies){
        double netForce = 0;
        for (Body body : bodies) {
            if (body.equals(this)) {
                continue;
            }
            netForce += calcForceExertedByY(body);
        }
        return netForce;
    }

    // update the position
    public void update(double dt, double fX, double fY){

        double aX = fX/mass;
        double aY = fY/mass;

        xxVel += aX*dt;
        yyVel += aY*dt;

        xxPos += xxVel*dt;
        yyPos += yyVel*dt;
    }

    // draw itself on the canvas
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}
