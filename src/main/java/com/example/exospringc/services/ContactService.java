package com.example.exospringc.services;

import com.example.exospringc.entities.Contact;
import com.example.exospringc.exceptions.ResourceNotFoundException;
import com.example.exospringc.mappers.ContactMapper;
import com.example.exospringc.models.ContactDTO;
import com.example.exospringc.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Primary
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public List<ContactDTO> getContacts() {
        return contactRepository
                .findAll()
                .stream()
                // .map(p -> personMapper.personToPersonDto((p)))
                .map(contactMapper::contactToContactDto)
                .toList();
    }

    public ContactDTO addContact(ContactDTO dto) {
//        Person person = personMapper.personDtoToPerson(dto);
//        Person savedPerson = personRepository.save(person);
//        PersonDTO savedDto = personMapper.personToPersonDto(savedPerson);
//        return savedDto;

        return contactMapper.contactToContactDto(contactRepository
                .save(contactMapper.contactDTOToContact(dto)));
    }

    public Boolean deleteContactById(UUID id) {
        Contact foundContact = contactRepository.findById(id).stream().findFirst().orElse(null);

        if (foundContact != null){

            contactRepository.delete(foundContact);


            return true;
        }

        return false;
    }

    public Optional<ContactDTO> getContactById(UUID id) {
        Optional<Contact> foundContact = contactRepository.findById(id);

        if (foundContact.isPresent()){
            ContactDTO contactDTO = contactMapper.contactToContactDto(foundContact.get());

            return Optional.ofNullable(contactDTO);
        }

        throw new ResourceNotFoundException();
    }

    public ContactDTO editContact(UUID id, ContactDTO newDatas){
        AtomicReference<ContactDTO> atomicReference = new AtomicReference<>();

        Optional<Contact> foundContact = contactRepository.findById(id);


        foundContact.ifPresentOrElse(found -> {
            if(newDatas.getLastName() != null) {
                found.setLastName(newDatas.getLastName());
            }

            if(newDatas.getFirstName() != null) {
                found.setFirstName(newDatas.getFirstName());
            }

            if (newDatas.getNbPhone() != null) {
                found.setNbPhone(newDatas.getNbPhone());
            }
            atomicReference.set(contactMapper.contactToContactDto(found));
            contactRepository.save(contactMapper.contactDTOToContact(atomicReference.get()));
        }, () -> {
            atomicReference.set(null);

        });


        return atomicReference.get();
    }
}
