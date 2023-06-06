package pl.ioad.adoto.backend.geoportal.logic.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ValueOfEnumExistValidator implements ConstraintValidator<ValueOfEnumExist, String> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnumExist annotation) {
        acceptedValues = Stream
                .of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(String enumValue, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(enumValue).map(i -> acceptedValues.contains(enumValue)).orElse(true);
    }
}
