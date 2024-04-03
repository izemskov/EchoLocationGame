/**
 * Created by Ilya Zemskov on 19.01.2017.
 */
package ru.develgame.echolocationgame.labyrinth;

public class Labyrinth {
    private Location[][] locations;
    private int dimension;

    public int getDimension() {return dimension;}
    public Location[][] getLocations() {return locations;}

    public Labyrinth(int dimension) throws Exception {
        if (dimension <= 1)
            throw new Exception("Can't create labyrinth with zero or one dimension");

        if (dimension > 50)
            throw new Exception("Too bid dimension");

        this.dimension = dimension;
        locations = new Location[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                locations[i][j] = new Location();
            }
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (j + 1 < dimension)
                    locations[i][j].setRightLocation(locations[i][j + 1]);

                if (j - 1 >= 0)
                    locations[i][j].setLeftLocation(locations[i][j - 1]);

                if (i + 1 < dimension)
                    locations[i][j].setBottomLocation(locations[i + 1][j]);

                if (i - 1 >= 0)
                    locations[i][j].setTopLocation(locations[i - 1][j]);
            }
        }
    }
}

