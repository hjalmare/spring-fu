package org.springframework.fu.jafu.r2dbc;

import java.util.function.Consumer;

import org.springframework.boot.autoconfigure.data.r2dbc.DatabaseClientInitializer;
import org.springframework.boot.autoconfigure.data.r2dbc.R2dbcProperties;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.fu.jafu.AbstractDsl;

/**
 * Jafu DSL for R2DBC configuration.
 * @author Sebastien Deleuze
 */
public class R2dbcDsl extends AbstractDsl {

	private final Consumer<R2dbcDsl> dsl;

	private final R2dbcProperties properties = new R2dbcProperties();

	public R2dbcDsl(Consumer<R2dbcDsl> dsl) {
		this.dsl = dsl;
	}

	public R2dbcDsl host(String host) {
		properties.setHost(host);
		return this;
	}

	public R2dbcDsl port(Integer port) {
		properties.setPort(port);
		return this;
	}

	public R2dbcDsl database(String database) {
		properties.setDatabase(database);
		return this;
	}

	public R2dbcDsl username(String username) {
		properties.setUsername(username);
		return this;
	}

	public R2dbcDsl password(String password) {
		password(password);
		return this;
	}

	@Override
	public void register(GenericApplicationContext context) {
		this.dsl.accept(this);
		if (properties.getHost() == null) {
			properties.setHost("localhost");
		}
		if (properties.getPort() == null) {
			properties.setPort(5432);
		}
		if (properties.getDatabase() == null) {
			properties.setDatabase("postgres");
		}
		if (properties.getUsername() == null) {
			properties.setUsername("postgres");
		}
		if (properties.getPassword() == null) {
			properties.setPassword("");
		}
		this.initializers.add(new DatabaseClientInitializer(properties));
	}
}
