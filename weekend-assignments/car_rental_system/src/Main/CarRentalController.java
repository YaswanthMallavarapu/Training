package Main;

import Dto.CarDetailsDTO;
import Service.CarRentalService;
import Util.DBConnectionUtil;

import java.sql.SQLException;
import java.util.*;


public class CarRentalController {
    public static void main(String[] args) {

        DBConnectionUtil dbConnectionUtil=DBConnectionUtil.getInstance();
        CarRentalService carRentalService=new CarRentalService();
        Scanner sc=new Scanner(System.in);
        while(true){
        System.out.println("1.Get Car Details with owner.");
        System.out.println("2.Get Car Stats.");
            System.out.println("0.Exit from System.");
        int choice=sc.nextInt();
            if (choice == 0) {
                break;
            }
        switch(choice) {
            case 1:


                try {
                    List<CarDetailsDTO>list = carRentalService.getFullInfo();
                    list.forEach(CarDetailsDTO->{
                        System.out.println("Car id: "+CarDetailsDTO.car().getId());
                        System.out.println("Registration Number: "+CarDetailsDTO.car().getRegistration_number());
                        System.out.println("Chasis Number: "+CarDetailsDTO.car().getChasis_number());
                        System.out.println("Registration State: "+CarDetailsDTO.car().getRegistration_state());

                        System.out.println("Brand : "+CarDetailsDTO.car().getBrand());
                        System.out.println("Model: "+CarDetailsDTO.car().getModel());
                        System.out.println("Variant: "+CarDetailsDTO.car().getVariant());
                        System.out.println("Owner Name: "+CarDetailsDTO.oname());
                        System.out.println("Year of Purchase: "+CarDetailsDTO.year_of_purchase());
                        System.out.println("Milage: "+ CarDetailsDTO.milage());

                        System.out.println("===========================================================");

                    });
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;
            case 2:
                System.out.println("Brand  No_of_Cars");
                try {
                    LinkedHashMap<String,Integer>map=carRentalService.getCarCount();
                    for (Map.Entry<String,Integer>entry:map.entrySet()){
                        System.out.println(entry.getKey()+"  "+entry.getValue());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Invalid case.");

        }
        }
    }

}
