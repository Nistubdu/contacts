package ru.dia.contacts.server;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.dia.contacts.domain.Contact;
import ru.dia.contacts.domain.ContactType;
import ru.dia.contacts.repository.ContactRepository;
import ru.dia.contacts.repository.ContactTypeRepository;
import ru.dia.contacts.repository.PersonRepository;
import ru.dia.contacts.server.ws.*;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

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
    @Transactional
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

        Result result = new Result();
        result.setCode( 0L );
        result.setDescription( "OK" );

        response.setResult( result );

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createContactRequest")
    @ResponsePayload
    @Transactional
    public CreateContactResponse createContactResponse( @RequestPayload CreateContactRequest createContactRequest )    {

        CreateContactResponse createContactResponse = new CreateContactResponse();
        Result result = new Result();

        if ( !contactTypeRepository.existsById( createContactRequest.getContactTypeId() ) ) {
            result.setCode( -1L );
            result.setDescription( "Error. Contact type not found." );
        }

        if ( !personRepository.existsById( createContactRequest.getPersonId() ) ) {
            result.setCode( -1L );
            result.setDescription( " Error. Person not found." );
        }

        if ( result.getCode() != -1L ) {
            Contact contact = new Contact(createContactRequest.getPersonId(),
                    createContactRequest.getContactTypeId(),
                    createContactRequest.getNumber());

            if ( noMatchingContact( contact ) )    {
                contact = contactRepository.saveAndFlush( contact );

                ContactObject contactObject = new ContactObject();
                contactObject.setId( contact.getId() );
                contactObject.setNumber( contact.getNumber() );
                createContactResponse.setContactObject( contactObject );

                result.setCode( 0L );
                result.setDescription( "OK" );

            }   else {

                result.setCode( 1L );
                result.setDescription( "Error. Such contact already exists." );
            }
        }


        createContactResponse.setResult( result );
        return createContactResponse;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateContactRequest")
    @ResponsePayload
    @Transactional
    public UpdateContactResponse updateContactResponse( @RequestPayload UpdateContactRequest updateContactRequest )    {

        UpdateContactResponse updateContactResponse = new UpdateContactResponse();
        Result result = new Result();

        if ( contactRepository.existsById( updateContactRequest.getContact().getId() ) )  {

            Contact contact = contactRepository.getOne( updateContactRequest.getContact().getId() );
            contact.setNumber( updateContactRequest.getContact().getNumber() );

            if ( noMatchingContact( contact ) )    {
                contactRepository.saveAndFlush( contact );

                result.setCode( 0L );
                result.setDescription( "OK" );

            }   else {

                result.setCode( 1L );
                result.setDescription( "Such contact already exists." );
            }

        }   else {

            result.setCode( -1L );
            result.setDescription( "Contact not found" );
        }

        updateContactResponse.setResult( result );

        return updateContactResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteContactRequest")
    @ResponsePayload
    @Transactional
    public DeleteContactResponse deleteContactResponse( @RequestPayload DeleteContactRequest deleteContactRequest )    {

        DeleteContactResponse deleteContactResponse = new DeleteContactResponse();
        Result result = new Result();

        Contact contact = contactRepository.getOne( deleteContactRequest.getContactId() );

        if ( contactRepository.existsById( deleteContactRequest.getContactId() ) )  {

            contactRepository.deleteById( contact.getId() );

            result.setCode( 0L );
            result.setDescription( "OK" );

        }   else {

            result.setCode( -1L );
            result.setDescription( "Contact not found." );
        }

        deleteContactResponse.setResult( result );

        return deleteContactResponse;
    }

    private boolean noMatchingContact( Contact contact ) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("number", exact().ignoreCase())
                .withMatcher("personId", exact().ignoreCase())
                .withMatcher("contactTypeId", exact().ignoreCase())
                .withIgnorePaths("id");

        System.out.println( contactRepository.exists( Example.of( contact, matcher)) );

        return !contactRepository.exists( Example.of( contact, matcher));
    }
}
