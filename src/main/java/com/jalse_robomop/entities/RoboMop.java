package com.jalse_robomop.entities;

import jalse.entities.Entity;
import jalse.entities.annotations.GetAttribute;
import jalse.entities.annotations.SetAttribute;

import java.awt.geom.Point2D;

public interface RoboMop extends Entity {
    @GetAttribute("position")
    Point2D.Double getPosition();

    @SetAttribute("position")
    void setPosition(Point2D.Double p);

    @GetAttribute("angle")
    Double getAngle();

    @SetAttribute("angle")
    void setAngle(Double a);

    default void printRoboMopData() {
        Point2D.Double position = getPosition();
        System.out.println("RoboMop data - x: " + position.x + ", y: " + position.y + ", angle: " + getAngle());
    }
}
