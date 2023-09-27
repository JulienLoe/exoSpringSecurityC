package com.example.exospringc.repositories;

import com.example.exospringc.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
    List<Contact> findAllByFirstNameStartingWith (String value);
}
