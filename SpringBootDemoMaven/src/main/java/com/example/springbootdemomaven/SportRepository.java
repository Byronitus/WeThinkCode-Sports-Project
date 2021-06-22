package com.example.springbootdemomaven;


import com.example.springbootdemomaven.Classes.Sport;
import org.springframework.data.repository.CrudRepository;

public interface SportRepository extends CrudRepository<Sport, Long> {
}
