package br.com.ufsm.projeto.compasso.produto.config.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = QuantidadeDisponibilidadeValidator.class)
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface QuantidadeDisponibilidade {
	String message() default "Quantidade e disponibilidade incompatíveis";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}