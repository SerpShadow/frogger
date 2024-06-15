import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Lane extends Floor {

    private final VEHICLE_TYPE vehicleType;

    private final ArrayList<Vehicle> vehicles = new ArrayList<>();


    public Lane(PApplet pApplet, VEHICLE_TYPE vehicleType, int amount, double speed) {
        this.vehicleType = vehicleType;

        PImage vehiclesSprite = pApplet.loadImage("assets/vehicles.png");
        for (int i = 0; i < amount; i++) {
            Vehicle vehicle = new Vehicle(pApplet, vehiclesSprite, vehicleType, speed, UTILS.generateVehicleXPosition(UTILS.chunksToPixel(vehicleType.getWidth()), i, amount));
            vehicles.add(vehicle);
        }
    }

    public boolean checkCollision(Hitbox frogHitbox) {
        for (Vehicle vehicle : vehicles) {
            if (UTILS.isColliding(frogHitbox, vehicle.getHitboxAbsolute(vehicleType.getPositionY()))) {
                return true;
            }
        }
        return false;
    }


    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, vehicleType.getPositionY());
        for (Vehicle vehicle : vehicles) {
            vehicle.draw(pApplet);
        }
        pApplet.popMatrix();
    }
}
