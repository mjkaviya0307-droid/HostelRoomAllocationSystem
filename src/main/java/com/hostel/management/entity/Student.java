package com.hostel.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="department")
    private String department;
    @Column(name="year")
    private int year;
    @Column(name="password")
    private String password;
    @Column(nullable = true)
    private String gender; // "Male" or "Female"


    // NEW: Relation with Room
    @ManyToOne
    @JoinColumn(name = "room_id") // foreign key in student table
    private Room room;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
