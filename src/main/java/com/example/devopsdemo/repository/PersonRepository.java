package com.example.devopsdemo.repository;

import com.example.devopsdemo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

// Extending JpaRepository gives us save(), findAll(), findById(), delete() etc.
// for free - no SQL to write, no implementation to write. Spring generates it.
public interface PersonRepository extends JpaRepository<Person, Long> {
}
