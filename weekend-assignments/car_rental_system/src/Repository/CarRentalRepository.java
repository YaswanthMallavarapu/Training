package Repository;

import Dao.CarRentalDAO;
import Dto.CarDetailsDTO;
import Model.Car;
import Util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CarRentalRepository implements CarRentalDAO {
    DBConnectionUtil dbConnectionUtil=DBConnectionUtil.getInstance();
    @Override
    public List<CarDetailsDTO> getFullCarInfo() throws SQLException {
        List<CarDetailsDTO>list=new ArrayList<>();
        String sql="select c.id as id ,c.registration_number number,c.chasis_number chasis,c.registration_state as state,c.brand brand,c.model model,c.variant variant,o.name as name,cd.year_of_purchase as year,cd.milage as milage " +
                "from car c,owner o,car_details cd where o.id=c.owner_id and c.car_details_id=cd.id";
        Connection conn=dbConnectionUtil.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                String number=rs.getString("number");
                String chasis=rs.getString("chasis");
                String state=rs.getString("state");
                String brand=rs.getString("brand");
                String model=rs.getString("model");
                String variant=rs.getString("variant");
                Car car=new Car();
                car.setId(id);
                car.setRegistration_number(number);
                car.setChasis_number(chasis);
                car.setRegistration_state(state);
                car.setBrand(brand);
                car.setModel(model);
                car.setVariant(variant);

                String oname=rs.getString("name");
                int year=rs.getInt("year");
                int milage=rs.getInt("milage");
                CarDetailsDTO carDetailsDTO=new CarDetailsDTO(car,oname,year,milage);
                list.add(carDetailsDTO);

            }

        return list;
    }

    @Override
    public LinkedHashMap<String, Integer> getCarCount() throws SQLException {
        LinkedHashMap<String,Integer>map=new LinkedHashMap<>();
        String sql="select brand ,count(id) as count from car group by brand order by count desc";
        Connection conn=dbConnectionUtil.getConnection();
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            String brand=rs.getString("brand");
            int count= rs.getInt("count");
            map.put(brand,count);
        }
        return map;
    }
}
