
//import edu.princeton.cs.introcs.StdDraw;
/**
 * NBody is a class that will actually run your simulation. This class will have NO constructor.
 * The goal of this class is to simulate a universe specified in one of the data files.*/
public class NBody {
    // return the radius of the universe
    public static double readRadius(String dir){
        In in = new In(dir);
        int num_plantes = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    // return an array of bodies
    public static Body[] readBodies(String dir){
        In in = new In(dir);
        int num_plantes = in.readInt();
        double radius = in.readDouble();

        Body[] bodies = new Body[num_plantes];
        int i = 0;

        // start to read bodies
        while (i < num_plantes){
            double xpos = in.readDouble();
            double ypos = in.readDouble();
            double xvel = in.readDouble();
            double yvel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();

            bodies[i] = new Body(xpos,ypos,xvel,yvel,mass,img);
            i += 1;
        }
        return bodies;
    }

    public static void main(String[] args) {
        /**
         * 1. Collecting all needed input
         * 2. Drawing the background
         * 3. Drawing one body
         * 4. Drawing more than one body
         * */

        // 1. Collecting all needed input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        String bgImg = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);



        // animation
        double time = 0;
        while (time < T){

            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            // calculate the net forces
            for (int i=0; i<bodies.length;i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            // update the positions of bodies
            for (int i=0; i<bodies.length;i++){
                bodies[i].update(dt,xForces[i],yForces[i]);
            }
            // start to draw
            StdDraw.clear();
            // draw the background
            StdDraw.picture(0,0,bgImg);

            // draw every body
            for (Body body:bodies){
                body.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);  // pause for 10 millisecond

            // update time
            time += dt;

        }

        // print the final results
        System.out.println(bodies.length);
        System.out.printf("%.2e\n",radius);
        for (Body body:bodies){
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %12.4e %15s\n",
                    body.xxPos,body.yyPos,body.xxVel,body.yyVel,body.mass,body.imgFileName);
        }
    }


}
