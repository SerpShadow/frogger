import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * Represents a lane in the game where vehicles move horizontally.
 * This class is responsible for generating vehicles, checking for collisions with entities crossing the lane,
 * and drawing the lane and its vehicles on the screen.
 */
public class Lane extends Floor {

    private final VEHICLE_TYPE vehicleType;

    private final ArrayList<Vehicle> vehicles = new ArrayList<>();

    /**
     * Constructs a Lane with specified vehicles, their amount, and speed.
     *
     * @param pApplet     The Processing applet used for drawing.
     * @param vehicleType The type of vehicles to populate this lane with.
     * @param amount      The number of vehicles to generate in this lane.
     * @param speed       The speed at which the vehicles will move across the lane.
     */
    public Lane(PApplet pApplet, VEHICLE_TYPE vehicleType, int amount, double speed) {
        this.vehicleType = vehicleType;

        PImage vehiclesSprite = pApplet.loadImage("assets/vehicles.png");
        // Populates the lane with vehicles based on the specified amount and type.
        for (int i = 0; i < amount; i++) {
            Vehicle vehicle = new Vehicle(pApplet, vehiclesSprite, vehicleType, speed, UTILS.generateVehicleXPosition(UTILS.chunksToPixel(vehicleType.getWidth()), i, amount));
            vehicles.add(vehicle);
        }
    }

    /**
     * Checks for collisions between any vehicle in the lane and a given hitbox.
     *
     * @param frogHitbox The hitbox of the frog to check for collisions with vehicles in the lane.
     * @return {@code true} if there is a collision; {@code false} otherwise.
     */
    @Override
    public boolean checkCollision(Hitbox frogHitbox) {
        for (Vehicle vehicle : vehicles) {
            if (UTILS.isColliding(frogHitbox, vehicle.getHitboxAbsolute(vehicleType.getPositionY()))) {
                return true; // Collision detected
            }
        }
        return false; // No collision
    }

    /**
     * Draws the lane and its vehicles on the screen.
     *
     * @param pApplet The Processing applet used for drawing.
     */
    @Override
    public void draw(PApplet pApplet) {
        // Translates the drawing context to the Y position of the lane to draw vehicles.
        pApplet.pushMatrix();
        pApplet.translate(0, vehicleType.getPositionY());
        for (Vehicle vehicle : vehicles) {
            vehicle.draw(pApplet);
        }
        pApplet.popMatrix();
    }
}
