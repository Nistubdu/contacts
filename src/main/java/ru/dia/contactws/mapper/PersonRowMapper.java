package mapper;

import domain.Person;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int arg1) throws SQLException {

        Person person = new Person();

        person.setId( Long.valueOf( rs.getString("id") ));
        person.setFirstName(rs.getString("employeeName"));
        person.setLastName(rs.getString("employeeEmail"));
        person.setMiddleName(rs.getString("employeeEmail"));

        return person;
    }
}
