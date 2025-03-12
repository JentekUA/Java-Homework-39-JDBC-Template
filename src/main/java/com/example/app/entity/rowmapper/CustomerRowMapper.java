package com.example.app.entity.rowmapper;

import com.example.app.entity.CustomerEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<CustomerEntity> {
    @Override
    public CustomerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CustomerEntity.builder()
                .id(rs.getLong("id"))
                .fullName(rs.getString("full_name"))
                .email(rs.getString("email"))
                .socialSecurityNumber(rs.getString("social_security_number"))
                .build();
    }
}
