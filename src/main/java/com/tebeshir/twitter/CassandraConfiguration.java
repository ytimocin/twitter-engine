package com.tebeshir.twitter;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableAutoConfiguration
class CassandraConfiguration {

	@Configuration
	@EnableCassandraRepositories
	static class CassandraConfig extends AbstractCassandraConfiguration {

		@Override
		public String getKeyspaceName() {
			return "twitterdata";
		}

		@Override
		public SchemaAction getSchemaAction() {
			return SchemaAction.NONE;
		}

		@Override
		protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
			return Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(getKeyspaceName()).ifNotExists()
					.with(KeyspaceOption.DURABLE_WRITES, true).withSimpleReplication());
		}

	}

}