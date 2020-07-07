package com.springcloudmysql.repository;

import org.springframework.stereotype.Repository;

import com.springcloudmysql.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
