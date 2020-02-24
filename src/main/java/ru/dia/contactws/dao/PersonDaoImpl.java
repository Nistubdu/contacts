package ru.dia.contactws.dao;

import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.dia.contactws.domain.Person;
import ru.dia.contactws.mapper.PersonRowMapper;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonDaoImpl implements PersonDao {

    public PersonDaoImpl(NamedParameterJdbcTemplate personTemplate) {
        this.personTemplate = personTemplate;
    }

    NamedParameterJdbcTemplate personTemplate;

    @Override
    public List<Person> findAll() {
        return personTemplate.query("select * from person", new PersonRowMapper());
    }

    @Override
    public void create(Person person) {
        final String sql = "insert into person (" +
                "id, first_name , last_name, middle_name) " +
                "values(" +
                ":id,:first_name,:last_name,:middle_name)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", person.getId())
                .addValue("first_name", person.getFirstName())
                .addValue("last_name", person.getLastName())
                .addValue("middle_name", person.getMiddleName());
        personTemplate.update(sql,param, holder);
    }

    @Override
    public void update(Person person) {
        final String sql = "update person set first_name=:first_name, last_name=:last_name, middle_name=:middle_name where id=:id";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", person.getId())
                .addValue("first_name", person.getFirstName())
                .addValue("last_name", person.getLastName())
                .addValue("middle_name", person.getMiddleName());
        personTemplate.update(sql,param, holder);
    }

    @Override
    public void executeUpdate(Person person) {
        final String sql = "update person set first_name=:first_name, last_name=:last_name, middle_name=:middle_name where id=:id";
        Map<String,Object> map=new HashMap<>();
        map.put("id", person.getId());
        map.put("first_name", person.getFirstName());
        map.put("last_name", person.getLastName());
        map.put("middle_name", person.getMiddleName());


        personTemplate.execute(sql,map, (PreparedStatementCallback<Object>) PreparedStatement::executeUpdate);
    }

    @Override
    public void delete(Person person) {

        final String sql = "delete from person where id=:id";

        Map<String,Object> map = new HashMap<>();
        map.put("id", person.getId());

        personTemplate.execute(sql,map, (PreparedStatementCallback<Object>) PreparedStatement::executeUpdate);
    }
}