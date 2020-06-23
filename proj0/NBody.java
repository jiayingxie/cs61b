public class NBody{//要按这个编译才行，不加encoding出错：javac -encoding UTF-8 NBody.java

    /**Given a file name, it should return a double corresponding to the radius of the universe in that file*/
    public static double readRadius(String Filename){
        In in = new In(Filename);
        int firstItemInFile = in.readInt();
        // the radius of the universe
        double radiusOfTheUniverse = in.readDouble();
        return radiusOfTheUniverse;
    }

    /** Given a file name, it should return an array of Planets corresponding to the planets in the file*/
    public static Planet[] readPlanets(String Filename) {
        In in = new In(Filename);
        /** first value is an integer N which represents the
         * number of planets*/
        int NumberOfPlanets = in.readInt();

        /** The second value is a real number R which represents
         * the radius of the universe, used to determine the
         * scaling of the drawing window.*/
        /** caution, could not use the method readRadius, because
         * if use that method, than, the second data actually did
         * not read in this function*/
        double radiusOfTheUniverse = in.readDouble();

        /**there are N rows, and each row contains 6 values. */
        Planet[] planets = new Planet[NumberOfPlanets];
        int i = 0; // i is used to search array;
        for (i = 0; i < NumberOfPlanets; ++i){
            /** The first two values are the x- and y-coordinates
             * of the initial position; */
            double xxP = in.readDouble();
            double yyP = in.readDouble();
            /** the next pair of values are the x- and y-components
             * of the initial velocity; */
            double xxV = in.readDouble();
            double yyV = in.readDouble();
            /** the fifth value is the mass; */
            double mmP = in.readDouble();
            /** the last value is a String that is the name of an
             * image file used to display the planets. Image files
             * can be found in the images directory. */
            String img = in.readString();

            planets[i] = new Planet(xxP, yyP, xxV, yyV, mmP, img);
        }

        return planets;
    }

    public static void main(String[] args){
        /** Store the 0th and 1st command line arguments as doubles named T and dt*/
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        /** Store the 2nd command line argument as a String named filename.*/
        String filename = args[2];

        /** Read in the planets and the universe radius from the file described by filename using your methods
         * from earlier in this assignment.*/
        Planet[] planets = readPlanets(filename); //./data/planets.txt
        double radiusOfTheUniverse = readRadius(filename);
        double size = radiusOfTheUniverse * 2;
        /** Drawing the Background*/
        /** First, set the scale so that it matches the radius of the universe.*/
        StdDraw.setScale(-radiusOfTheUniverse, radiusOfTheUniverse);
        /** Then draw the image starfield.jpg as the background. */
        String imageToDraw = "images/starfield.jpg";
        //StdDraw.picture(0, 0, imageToDraw, size, size); //???位置不确定
        StdDraw.picture(0, 0, imageToDraw);
        /** Drawing All of the Planets*/
        int i = 0;
        for (i = 0; i < planets.length ; ++i) {
            planets[i].draw();
        }

        /** Creating an Animation */
        /** Enables double buffering.*/
        StdDraw.enableDoubleBuffering();
        /** Create a time variable and set it to 0. Set up a loop to loop until this time variable is T.*/
        double timeVariable = 0;
        while (timeVariable < T) {
            /** For each time through the loop, do the following:*/

            // Create an xForces array and yForces array.
            double[] xForces = new double [planets.length];
            double[] yForces = new double [planets.length];
            /** Calculate the net x and y forces for each planet, storing these in the xForces and yForces
             * arrays respectively.*/
            for (i = 0; i < planets.length; ++i) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            /** Call update on each of the planets.*/
            for (i = 0; i < planets.length; ++i) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            /** Then draw the background. */
            //StdDraw.picture(0, 0, imageToDraw, size, size); //???位置不确定
            StdDraw.picture(0, 0, imageToDraw);
            /** Drawing All of the Planets*/
            for (i = 0; i < planets.length ; ++i) {
                planets[i].draw();
            }

            /** Show the offscreen buffer*/
            StdDraw.show();
            /** Pause the animation for 10 milliseconds*/
            StdDraw.pause(10);

            timeVariable += dt;
        }

        /** Printing the Universe When the simulation is over, i.e. when you’ve reached time T, you should
         * print out the final state of the universe in the same format as the input, e.g.:*/
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radiusOfTheUniverse);
        for (i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
} 