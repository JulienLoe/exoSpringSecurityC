package com.example.exospringc.mappers;

import java.time.LocalDate;

import com.example.exospringc.entities.Contact;
import com.example.exospringc.models.ContactDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
@Mapper
public interface ContactMapper {


    Contact contactDTOToContact(ContactDTO dto);

//    @Mapping(source = "firstName", target = "blabla")
//    @Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "convertDate")

    ContactDTO contactToContactDto(Contact contact);



//    @Named("convertDateToAge")
//    public static Integer convertDateToAge(LocalDate date) {
//        LocalDate now = LocalDate.now();
//        int age = now.getYear() - date.getYear();
//
//        if (now.minusYears(age).isBefore(date)) {
//            age--;
//        }
//
//        return age;
//    }
//
//    @Named("convertDate")
//    public static String convertDate(LocalDate date) {
//        String string = date.toString();
//
//        return string;
//    }
}
