package fl.user.service;

import org.springframework.transaction.annotation.Transactional;

import fl.base.service.BaseService;
import fl.user.domain.Person;

@Transactional
public interface PersonService extends BaseService<Person> {

	public Person findPersonByAccount(String account);

	public Person getPerson(String account, String password);

}
