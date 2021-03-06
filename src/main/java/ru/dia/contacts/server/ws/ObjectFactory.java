//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.28 at 04:11:49 PM MSK 
//


package ru.dia.contacts.server.ws;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.dia.contacts.server.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.dia.contacts.server.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllContactsRequest }
     * 
     */
    public GetAllContactsRequest createGetAllContactsRequest() {
        return new GetAllContactsRequest();
    }

    /**
     * Create an instance of {@link GetAllContactsResponse }
     * 
     */
    public GetAllContactsResponse createGetAllContactsResponse() {
        return new GetAllContactsResponse();
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link PersonObject }
     * 
     */
    public PersonObject createPersonObject() {
        return new PersonObject();
    }

    /**
     * Create an instance of {@link CreateContactRequest }
     * 
     */
    public CreateContactRequest createCreateContactRequest() {
        return new CreateContactRequest();
    }

    /**
     * Create an instance of {@link CreateContactResponse }
     * 
     */
    public CreateContactResponse createCreateContactResponse() {
        return new CreateContactResponse();
    }

    /**
     * Create an instance of {@link ContactObject }
     * 
     */
    public ContactObject createContactObject() {
        return new ContactObject();
    }

    /**
     * Create an instance of {@link UpdateContactRequest }
     * 
     */
    public UpdateContactRequest createUpdateContactRequest() {
        return new UpdateContactRequest();
    }

    /**
     * Create an instance of {@link UpdateContactResponse }
     * 
     */
    public UpdateContactResponse createUpdateContactResponse() {
        return new UpdateContactResponse();
    }

    /**
     * Create an instance of {@link DeleteContactRequest }
     * 
     */
    public DeleteContactRequest createDeleteContactRequest() {
        return new DeleteContactRequest();
    }

    /**
     * Create an instance of {@link DeleteContactResponse }
     * 
     */
    public DeleteContactResponse createDeleteContactResponse() {
        return new DeleteContactResponse();
    }

    /**
     * Create an instance of {@link ContactTypeObject }
     * 
     */
    public ContactTypeObject createContactTypeObject() {
        return new ContactTypeObject();
    }

}
