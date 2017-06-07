package guru.springframework.commands.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import guru.springframework.commands.CustomerForm;

@Component
public class CustomerFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return CustomerForm.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CustomerForm customerForm = (CustomerForm) target;
		
		if (customerForm.getPassword().trim().equals("")) {
			errors.rejectValue("password", "PasswordIsEmpty.customerForm.password", "Password Shouldn't Be Empty");
			return;
		}
		
		if ( ! customerForm.getPassword().equals(customerForm.getPasswordConf())) {
			errors.rejectValue("password", "PasswordDontMatch.customerForm.password", "Password Don't Match");
			errors.rejectValue("passwordConf", "PasswordDontMatch.customerForm.passwordConf", "Password Don't Match");
		}
	}

}
