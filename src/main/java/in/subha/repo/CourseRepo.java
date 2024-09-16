package in.subha.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.subha.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

}
