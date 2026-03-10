package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    // Constructor Injection (DI)
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Student update(Integer id, Student studentDetails) {
        // Tìm sinh viên cũ trong DB
        Student existingStudent = repository.findById(id).orElse(null);
        if (existingStudent != null) {
            // Cập nhật thông tin mới
            existingStudent.setName(studentDetails.getName());
            existingStudent.setEmail(studentDetails.getEmail());
            existingStudent.setAge(studentDetails.getAge());
            // Lưu lại vào DB
            return repository.save(existingStudent);
        }
        return null; // Hoặc ném ra Exception tùy ý bạn
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}