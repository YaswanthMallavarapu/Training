package org.ecom.repository;

import org.ecom.dto.Item;
import org.ecom.model.CartItem;
import org.ecom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class CartRepository {


    private final JdbcTemplate jdbcTemplate;
    public CartRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public int addUser(User user) {
        String sql="insert into user(name,membership) values(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, user.getName());
            ps.setString(2,user.getMembership().toString());

            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public void addItem(CartItem cartItem, int userId) {
        String sql="insert into cartItem(name,price,quantity,user_id) values(?,?,?,?)";
        jdbcTemplate.update(sql,cartItem.getName(),cartItem.getPrice(),cartItem.getQuantity(),userId);
    }

    public List<Item> getAllItems() {
        String sql =
                "SELECT c.id AS pid, " +
                        "c.name AS pname, " +
                        "c.price AS price, " +
                        "c.quantity AS qty, " +
                        "u.id AS uid, " +
                        "u.name AS uname, " +
                        "u.membership AS membership " +
                        "FROM `user` u " +
                        "JOIN cartItem c ON u.id = c.user_id";
        return jdbcTemplate.query(sql,rowMap());
    }


    public List<Item> getItemsByName(String username) {
        String sql="SELECT c.id AS pid, " +
                "c.name AS pname, " +
                "c.price AS price, " +
                "c.quantity AS qty, " +
                "u.id AS uid, " +
                "u.name AS uname, " +
                "u.membership AS membership " +
                "FROM `user` u " +
                "JOIN cartItem c ON u.id = c.user_id " +
                "where u.name=?";
        return jdbcTemplate.query(sql,rowMap(),username);
    }

    private RowMapper<Item> rowMap(){
        return new RowMapper<Item>() {
            @Nullable
            @Override
            public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Item(
                        rs.getInt("pid"),
                        rs.getString("pname"),
                        rs.getBigDecimal("price"),
                        rs.getInt("qty"),
                        rs.getInt("uid"),
                        rs.getString("uname"),
                        rs.getString("membership")
                );
            }
        };
    }

    @Transactional
    public void deleteItem(int id) {
        String sql_userid="select user_id from cartItem where id= ? ";
        int userid=jdbcTemplate.queryForObject(sql_userid,Integer.class,id);
        String sql="delete from cartItem where id= ? ";
        jdbcTemplate.update(sql,id);
        String delete_user_sql="delete from user where id = ?";
        jdbcTemplate.update(delete_user_sql,userid);
    }

    public boolean checkId(int id) {
        String sql="select count(*) from cartItem where id=?";
        int size=jdbcTemplate.queryForObject(sql,Integer.class,id);
        return size>0;
    }

}
