/**
 * Created by Ilya Zemskov on 19.01.2017.
 */
package ru.develgame.echolocationgame.labyrinth;

import java.util.ArrayList;
import java.util.Random;

public class GenLabyrinth {

    private static CurrentLocation getNotVisitedNeighbour(Labyrinth l, boolean[][] isVisited, CurrentLocation currentLocation) {
        ArrayList<CurrentLocation> neighbours = new ArrayList<>();

        if (currentLocation.j + 1 < l.getDimension() && !isVisited[currentLocation.i][currentLocation.j + 1])
            neighbours.add(new CurrentLocation(currentLocation.i, currentLocation.j + 1,
                    l.getLocations()[currentLocation.i][currentLocation.j + 1]));

        if (currentLocation.j - 1 >= 0 && !isVisited[currentLocation.i][currentLocation.j - 1])
            neighbours.add(new CurrentLocation(currentLocation.i, currentLocation.j - 1,
                    l.getLocations()[currentLocation.i][currentLocation.j - 1]));

        if (currentLocation.i + 1 < l.getDimension() && !isVisited[currentLocation.i + 1][currentLocation.j])
            neighbours.add(new CurrentLocation(currentLocation.i + 1, currentLocation.j,
                    l.getLocations()[currentLocation.i + 1][currentLocation.j]));

        if (currentLocation.i - 1 >= 0 && !isVisited[currentLocation.i - 1][currentLocation.j])
            neighbours.add(new CurrentLocation(currentLocation.i - 1, currentLocation.j,
                    l.getLocations()[currentLocation.i - 1][currentLocation.j]));

        if (neighbours.isEmpty())
            return null;
        else if (neighbours.size() == 1)
            return neighbours.get(0);
        else {
            Random random = new Random();
            return neighbours.get(random.nextInt(neighbours.size()));
        }
    }

    private static void deleteWall(CurrentLocation currentLocation, CurrentLocation neighbour) throws Exception {
        if (currentLocation.j < neighbour.j)
            currentLocation.location.setRightWall(false);
        else if (currentLocation.j > neighbour.j)
            currentLocation.location.setLeftWall(false);
        else if (currentLocation.i > neighbour.i)
            currentLocation.location.setTopWall(false);
        else if (currentLocation.i < neighbour.i)
            currentLocation.location.setBottomWall(false);
        else
            throw new Exception("Can't delete Wall");
    }

    public static boolean genLabyrinth(Labyrinth l) {
        if (l == null)
            return false;

        int countNotVisited = l.getDimension() * l.getDimension();
        boolean[][] isVisited = new boolean[l.getDimension()][l.getDimension()];

        for (int i = 0; i < l.getDimension(); i++) {
            for (int j = 0; j < l.getDimension(); j++) {
                isVisited[i][j] = false;
            }
        }

        ArrayList<CurrentLocation> stack = new ArrayList<>();

        CurrentLocation currentLocation = new CurrentLocation(0, 0, l.getLocations()[0][0]);
        isVisited[currentLocation.i][currentLocation.j] = true;
        countNotVisited--;

        while (countNotVisited > 0) {
            CurrentLocation neighbour = getNotVisitedNeighbour(l, isVisited, currentLocation);
            if (neighbour != null) {
                stack.add(currentLocation);
                try {
                    deleteWall(currentLocation, neighbour);
                }
                catch (Exception e) {
                    return false;
                }

                currentLocation = neighbour;
                isVisited[neighbour.i][neighbour.j] = true;
                countNotVisited--;
            }
            else if (!stack.isEmpty()) {
                currentLocation = stack.get(stack.size() - 1);
                stack.remove(stack.size() - 1);
            }
            else {
                ArrayList<CurrentLocation> notVisited = new ArrayList<>();

                for (int i = 0; i < l.getDimension(); i++) {
                    for (int j = 0; j < l.getDimension(); j++) {
                        if (!isVisited[i][j])
                            notVisited.add(new CurrentLocation(i, j, l.getLocations()[i][j]));
                    }
                }

                if (notVisited.isEmpty())
                    return false;

                Random random = new Random();
                currentLocation = notVisited.get(random.nextInt(notVisited.size()));
                isVisited[currentLocation.i][currentLocation.j] = true;
                countNotVisited--;
            }
        }

        return true;
    }

    public static class CurrentLocation {
        public int i;
        public int j;
        public Location location;

        public CurrentLocation(int i, int j, Location location) {
            this.i = i;
            this.j = j;
            this.location = location;
        }
    }
}

