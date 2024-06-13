import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public abstract class Lane {

    private final PApplet pApplet;
    private final ArrayList<Vehicle> vehicles = new ArrayList<>();


    public Lane(PApplet pApplet) {
        this.pApplet = pApplet;
    }

    protected void generateVehicles(VEHICLE_TYPE vehicleType, int amount, int speed) {
        PImage vehiclesSprite = pApplet.loadImage("assets/vehicles.png");
        for (int i = 0; i < amount; i++) {
            Vehicle vehicle = new Vehicle(pApplet, vehiclesSprite, vehicleType, speed, UTILS.generateVehicleXPosition(vehicleType.getWidth(), i, amount));
            vehicles.add(vehicle);
        }
    }

    public abstract boolean checkCollision(Hitbox hitbox);

    public boolean checkCollision(Hitbox froggerHitbox, int chunksY) {
        for (Vehicle vehicle : vehicles) {
            if (UTILS.isColliding(froggerHitbox, vehicle.getHitboxAbsolute(chunksY))) {
                System.out.println("Collision found");
                return true;
            }
        }
        return false;
    }


    public void draw(PApplet pApplet) {
        for (Vehicle vehicle : vehicles) {
            vehicle.draw(pApplet);
        }
    }
}
