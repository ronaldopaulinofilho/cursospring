package com.aprendendospring.cursospring.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface ClienteUpdate {


    @Constraint(validatedBy = ClienteUpdateValidator.class)
    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Nome {
        String message() default "Erro de validação";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
