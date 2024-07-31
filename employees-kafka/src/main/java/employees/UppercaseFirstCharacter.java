package employees;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // JavaSE
@Target({ElementType.FIELD, ElementType.METHOD}) // JavaSE
@Constraint(validatedBy = {UppercaseFirstCharacterValidator.class}) // jakarta.validation
public @interface UppercaseFirstCharacter {

    String message() default "Invalid value, first character is not uppercase";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
