package shpp.mentor;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class DTOvalidation {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final jakarta.validation.Validator validator = factory.getValidator();

    private DTOvalidation() {
    }

    public static Set<ConstraintViolation<DTO>> getValidate(DTO DTOunit) {
        return validator.validate(DTOunit);
    }
}