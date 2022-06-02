package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageRepository extends AbstractRepository {


	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronageById(int id);


	@Query("select p from Patronage p where p.inventor.id = :id")
	Collection<Patronage> findPatronagesByInventorId(int id);
	
	@Query("select p from Patronage p where p.inventor.id = :id AND p.published=true")
	Collection<Patronage> findPatronagesPublishedByInventorId(int id);
	
	@Query("select c.defaultCurrency from InitialConfiguration c")
	String defaultCurrency();
	

}