package com.repository;

import com.enums.TicketPriority;
import com.enums.TicketStatus;
import com.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class TicketRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public void insert(Ticket ticket) {
        String sql="insert into tickets(subject, details, t_priority,t_status,createdAt) " +
                " values (?,?,?,?,?)";
        jdbcTemplate.update(sql,ticket.getSubject(),ticket.getDetails(),ticket.getTicketPriority().toString(),ticket.getTicketStatus().toString(),ticket.getCreatedAt());
        return;
    }

    public void deleteTicketByID(int id) {
            String sql="delete from tickets where id=?";
            jdbcTemplate.update(sql,id);
            System.out.println("Delete ticket by id: "+id);
    }

    public boolean checkid(int id) {
        String sql="select count(*) from tickets where id=?";
        int size = jdbcTemplate.queryForObject(sql,Integer.class,id);
        return size>0;
    }
    private  RowMapper<Ticket> rowMap(){
        return new RowMapper<Ticket>() {
            @Override
            public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Ticket(
                        rs.getInt("id"),
                        rs.getString("subject"),
                        rs.getString("details"),
                        TicketStatus.valueOf(rs.getString("t_status")),
                        TicketPriority.valueOf(rs.getString("t_priority")),
                        rs.getTimestamp("createdAt").toInstant()
                );
            }
        };
    }

    public List<Ticket> fetchAllTickets() {
        String sql = "select * from tickets";
        return  jdbcTemplate.query(sql, rowMap());
    }
}
