package ru.dia.contactws.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import ru.dia.contactws.repository.ContactRepository;
import ru.dia.contactws.repository.ContactTypeRepository;
import ru.dia.contactws.repository.PersonRepository;

@Endpoint
public class ContactsEP {

    private ContactRepository countryRepository;
    private PersonRepository personRepository;
    private ContactTypeRepository contactTypeRepository;

    @Autowired
    public ContactsEP(ContactRepository countryRepository, PersonRepository personRepository,
                      ContactTypeRepository contactTypeRepository) {

    }
}
