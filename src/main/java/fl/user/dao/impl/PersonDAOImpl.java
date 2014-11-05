package fl.user.dao.impl;

import java.util.List;

import fl.base.dao.impl.BaseDAOImpl;
import fl.user.dao.PersonDAO;
import fl.user.domain.Person;

public class PersonDAOImpl extends BaseDAOImpl<Person> implements PersonDAO {

    public static final String QUERY_PERSON_BY_ACCOUNT = "select p from Person p where lower(p.account) = lower(:account) and deleted = false";

    @Override
    protected Class<Person> getDomainClass() {
        return Person.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Person findPersonByAccount(String account) {
        List<Person> list = sessionFactory.getCurrentSession().createQuery(QUERY_PERSON_BY_ACCOUNT)
                .setParameter("account", account.trim()).list();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


}
