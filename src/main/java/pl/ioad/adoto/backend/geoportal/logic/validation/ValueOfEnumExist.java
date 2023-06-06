package pl.ioad.adoto.backend.geoportal.logic.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueOfEnumExistValidator.class)
public @interface ValueOfEnumExist {
    Class<? extends Enum<?>> enumClass();

    String message() default "Layer does not exist";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
