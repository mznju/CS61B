public class NBody {
    public static double readRadius(String file){
        In in = new In(file);
        in.readInt();
        return in.readDouble();
    }




    
    public static Planet[] readPlanets(String file){

        In in = new In(file);
        int num = in.readInt();
        Planet[] allPlanets = new Planet[num];
        in.readDouble();
        //
        int i =0;
        while(!in.isEmpty()&&i<num){
            allPlanets[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
            i++;
        }
        return allPlanets;
       /* In in = new In(file);
        int num = in.readInt();  // 读取行星数量
        Planet[] allPlanets = new Planet[num];
        in.readDouble();  // 如果确实需要跳过某个值

        for(int i = 0; i < num; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();

            allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
        }
        return allPlanets;*/
    }
    private static int number(String file){
        In in = new In(file);
        int num = in.readInt();
        return num;
    }

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = NBody.readRadius(fileName);
        Planet[] allPlanets = NBody.readPlanets(fileName);
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        for(Planet p:allPlanets){
            p.draw();
        }
        double time = 0;
        int num = NBody.number(fileName);
        while(time!=T){
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            int i = 0;
            for(Planet p:allPlanets){
                xForces[i] = p.calcNetForceExertedByX(allPlanets);
                yForces[i] = p.calcNetForceExertedByY(allPlanets);
                i++;
            }
            for(Planet p:allPlanets){
                p.update(dt,p.calcNetForceExertedByX(allPlanets),p.calcNetForceExertedByY(allPlanets));
            }
            StdDraw.setScale(-radius,radius);
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Planet p:allPlanets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time+=dt;
        }
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }

    }


}
