package com.hostel.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostel.management.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}