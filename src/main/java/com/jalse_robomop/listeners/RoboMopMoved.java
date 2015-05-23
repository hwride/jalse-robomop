package com.jalse_robomop.listeners;

import com.jalse_robomop.Room;
import jalse.listeners.AttributeEvent;
import jalse.listeners.AttributeListener;

import java.awt.geom.Point2D;

public class RoboMopMoved implements AttributeListener<Point2D.Double> {
    private Room room;

    public RoboMopMoved(Room room) {
        this.room = room;
    }

    @Override
    public void attributeChanged(AttributeEvent<Point2D.Double> event) {
        Point2D.Double position = event.getValue();
        System.out.println("RoboMop moved to - x: " + position.x + ", y: " + position.y);

        room.markPositionAsClean(position);
        room.checkIfFloorIsClean();
    }
}