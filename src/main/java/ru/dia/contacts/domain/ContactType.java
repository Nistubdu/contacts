package ru.dia.contacts.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contact_type")
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_TYPE_ID_GEN")
    @SequenceGenerator(name = "CONTACT_TYPE_ID_GEN", sequenceName = "contact_type_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ContactType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactType)) return false;
        ContactType that = (ContactType) o;
        return getId().equals(that.getId()) &&
                getType().equals(that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType());
    }
}
