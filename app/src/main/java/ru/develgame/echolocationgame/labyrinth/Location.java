/**
 * Created by Ilya Zemskov on 19.01.2017.
 */
package ru.develgame.echolocationgame.labyrinth;

public class Location {
    private Location left = null;
    private Location right = null;
    private Location top = null;
    private Location bottom = null;

    private boolean leftWall = true;
    private boolean rightWall = true;
    private boolean topWall = true;
    private boolean bottomWall = true;

    public void setLeftLocation(Location location) {
        if (location != null) {
            this.left = location;
            location.right = this;
        }
    }

    public void setRightLocation(Location location) {
        if (location != null) {
            this.right = location;
            location.left = this;
        }
    }

    public void setTopLocation(Location location) {
        if (location != null) {
            this.top = location;
            location.bottom = this;
        }
    }

    public void setBottomLocation(Location location) {
        if (location != null) {
            this.bottom = location;
            location.top = this;
        }
    }

    public void setLeftWall(boolean value) {
        leftWall = value;
        if (left != null) {
            left.rightWall = value;
        }
    }

    public void setRightWall(boolean value) {
        rightWall = value;
        if (right != null) {
            right.leftWall = value;
        }
    }

    public void setTopWall(boolean value) {
        topWall = value;
        if (top != null) {
            top.bottomWall = value;
        }
    }

    public void setBottomWall(boolean value) {
        bottomWall = value;
        if (bottom != null) {
            bottom.topWall = value;
        }
    }

    public boolean getLeftWall() {return leftWall;}
    public boolean getRightWall() {return rightWall;}
    public boolean getTopWall() {return topWall;}
    public boolean getBottomWall() {return bottomWall;}

    public Location getLeftLocation() {return left;}
    public Location getRightLocation() {return right;}
    public Location getTopLocation() {return top;}
    public Location getBottomLocation() {return bottom;}
}
