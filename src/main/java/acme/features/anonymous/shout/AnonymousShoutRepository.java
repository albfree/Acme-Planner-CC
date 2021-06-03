package acme.features.anonymous.shout;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.grecias.Grecia;
import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {
	
	@Query("select s from Shout s where s.moment >= ?1")
	Collection<Shout> findRecentShouts(Date deadline);
	
	@Query("select g from Grecia g where g.zeus = ?1")
	Grecia findGreciaByZeus(String zeus);
}
