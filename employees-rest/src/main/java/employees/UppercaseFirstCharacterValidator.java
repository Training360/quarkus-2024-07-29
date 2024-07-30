package employees;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UppercaseFirstCharacterValidator implements ConstraintValidator<UppercaseFirstCharacter, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null &&
                !value.isEmpty() &&
                Character.isUpperCase(value.charAt(0));
    }

}
