package school.hei.haapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import school.hei.haapi.model.Course;
import school.hei.haapi.repository.CourseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
  private final CourseRepository repository;

  public List<Course> getAllCoursesBySomeCriteria(
          String code,
          String name,
          String teacher_first_name,
          String teacher_last_name,
          Integer credits,
          Sort.Direction creditOrder,
          Sort.Direction codeOrder,
          int page,
          int perPage
  ){
    Sort sort = Sort.by(creditOrder , "credits")
            .and(
                    Sort.by(codeOrder , "code")
            );
    Pageable pageable = PageRequest.of(page,perPage,sort);
    return repository.findCoursesByCreditsIsNullOrCreditsEqualsAndAndCodeIsContainingIgnoreCaseAndNameContainingIgnoreCaseAndMain_teacherContainingIgnoreCaseAndCodeIsContainingIgnoreCase(
            name,
            code,
            credits,
            teacher_first_name,
            teacher_last_name,
            pageable
            );
  }
}
