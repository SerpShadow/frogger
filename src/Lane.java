import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Lane {

    private PApplet pApplet;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();


    public Lane(PApplet pApplet) {
        this.pApplet = pApplet;
//        PImage vehiclesSprite = pApplet.loadImage("assets/vehicles.png");
//        for (int i = 0; i < amount; i++) {
//            Vehicle vehicle = new Vehicle(pApplet, vehiclesSprite, vehicleType, UTILS.generateVehicleXPosition(width, i, amount));
//            vehicles.add(vehicle);
//        }

    }

    protected void generateVehicles(VEHICLE_TYPE vehicleType, int width, int amount, int speed) {
        PImage vehiclesSprite = pApplet.loadImage("assets/vehicles.png");
        for (int i = 0; i < amount; i++) {
            // todo: speed is in class, not from lane nor level
            Vehicle vehicle = new Vehicle(pApplet, vehiclesSprite, vehicleType, UTILS.generateVehicleXPosition(width, i, amount));
            vehicles.add(vehicle);
        }
    }

    public boolean checkCollision(Hitbox froggerHitbox) {
        for (Vehicle vehicle : vehicles) {
            if (UTILS.isColliding(froggerHitbox, vehicle.getHitbox())) {
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
