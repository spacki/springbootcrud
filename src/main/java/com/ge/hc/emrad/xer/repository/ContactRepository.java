package com.ge.hc.emrad.xer.repository;

import com.ge.hc.emrad.xer.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer>{
    
}
