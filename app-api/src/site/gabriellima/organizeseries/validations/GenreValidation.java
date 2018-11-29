package site.gabriellima.organizeseries.validations;

import java.util.ArrayList;
import java.util.List;

import site.gabriellima.organizeseries.entities.Genre;
import site.gabriellima.organizeseries.entities.ResponseError;

public class GenreValidation {

	public static List<ResponseError> verify(Genre genre) {
		List<ResponseError> errors = new ArrayList<>();

		if (genre.getName() == null || genre.getName().isEmpty()) {
			errors.add(new ResponseError("Erro na validação", "O campo name é obrigátorio"));
		}

		return errors;
	}

}
