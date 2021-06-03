package acme.features.anonymous.shout;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.grecias.Grecia;
import acme.entities.shouts.Shout;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;
import acme.utils.SpamChecker;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	@Autowired
	protected AnonymousShoutRepository repository;
	
	@Autowired
	private SpamChecker spamChecker;
	
	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "author", "text", "info", "grecia.zeus", "grecia.poseidon", "grecia.hades", "grecia.afrodita");		
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;
		
		Shout result;
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new Shout();
		result.setAuthor("");
		result.setText("");
		result.setMoment(moment);
		result.setInfo("");
		
		final Grecia grecia = new Grecia();
		result.setGrecia(grecia);
		
		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;		
		
		if (!errors.hasErrors("text")) {
			errors.state(request, !this.spamChecker.isSpamText(entity.getText()), "text", "anonymous.shout.error.spam");
		}
		
		if (!errors.hasErrors("author")) {
			errors.state(request, !this.spamChecker.isSpamText(entity.getAuthor()), "author", "anonymous.shout.error.spam");
		}
		
		if (!errors.hasErrors("grecia.zeus")) {
			final Grecia grecia = this.repository.findGreciaByZeus(entity.getGrecia().getZeus());
			
			errors.state(request, grecia == null, "grecia.zeus", "anonymous.shout.error.zeus");
		}
		
		if (!errors.hasErrors("grecia.hades")) {
			final String currency = entity.getGrecia().getHades().getCurrency();
			
			errors.state(request, currency.equals("€") || currency.equals("$"), "grecia.hades", "anonymous.shout.error.hades");
		}
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {

		assert request != null;
		assert entity != null;

		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		
		this.repository.save(entity.getGrecia());
		
		this.repository.save(entity);
	}

	
}
