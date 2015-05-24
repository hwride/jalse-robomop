package com.jalse_robomop;

import com.jalse_robomop.entities.RoboMop;
import jalse.JALSE;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Room {
    private int width;
    private int height;
    private boolean[][] cleanedFloorSquares;
    private JALSE jalse;
    private RoboMop roboMop;

    public Room(int width, int height, JALSE jalse, RoboMop roboMop) {
        this.width = width;
        this.height = height;
        cleanedFloorSquares = new boolean[this.width][this.height];
        this.jalse = jalse;
        this.roboMop = roboMop;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Mark the given position in the room as clean.
     *
     * @param position Position that is now clean.
     */
    public void markPositionAsClean(Point2D.Double position) {
        Point roundedRoboMopPosition = getRoundedPosition(position);
        cleanedFloorSquares[roundedRoboMopPosition.x][roundedRoboMopPosition.y] = true;
    }

    /**
     * Checks if the floor is clean. If it is then ends the current simulation.
     */
    public void checkIfFloorIsClean() {
        boolean wholeFloorClean = true;
        for(int x = 0; x < this.width - 1; x++) {
            for(int y = 0; y < this.height - 1; y++) {
                wholeFloorClean = wholeFloorClean && cleanedFloorSquares[x][y];
                if(!wholeFloorClean) break;
            }
        }
        if(wholeFloorClean) {
            System.out.println("The floor is clean.");
            printFloor();
            jalse.stop();
        } else {
            System.out.println("The floor is dirty.");
            printFloor();
        }
    }

    /**
     * Print the current state of the floor. X = dirty, ' ' = clean, R = RoboMop.
     */
    public void printFloor() {
        String floor = "";
        Point roboMopPosition = getRoundedPosition(roboMop.getPosition());
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                String tileContents;
                if(roboMopPosition.x == x && roboMopPosition.y == y) {
                    tileContents = "R";
                } else if(cleanedFloorSquares[x][y]) {
                    tileContents = " ";
                } else {
                    tileContents = "X";
                }

                floor += "[" + tileContents + "]";
            }
            floor += "\n";
        }
        System.out.println(floor);
    }

    /**
     * Get the nearest floor tile to the given position.
     *
     * @param position A non rounded position.
     * @return A rounded position.
     */
    private Point getRoundedPosition(Point2D.Double position) {
        int x = (int)Math.floor(position.x);
        if(x < 0) {
            x = 0;
        }
        int y = (int)Math.floor(position.y);
        if(y < 0) {
            y = 0;
        }
        return new Point(x, y);
    }
}
