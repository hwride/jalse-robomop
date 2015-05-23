package com.jalse_robomop;

import com.jalse_robomop.actions.MoveRoboMop;
import com.jalse_robomop.entities.RoboMop;
import com.jalse_robomop.listeners.RoboMopMoved;
import jalse.JALSE;
import jalse.JALSEBuilder;
import jalse.attributes.Attributes;
import java.awt.geom.Point2D;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int MS_BETWEEN_MOVES = 500;
    private static final Point2D.Double ROBO_MOP_START_POS = new Point2D.Double(1, 1);
    private static final double ROBO_MOP_START_ANGLE = 0;

    private static final int ROOM_WIDTH = 5;
    private static final int ROOM_HEIGHT = 5;


    public static void main(String[] args) {
        JALSE jalse = JALSEBuilder.buildSingleThreadedJALSE();

        // Create room.
        Room room = new Room(ROOM_WIDTH, ROOM_HEIGHT, jalse);
        jalse.putInBindings("room", room);

        // Create RoboMop.
        RoboMop roboMop = jalse.newEntity(RoboMop.class);
        roboMop.setPosition(ROBO_MOP_START_POS);
        roboMop.setAngle(ROBO_MOP_START_ANGLE);

        // Mark initial position of RoboMop as clean and print initial state.
        room.markPositionAsClean(roboMop.getPosition());
        roboMop.printRoboMopData();
        room.checkIfFloorIsClean();

        // Listen for when RoboMop moves and update cleaned positions.
        roboMop.addAttributeListener(Attributes.newNamedTypeOf("position", Point2D.Double.class), new RoboMopMoved(room));

        // Move RoboMops.
        jalse.scheduleForActor(new MoveRoboMop(), 0, MS_BETWEEN_MOVES, TimeUnit.MILLISECONDS);
    }
}
