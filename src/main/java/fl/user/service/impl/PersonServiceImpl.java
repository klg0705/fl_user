package fl.user.service.impl;

import fl.base.dao.BaseDAO;
import fl.base.service.impl.BaseServiceImpl;
import fl.user.dao.PersonDAO;
import fl.user.domain.Person;
import fl.user.service.PersonService;
import fl.user.util.MD5Util;

public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {

    private PersonDAO personDAO;

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Person findPersonByAccount(String account) {
        return personDAO.findPersonByAccount(account);
    }

    public Person getPerson(String account, String password) {
        Person person = findPersonByAccount(account);
        if (person == null) {
            return null;
        }

        if (!person.getPassword().equals(MD5Util.calc(password))) {
            return null;
        }

        return person;
    }

    public void create(Person person) {
        person.setPassword(MD5Util.calc(person.getPassword()));
        super.create(person);
    }

    public void update(Person baseDomain) {
        // TODO Auto-generated method stub
        super.update(baseDomain);
    }

    protected BaseDAO<Person> getBaseDAO() {
        return personDAO;
    }

}
