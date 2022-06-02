package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorToolkitDeleteService  implements AbstractDeleteService<Inventor, Toolkit> {

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		boolean result;
		int toolkitId; 
		Toolkit toDelete; 
		Inventor inventor;
		
		toolkitId = request.getModel().getInteger("id"); 
		toDelete = this.repository.findOneToolkitById(toolkitId); 
		inventor = toDelete.getInventor();
		
		result = request.isPrincipal(inventor);
		
		return result;
	}
	
	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "moreInfo", "retailPrice");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "moreInfo", "retailPrice", "draftMode");
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		Toolkit result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);
		
		return result;
	}
	
	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		Collection<Quantity> quantities;

		quantities = this.repository.findManyQuantitiesByToolkitId(entity.getId());
		for (final Quantity quan : quantities) {
			this.repository.delete(quan);
		}
		this.repository.delete(entity);
	}
}
