package org.springframework.fu.jafu.web;

import java.util.function.Consumer;

import org.springframework.boot.autoconfigure.mustache.MustacheInitializer;
import org.springframework.boot.autoconfigure.mustache.MustacheProperties;
import org.springframework.boot.autoconfigure.mustache.MustacheReactiveWebInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.fu.jafu.AbstractDsl;

public class MustacheDsl extends AbstractDsl {

	private final Consumer<MustacheDsl> dsl;

	private MustacheProperties properties = new MustacheProperties();

	public MustacheDsl(Consumer<MustacheDsl> dsl) {
		this.dsl = dsl;
	}

	public MustacheDsl prefix(String prefix) {
		this.properties.setPrefix(prefix);
		return this;
	}

	public MustacheDsl suffix(String suffix) {
		this.properties.setSuffix(suffix);
		return this;
	}

	@Override
	public void register(GenericApplicationContext context) {
		this.dsl.accept(this);
		initializers.add(new MustacheInitializer(properties));
		initializers.add(new MustacheReactiveWebInitializer(properties));
	}
}
