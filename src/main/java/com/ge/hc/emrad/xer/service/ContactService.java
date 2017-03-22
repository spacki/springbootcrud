package com.ge.hc.emrad.xer.service;

import com.ge.hc.emrad.xer.domain.Contact;

public interface ContactService {
    Iterable<Contact> getAllContacts();
    Contact getContactById(Integer id);
    Contact saveContact(Contact contact);
    void deleteContact(Integer id);
}
