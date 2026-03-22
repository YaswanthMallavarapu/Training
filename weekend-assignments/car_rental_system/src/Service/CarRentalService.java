package Service;

import Dto.CarDetailsDTO;
import Repository.CarRentalRepository;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CarRentalService {
    CarRentalRepository carRentalRepository=new CarRentalRepository();
    public  List<CarDetailsDTO> getFullInfo() throws SQLException {
        return carRentalRepository.getFullCarInfo();
    }

    public LinkedHashMap<String, Integer> getCarCount() throws SQLException {
        LinkedHashMap<String, Integer>map= carRentalRepository.getCarCount();
        return map;
    }
}
