package com.hostel.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostel.management.entity.Student;
import java.util.Optional;
import java.util.List;

// JpaRepository gives CRUD methods for free
public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByRoomId(Long roomid);
	long countByRoomId(Long roomId);
	Optional<Student> findById(Long id); // default JpaRepository method
}
