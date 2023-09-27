package com.example.exospringc.controllers;

import com.example.exospringc.exceptions.ResourceNotFoundException;
import com.example.exospringc.models.ContactDTO;
import com.example.exospringc.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final ContactService contactService;

//    @GetMapping
//    public String getHomePage() {
//        return "home";
//    }
    @GetMapping
    public String listContacts(Model model) {
        List<ContactDTO> contacts = contactService.getContacts();

        model.addAttribute("contacts", contacts);

        return "home";
    }

    @GetMapping("/{contactId}")
    public String getContactDetails(@PathVariable("contactId") UUID id, Model model) {
        Optional<ContactDTO> foundContact = contactService.getContactById(id);

        if (foundContact.isPresent()) {
            model.addAttribute("contact", foundContact.get());
            model.addAttribute("mode", "details");

            return "private/contactForm";
        }

        throw new ResourceNotFoundException();
    }
//    @GetMapping("/contact")
//    public String getListContactPage() {
//        return "contact";
//    }
}
