package org.springframework.fu.jafu;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.context.support.GenericApplicationContext;

/**
 * Jafu DSL for beans configuration.
 *
 * @author Sebastien Deleuze
 */
public class BeanDsl extends AbstractDsl {

	private final Consumer<BeanDsl> dsl;

	public BeanDsl(Consumer<BeanDsl> dsl) {
		this.dsl = dsl;
	}

	public final <T> BeanDsl bean(Class<T> beanClass) {
		this.context.registerBean(beanClass);
		return this;
	}

	public final <T> BeanDsl bean(String beanName, Class<T> beanClass) {
		this.context.registerBean(beanName, beanClass);
		return this;
	}

	public final <T> BeanDsl bean(Class<T> beanClass, Supplier<T> supplier) {
		this.context.registerBean(beanClass, supplier);
		return this;
	}

	public final <T> BeanDsl bean(String beanName, Class<T> beanClass, Supplier<T> supplier) {
		this.context.registerBean(beanName, beanClass, supplier);
		return this;
	}

	@Override
	public void register(GenericApplicationContext context) {
		this.dsl.accept(this);
	}
}
