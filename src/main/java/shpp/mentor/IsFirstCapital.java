package shpp.mentor;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsFirstCapitalEngine.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsFirstCapital {
    String message() default "No errors in field Name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}