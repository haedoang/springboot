package io.haedoang.springdatamongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> fetchAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") String id, @RequestBody Student student) {
        studentService.update(student, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        studentService.delete(id);
    }
}
