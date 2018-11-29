package site.gabriellima.organizeseries.validations;

import java.util.ArrayList;
import java.util.List;

import site.gabriellima.organizeseries.entities.ResponseError;
import site.gabriellima.organizeseries.entities.Series;

public class SeriesValidation {

	public static List<ResponseError> verify(Series series) {
		List<ResponseError> errors = new ArrayList<>();

		if (series.getName() == null || series.getName().isEmpty()) {
			errors.add(new ResponseError("Erro na validação", "O campo name é obrigátorio"));
		}

		return errors;
	}
}
