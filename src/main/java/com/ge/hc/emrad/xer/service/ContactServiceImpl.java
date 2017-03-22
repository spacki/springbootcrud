package com.ge.hc.emrad.xer.service;

import com.ge.hc.emrad.xer.repository.ContactRepository;
import com.ge.hc.emrad.xer.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;
    
    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    
    @Override
    public Iterable<Contact> getAllContacts() {
        return this.contactRepository.findAll();
    }
    
    @Override
    public Contact getContactById(Integer id) {
        return this.contactRepository.findOne(id);
    }
    
    @Override
    public Contact saveContact(Contact contact) {
        return this.contactRepository.save(contact);
    }
    
    @Override
    public void deleteContact(Integer id) {
        this.contactRepository.delete(id);
    }
    
}
