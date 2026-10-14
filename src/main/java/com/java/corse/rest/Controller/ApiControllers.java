package com.java.corse.rest.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.corse.rest.Models.User;
import com.java.corse.rest.Repo.UserRepo;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    private Connection connectToDB() throws SQLException {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        return DriverManager.getConnection(url, user, password);
    }

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value = "/users")
    public ResponseEntity<?> getUsers() {
        try (Connection conn = connectToDB()) {
            List<User> users = userRepo.findAll();
            return ResponseEntity.ok(users);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database error: " + e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try (Connection conn = connectToDB()) {
            userRepo.save(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User " + user.getFirstName() + " added successfully!");
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email or unique entry already exists.");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody User user) {
        try (Connection conn = connectToDB()) {
            if (!userRepo.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: User not found.");
            }

            User updatedUser = userRepo.findById(id).get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setOccupation(user.getOccupation());
            updatedUser.setAge(user.getAge());
            userRepo.save(updatedUser);

            return ResponseEntity.ok("User with id " + id + " updated successfully!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database error: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        try (Connection conn = connectToDB()) {
            if (!userRepo.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: User not found.");
            }

            userRepo.deleteById(id);
            return ResponseEntity.ok("Deleted user with the id: " + id);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database error: " + e.getMessage());
        }
    }
}