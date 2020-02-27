package ru.dia.contacts.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_ID_GEN")
    @SequenceGenerator(name = "CONTACT_ID_GEN", sequenceName = "contact_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "contact_type_id", nullable = false)
    private long contactTypeId;

    @Column(name = "person_id", nullable = false)
    private long personId;

    @Column(name = "number", nullable = false)
    private String number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(long contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", contactTypeId=" + contactTypeId +
                ", personId=" + personId +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return getId() == contact.getId() &&
                getContactTypeId() == contact.getContactTypeId() &&
                getPersonId() == contact.getPersonId() &&
                getNumber().equals(contact.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContactTypeId(), getPersonId(), getNumber());
    }
}
