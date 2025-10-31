public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public  static final double g = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos));
    }
    public double calcForceExertedBy(Planet p){
        return Planet.g * this.mass * p.mass / (this.calcDistance(p)*this.calcDistance(p));
    }
    public double  calcForceExertedByX(Planet p){
        double dx = p.xxPos-this.xxPos;
        return this.calcForceExertedBy(p) * dx /this.calcDistance(p);
    }
    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos-this.yyPos;
        return this.calcForceExertedBy(p) * dy /this.calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double ret = 0;
        for(Planet p:allPlanets){
            if(!this.equals(p)){
                ret+=this.calcForceExertedByX(p);
            }
        }
        return ret;

    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double ret = 0;
        for(Planet p:allPlanets){
            if(!this.equals(p)){
                ret+=this.calcForceExertedByY(p);
            }
        }
        return ret;

    }
    public void update(double dt, double fX, double fY){
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel += ax *dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel *dt;
    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+this.imgFileName);
    }
}
