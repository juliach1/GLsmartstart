
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class App {

    private List<Vehicle> cars, planes, bicycles, ships;
    private String chosenOption;

    private Logger logger;


    public App(){
        cars = createInitialCars();
        planes = createInitialPlanes();
        bicycles = createInitialBicycles();
        ships = createInitialShips();
        Vehicle vehicle;


        logger = LogManager.getLogger();

        showMenu();

        do{
            Scanner scanner = new Scanner(System.in);
            chosenOption = scanner.nextLine();

            exitIfExitOptionChosen();

            vehicle = getFastestVehicle(chosenOption);

            if(vehicle==null){
                logger.info("Wybierz prawidłową opcję: ");
            }else {

                logger.info( "Pojazd "+vehicle.getClass().getName()+
                        " producenta "+vehicle.getProducer()+
                        " jest najszybszy" +
                        " (maksymalna prędkość to = "+vehicle.getMaxSpeed()+")");

                logger.info("Wybierz kolejną opcję: ");
            }

        }while (1==1);
    }

    public static void main(String[] args) {
        App app = new App();
    }



    private void showMenu() {
        logger.info("---------------------------");
        logger.info("Lista dostępnych opcji:");
        logger.info("CAR, PLANE, SHIP, BICYCLE");
        logger.info("ALL");
        logger.info("EXIT");
        logger.info("---------------------------");

        logger.info("Wybierz opcję: ");
    }

    private void exitIfExitOptionChosen() {
        if(Objects.equals(chosenOption, "exit")){
            logger.info("WYŁĄCZANIE PROGRAMU");
            System.exit(0);
        }
    }

    private Vehicle getFastestVehicleFromGroup(List<Vehicle> vehicles){
        Vehicle fastestVehicle = vehicles.get(0);
        for(Vehicle vehicle : vehicles){
            fastestVehicle = getFasterVehicle(vehicle,fastestVehicle);
        }
        return fastestVehicle;
    }

    private Vehicle getFasterVehicle(Vehicle v1, Vehicle v2){
        if(v1.getMaxSpeed()>v2.getMaxSpeed()){
            return v1;
        }
        return v2;
    }

    private Vehicle getFastestVehicleFromAll(){
        Vehicle fastestVehicle = cars.get(0);

       fastestVehicle = getFasterVehicle(fastestVehicle,getFastestVehicleFromGroup(cars));
       fastestVehicle = getFasterVehicle(fastestVehicle, getFastestVehicleFromGroup(ships));
       fastestVehicle = getFasterVehicle(fastestVehicle, getFastestVehicleFromGroup(planes));
       fastestVehicle = getFasterVehicle(fastestVehicle, getFastestVehicleFromGroup(bicycles));

        return fastestVehicle;
    }

    private Vehicle getFastestVehicle(String chosenOption){

        switch (chosenOption.toLowerCase()) {
            case "car":
                return getFastestVehicleFromGroup(cars);

            case "ship":
                return getFastestVehicleFromGroup(ships);

            case "plane":
                return getFastestVehicleFromGroup(planes);

            case "bicycle":
                return getFastestVehicleFromGroup(bicycles);

            case "all":
                return getFastestVehicleFromAll();

             default:
                 return null;
        }

    }

    private List<Vehicle> createInitialCars(){
        Vehicle car1 = new Car("BMW",220);
        Vehicle car2 = new Car("Toyota",230);
        Vehicle car3 = new Car("Nissan",200);

        return List.of(car1,car2,car3);
    }
    private List<Vehicle> createInitialPlanes(){
        Vehicle plane1 = new Plane("Boeing",950);
        Vehicle plane2 = new Plane("Embraer",880);
        Vehicle plane3 = new Plane("Airbus",900);

        return List.of(plane1,plane2,plane3);
    }

    private List<Vehicle> createInitialBicycles(){
        Vehicle bicycle1 = new Bicycle("Canyon",100);
        Vehicle bicycle2 = new Bicycle("Superior",120);
        Vehicle bicycle3 = new Bicycle("Trek",140);

        return List.of(bicycle1,bicycle2,bicycle3);
    }

    private List<Vehicle> createInitialShips(){
        Vehicle ship1 = new Ship("Gdańska Stocznia 'REMONTOWA'",50);
        Vehicle ship2 = new Ship("SINCOR",45);
        Vehicle ship3 = new Ship("Grupa Przemysłowa Baltic",60);

        return List.of(ship1,ship2,ship3);
    }
}
