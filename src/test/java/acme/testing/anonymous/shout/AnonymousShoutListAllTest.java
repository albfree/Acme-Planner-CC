package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListAllTest extends AcmePlannerTest{

//	  - Caso positivo de la acción list sobre la entidad Shout por parte del rol Anonymous
//	  - El test espera resultados positivos comprobando que las entidades aparecen en el listado y pueden mostrarse con todos sus datos.
//	  - Los datos utilizados en el fichero .csv son shouts válidos con su entidad asociada también válida
	     
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex, final String moment, final String author, final String text, final String info,
		final String zeus, final String poseidon, final String hades, final String afrodita) {
		
		super.clickOnMenu("Anonymous", "Shouts list");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, zeus);
		super.checkColumnHasValue(recordIndex, 5, poseidon);
		super.checkColumnHasValue(recordIndex, 6, hades);
		super.checkColumnHasValue(recordIndex, 7, afrodita);

	}
}
