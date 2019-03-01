package com.epam.grow.jerseyrest.validation;

import com.epam.grow.jerseyrest.controller.dto.Identifiable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class IdMatchConstraint implements ConstraintValidator<IdMatch, Object[]> {

   public void initialize(IdMatch constraint) {
   }

   public boolean isValid(Object[] obj, ConstraintValidatorContext context) {
      if (obj.length != 2) {
         throw new IllegalArgumentException("Invalid number of arguments: must be 2, but was + " + obj.length);
      }
      if (obj[0] == null || obj[1] == null) {
         return true;
      }
      if (!(obj[0] instanceof Number) || !(obj[1] instanceof Identifiable)) {
         throw new IllegalArgumentException("Invalid arguments types");
      }
      Number id = (Number) obj[0];
      Identifiable identifiable = (Identifiable) obj[1];
      return identifiable.getId() == null || id.equals(identifiable.getId());
   }
}
