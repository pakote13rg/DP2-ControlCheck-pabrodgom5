package acme.features.authenticated.announcement;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticationAnnouncementListRecentService implements AbstractListService<Authenticated, Announcement>{

	// Internal state -------------------------------------------------
	@Autowired
	protected AuthenticatedAnnouncementRepository repository;
	
	// AbstractListService<Authenticated, Announcement> interface -----
	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Announcement> findMany(final Request<Announcement> request) {
		assert request != null;
		
		final Collection<Announcement> result;
		
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		final Date deadline = calendar.getTime();
		result = this.repository.findRecentAnnouncement(deadline);
		return result;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "creationMoment", "body");
		
	}

}
