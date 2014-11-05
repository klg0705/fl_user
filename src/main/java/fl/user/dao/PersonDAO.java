package fl.user.dao;

import fl.base.dao.BaseDAO;
import fl.user.domain.Person;

public interface PersonDAO extends BaseDAO<Person> {

    public Person findPersonByAccount(String account);

}
