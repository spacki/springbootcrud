package com.ge.hc.emrad.xer.controller;

import com.ge.hc.emrad.xer.service.ContactService;
import com.ge.hc.emrad.xer.domain.Contact;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/contacts")
public class ContactController {
    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }
    
    @RequestMapping(value = { "", "/" })
    public String index(Model model) {
        model.addAttribute("activePage", "contacts");
        model.addAttribute("contacts", this.contactService.getAllContacts());
        return "contacts/index";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addContactForm(Contact contact, Model model) {
        model.addAttribute("activePage", "contacts");
        return "contacts/add";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@Valid Contact contact, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "contacts");
            return "contacts/add";
        }
        
        this.contactService.saveContact(contact);
        return "redirect:/contacts";
    }
    
    @RequestMapping(value = "/view/{id}")
    public String viewContact(@PathVariable Integer id, Model model) {
        model.addAttribute("contact", this.contactService.getContactById(id));
        model.addAttribute("activePage", "contacts");
        return "contacts/view";
    }
    
    @RequestMapping(value = "/edit/{id}")
    public String editContact(@PathVariable Integer id, Model model) {
        model.addAttribute("contact", this.contactService.getContactById(id));
        model.addAttribute("activePage", "contacts");
        return "contacts/edit";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateContact(Contact contact) {
        System.out.println("Contact ID: " + contact.getId());
        this.contactService.saveContact(contact);
        return "redirect:/contacts/view/" + contact.getId();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable Integer id) {
        this.contactService.deleteContact(id);
        return "redirect:/contacts";
    }
    
}
