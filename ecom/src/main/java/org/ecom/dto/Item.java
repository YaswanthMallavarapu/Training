package org.ecom.dto;

import org.ecom.enums.UserMemberShip;

import java.math.BigDecimal;

public record Item(
        int pid,
        String pname,
        BigDecimal price,
        int qty,
        int uid,
        String uname,
        String memberShip

) {
}
