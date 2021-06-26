package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.kales.Kale;
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
		
		request.unbind(entity, model, "author", "text", "info", "kale.tiplet", "kale.deadline", "kale.budget", "kale.important");		
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
		
		final Kale kale = new Kale();
		result.setKale(kale);
		
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
		
		if (!errors.hasErrors("kale.tiplet")) {
			final Kale kale = this.repository.findKaleByTiplet(entity.getKale().getTiplet());

			if (kale != null) {
				errors.state(request, false, "kale.tiplet", "anonymous.shout.error.tiplet");
			} else {
				final String[] parts = entity.getKale().getTiplet().split("#");

				final String datePartsYear = parts[0].substring(0,2);
				final String datePartsMonth = parts[0].substring(2,4);
				final String datePartsDay = parts[0].substring(4,6);

				final String[] today = LocalDate.now().toString().split("-");
				
				final String todayFormatted = today[0].substring(2, 4);

				errors.state(request, todayFormatted.equals(datePartsYear) && today[1].equals(datePartsMonth) &&
					today[2].equals(datePartsDay), "kale.tiplet", "anonymous.shout.error.tiplet-date");
			}
		}
		
		if (!errors.hasErrors("kale.deadline")) {
			final Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			
			errors.state(request, entity.getKale().getDeadline().before(calendar.getTime()), "kale.deadline", 
				"anonymous.shout.error.deadline");
		}
		
		if (!errors.hasErrors("kale.budget")) {
			final String currency = entity.getKale().getBudget().getCurrency();
			
			errors.state(request, currency.equals("EUR") || currency.equals("USD"), "kale.budget", "anonymous.shout.error.budget");
		}
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {

		assert request != null;
		assert entity != null;

		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		
		this.repository.save(entity.getKale());
		
		this.repository.save(entity);
	}

	
}
