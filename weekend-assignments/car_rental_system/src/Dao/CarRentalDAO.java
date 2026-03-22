package Dao;

import Dto.CarDetailsDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CarRentalDAO {
    List<CarDetailsDTO> getFullCarInfo() throws SQLException;

    Map<String, Integer> getCarCount() throws SQLException;
}
