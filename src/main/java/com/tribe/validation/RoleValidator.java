package com.tribe.validation;

import com.tribe.validation.annotation.ValidRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleValidator implements ConstraintValidator<ValidRole, String> {
    private final List<String> allowedRoles = List.of("seller", "customer");

    @Override
    public boolean isValid(String role, ConstraintValidatorContext context) {
        return role != null && allowedRoles.contains(role);
    }
}
