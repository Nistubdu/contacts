package service;

import dao.PersonDao;
import domain.Person;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class PersonServiceImpl implements PersonService {

    @Resource
    PersonDao personDao;

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public void create(Person person) {
        personDao.create(person);
    }

    @Override
    public void update(Person person) {
        personDao.update(person);
    }

    @Override
    public void executeUpdate(Person person) {
        personDao.executeUpdate(person);
    }

    @Override
    public void delete(Person emp) {
        personDao.delete(emp);
    }

}
