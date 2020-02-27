package ru.dia.contacts.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.dia.contacts.repository.ContactRepository;
import ru.dia.contacts.repository.ContactTypeRepository;
import ru.dia.contacts.repository.PersonRepository;
import ru.dia.contacts.server.ws.GetAllContactsResponse;
import ru.dia.contacts.server.ws.PersonObject;

@Endpoint
@Service
public class ContactsEP {

    private static final String NAMESPACE_URI = "http://ws.server.contacts.dia.ru/";

    private final ContactRepository contactRepository;
    private final PersonRepository personRepository;
    private final ContactTypeRepository contactTypeRepository;

    public ContactsEP(ContactRepository contactRepository, PersonRepository personRepository,
                      ContactTypeRepository contactTypeRepository) {

        this.contactRepository = contactRepository;
        this.personRepository = personRepository;
        this.contactTypeRepository = contactTypeRepository;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllContactsRequest")
    @ResponsePayload
    public GetAllContactsResponse getAllContactsResponse() {

        GetAllContactsResponse response = new GetAllContactsResponse();

        contactRepository.findAll();
        personRepository.findAll();
        contactTypeRepository.findAll();

        /*
        personRepository.findAll().forEach( person -> {

            PersonObject personObject = new PersonObject();
            personObject.setId( person.getId() );
            personObject.setId( person.getId() );
            personObject.setId( person.getId() );
            personObject.setId( person.getId() );

            response.getPersonObjects().add( personObject );
        } );
*/
        response.getPersonObjects().add( new PersonObject() );
        return response;
    }
}
