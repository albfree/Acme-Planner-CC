package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{

//	  - Caso positivo de la acción create sobre la entidad Shout por parte del rol Anonymous
//	  - El test espera resultados positivos comprobando que se ha creado la entidad y es posible visualizar sus datos.
//	  - Los datos utilizados en el fichero .csv son shouts válidos
	     
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String author, final String text, final String info,
		final String tiplet, final String deadline, final String budget, final String important) {
		
		super.clickOnMenu("Anonymous", "Create shout");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		
		super.fillInputBoxIn("kale.tiplet", tiplet);
		super.fillInputBoxIn("kale.deadline", deadline);
		super.fillInputBoxIn("kale.budget", budget);
		super.fillInputBoxIn("kale.important", important);
		
		super.clickOnSubmitButton("Shout!");

		super.clickOnMenu("Anonymous", "Shouts list");
		
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, tiplet);
		super.checkColumnHasValue(recordIndex, 5, deadline);
		super.checkColumnHasValue(recordIndex, 6, budget);
		super.checkColumnHasValue(recordIndex, 7, important);
		
	}
	
//        - Caso negativo de la acción create sobre la entidad Shout por parte del rol Anonymous
//        - El test espera resultados negativos comprobando que existen errores al intentar crear una entidad.
//        - Los datos utilizados en el fichero .csv son shouts inválidos
//        - El test comprueba que se violan las siguientes restricciones:
//        - Restricción 1: La entidad no debe contener spam en el autor
//	      - Restricción 2: La entidad no debe contener spam en el texto
//	      - Restricción 3: La entidad no debe contener spam en el autor ni en el texto
//	      - Restricción 4: La entidad no debe contener un autor de un tamaño menor a 5
//	      - Restricción 5: La entidad no debe contener un texto de un tamaño mayor a 100
//        - Restricción 6: La entidad no debe contener un autor vacío
//        - Restricción 7: La entidad no debe contener un texto vacío
//		  - Restricción 8: La entidad no debe contener un link (atributo info) con un formato erróneo
//		  - Restricción 9: La entidad no debe contener un autor con un tamaño mayor a 25
//		  - Restricción 10: El atributo único de la nueva entidad no debe estar repetido
//  	  - Restricción 11: La fecha del patrón de la nueva entidad no es la actual
//  	  - Restricción 12: El momento de la nueva entidad no cumple la restricción
//	  	  - Restricción 13: Cantidad de dinero negativa en la nueva entidad
//	      - Restricción 14: Moneda no admitida en la nueva entidad
//	     
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String author, final String text, final String info,
		final String tiplet, final String deadline, final String budget, final String important) {
		
		super.clickOnMenu("Anonymous", "Create shout");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		
		super.fillInputBoxIn("kale.tiplet", tiplet);
		super.fillInputBoxIn("kale.deadline", deadline);
		super.fillInputBoxIn("kale.budget", budget);
		super.fillInputBoxIn("kale.important", important);
		
		super.clickOnSubmitButton("Shout!");

		super.checkErrorsExist();

		
	}
}
