package General;

public class LevelData {
    private int vehicleTruckAmount;
    private int vehicleTruckSpeed;

    private int vehicleRaceCarAmount;
    private int vehicleRaceCarSpeed;

    private int vehicleCoupeAmount;
    private int vehicleCoupeSpeed;

    private int vehicleBulldozerAmount;
    private int vehicleBulldozerSpeed;

    private int vehicleDuneBuggyAmount;
    private int vehicleDuneBuggySpeed;

    public LevelData(int vehicleTruckAmount, int vehicleTruckSpeed, int vehicleRaceCarAmount, int vehicleRaceCarSpeed, int vehicleCoupeAmount, int vehicleCoupeSpeed, int vehicleBulldozerAmount, int vehicleBulldozerSpeed, int vehicleDuneBuggyAmount, int vehicleDuneBuggySpeed) {
        this.vehicleTruckAmount = vehicleTruckAmount;
        this.vehicleTruckSpeed = vehicleTruckSpeed;
        this.vehicleRaceCarAmount = vehicleRaceCarAmount;
        this.vehicleRaceCarSpeed = vehicleRaceCarSpeed;
        this.vehicleCoupeAmount = vehicleCoupeAmount;
        this.vehicleCoupeSpeed = vehicleCoupeSpeed;
        this.vehicleBulldozerAmount = vehicleBulldozerAmount;
        this.vehicleBulldozerSpeed = vehicleBulldozerSpeed;
        this.vehicleDuneBuggyAmount = vehicleDuneBuggyAmount;
        this.vehicleDuneBuggySpeed = vehicleDuneBuggySpeed;
    }

    public int getVehicleTruckAmount() {
        return vehicleTruckAmount;
    }

    public int getVehicleTruckSpeed() {
        return vehicleTruckSpeed;
    }

    public int getVehicleRaceCarAmount() {
        return vehicleRaceCarAmount;
    }

    public int getVehicleRaceCarSpeed() {
        return vehicleRaceCarSpeed;
    }

    public int getVehicleCoupeAmount() {
        return vehicleCoupeAmount;
    }

    public int getVehicleCoupeSpeed() {
        return vehicleCoupeSpeed;
    }

    public int getVehicleBulldozerAmount() {
        return vehicleBulldozerAmount;
    }

    public int getVehicleBulldozerSpeed() {
        return vehicleBulldozerSpeed;
    }

    public int getVehicleDuneBuggyAmount() {
        return vehicleDuneBuggyAmount;
    }

    public int getVehicleDuneBuggySpeed() {
        return vehicleDuneBuggySpeed;
    }
}
