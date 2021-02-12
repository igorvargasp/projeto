package br.com.ufsm.projeto.compasso.produto.config.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import br.com.ufsm.projeto.compasso.produto.controller.form.*;

public class QuantidadeDisponibilidadeValidator implements ConstraintValidator<QuantidadeDisponibilidade, ProdutoForm> {
    @Override
    public void initialize(QuantidadeDisponibilidade annotation) {
    }

    @Override
    public boolean isValid(ProdutoForm form, ConstraintValidatorContext constraintContext) {
    	if (form.getQuantidade() <= 0 && form.getDisponivel()) {
    		return false;
        }
        return true;
    }
}
