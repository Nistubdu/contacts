package ru.dia.contacts.server;

import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.dia.contacts.domain.ContactType;
import ru.dia.contacts.repository.ContactRepository;
import ru.dia.contacts.repository.ContactTypeRepository;
import ru.dia.contacts.repository.PersonRepository;
import ru.dia.contacts.server.ws.ContactObject;
import ru.dia.contacts.server.ws.ContactTypeObject;
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

        personRepository.findAll().forEach( person -> {

            PersonObject po = new PersonObject();
            po.setId( person.getId() );
            po.setFirstName( person.getFirstName() );
            po.setLastName( person.getLastName() );
            po.setMiddleName( person.getMiddleName() );

            contactRepository.byPersonId( person.getId() ).forEach( contact -> {

                ContactObject co = new ContactObject();
                co.setId( contact.getId() );
                co.setNumber( contact.getNumber() );

                ContactType ct = contactTypeRepository.getOne( contact.getContactTypeId() );
                ContactTypeObject cto = new ContactTypeObject();
                cto.setId( ct.getId() );
                cto.setType( ct.getType() );

                co.setContactTypeObject( cto );
                po.getContactObject().add( co );
            } );

            response.getPersonObject().add( po );
        } );

        return response;
    }
}
