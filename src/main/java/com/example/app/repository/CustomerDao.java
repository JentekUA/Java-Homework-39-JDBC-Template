package com.example.app.repository;

import com.example.app.entity.CustomerEntity;
import com.example.app.entity.rowmapper.CustomerRowMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerDao {
    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS customers (
                id SERIAL PRIMARY KEY,
                full_name VARCHAR(255) NOT NULL,
                email VARCHAR(255) UNIQUE NOT NULL,
                social_security_number VARCHAR(50) UNIQUE NOT NULL
            );
        """;
        jdbcTemplate.execute(sql);
    }

    public void addCustomer(CustomerEntity customer) {
        String sql = "INSERT INTO customers (full_name, email, social_security_number) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, customer.getFullName(), customer.getEmail(), customer.getSocialSecurityNumber());
    }

    public Optional<CustomerEntity> findById(Long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        return jdbcTemplate.query(sql, new CustomerRowMapper(), id).stream().findFirst();
    }

    public void updateCustomer(CustomerEntity customer) {
        String sql = "UPDATE customers SET full_name = ?, email = ?, social_security_number = ? WHERE id = ?";
        jdbcTemplate.update(sql, customer.getFullName(), customer.getEmail(), customer.getSocialSecurityNumber(), customer.getId());
    }

    public void deleteCustomer(Long id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<CustomerEntity> getAllCustomers() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }
}
