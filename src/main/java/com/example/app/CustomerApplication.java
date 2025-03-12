package com.example.app;

import com.example.app.entity.CustomerEntity;
import com.example.app.repository.CustomerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class CustomerApplication implements CommandLineRunner {
    private final CustomerDao customerDao;

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Select a command ---");
            System.out.println("1. Add a customer");
            System.out.println("2. Find a customer by ID");
            System.out.println("3. Update a customer");
            System.out.println("4. Delete a customer");
            System.out.println("5. Get all customers");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addCustomer(scanner, customerDao);
                    break;
                case "2":
                    findCustomer(scanner, customerDao);
                    break;
                case "3":
                    updateCustomer(scanner, customerDao);
                    break;
                case "4":
                    deleteCustomer(scanner, customerDao);
                    break;
                case "5":
                    getAllCustomers(customerDao);
                    break;
                case "0":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Unknown command!");
            }
        }
    }

    private void addCustomer(Scanner scanner, CustomerDao customerDao) {
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Social Security Number: ");
        String ssn = scanner.nextLine();

        CustomerEntity customer = CustomerEntity.builder().fullName(fullName).email(email).socialSecurityNumber(ssn).build();
        customerDao.addCustomer(customer);
        System.out.println("Customer added!");
    }

    private void findCustomer(Scanner scanner, CustomerDao customerDao) {
        System.out.print("Enter customer ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        customerDao.findById(id).ifPresentOrElse(
                customer -> System.out.println("Found: " + customer),
                () -> System.out.println("Customer not found")
        );
    }

    private void updateCustomer(Scanner scanner, CustomerDao customerDao) {
        System.out.print("Enter customer ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.print("New name: ");
        String fullName = scanner.nextLine();
        System.out.print("New email: ");
        String email = scanner.nextLine();
        System.out.print("New SSN: ");
        String ssn = scanner.nextLine();

        CustomerEntity customer = CustomerEntity.builder().id(id).fullName(fullName).email(email).socialSecurityNumber(ssn).build();
        customerDao.updateCustomer(customer);
        System.out.println("Customer updated!");
    }

    private void deleteCustomer(Scanner scanner, CustomerDao customerDao) {
        System.out.print("Enter customer ID to delete: ");
        Long id = Long.parseLong(scanner.nextLine());
        customerDao.deleteCustomer(id);
        System.out.println("Customer deleted!");
    }

    private void getAllCustomers(CustomerDao customerDao) {
        customerDao.getAllCustomers().forEach(System.out::println);
    }
}