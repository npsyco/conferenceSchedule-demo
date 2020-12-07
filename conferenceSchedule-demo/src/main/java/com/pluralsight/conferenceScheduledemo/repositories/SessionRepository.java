package com.pluralsight.conferenceScheduledemo.repositories;


import com.pluralsight.conferenceScheduledemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
