package com.example.exospringc.controllers;

import com.example.exospringc.models.ContactDTO;
import com.example.exospringc.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/private")
@RequiredArgsConstructor
public class PrivateController {

    private final ContactService contactService;

    @GetMapping
    public String getSecretPage() {
        return "private/home";
    }

    @GetMapping("/add")
    public String getContactForm(Model model) {

        model.addAttribute("contact", ContactDTO.builder().build());
        model.addAttribute("mode", "add");

        return "private/contactForm";
    }

    @PostMapping("/add")
    public String addContact(ContactDTO newContact) {
        contactService.addContact(newContact);

        return "redirect:/public";
    }

    @GetMapping ("/delete/{contactId}")
    public String deleteContactDetails(@PathVariable("contactId") UUID id) {


        contactService.deleteContactById(id);


        return "redirect:/public";

    }

    @GetMapping("/edit/{contactId}")
    public String editContactForm(Model model, @PathVariable("contactId") UUID id) {

        Optional<ContactDTO> foundContact = contactService.getContactById(id);

        model.addAttribute("contact", foundContact.get());
        model.addAttribute("mode", "add");

        return "private/contactForm";
    }

    @PostMapping  ("/edit/{contactId}")
    public String patchContact(ContactDTO newContact, @PathVariable("contactId") UUID id ) {
        contactService.editContact(id, newContact);

        return "redirect:/public";
    }
}
