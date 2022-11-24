package shpp.mentor;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsFirstCapitalEngine implements ConstraintValidator<IsFirstCapital, String> {
    String parameter;
    @Override
    public void initialize(IsFirstCapital annotation) {
        //This field is empty
    }
    @Override
    public boolean isValid(String s,
                           ConstraintValidatorContext constraintValidatorContext) {
        return s.substring(0,1).equals(s.substring(0,1).toUpperCase());
    }
}