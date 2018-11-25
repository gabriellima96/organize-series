package site.gabriellima.organizeseries.validations;

import java.util.ArrayList;
import java.util.List;

import site.gabriellima.organizeseries.entities.ResponseError;
import site.gabriellima.organizeseries.entities.User;

public class UserValidation {

	public static List<ResponseError> verify(User user) {
		List<ResponseError> errors = new ArrayList<>();

		if (user.getEmail() == null || user.getEmail().isEmpty()) {
			errors.add(new ResponseError("Erro na validação", "O campo email é obrigátorio"));
		}

		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			errors.add(new ResponseError("Erro na validação", "O campo password é obrigátorio"));
		}

		if (user.getName() == null || user.getName().isEmpty()) {
			errors.add(new ResponseError("Erro na validação", "O campo name é obrigátorio"));
		}
		
		if (user.getEmail() != null && !user.getEmail().isEmpty() && !user.getEmail().contains("@")) {
			errors.add(new ResponseError("Erro na validação", "O email é inválido"));
		}
		
		return errors;
	}
}
