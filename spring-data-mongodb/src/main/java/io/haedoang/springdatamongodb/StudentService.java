package io.haedoang.springdatamongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student save(Student student) {
        return studentRepository.insert(student);
    }

    public void update(Student student, String id) {
        Student savedStudent = studentRepository.findById(id)
                .orElseThrow(IllegalStateException::new);
        savedStudent.update(student);
        studentRepository.save(savedStudent);
    }

    public void delete(String id) {
        studentRepository.deleteById(id);
    }
}
