package com.tebeshir.twitter.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.tebeshir.twitter.domain.Tag;

@Repository
public interface TagRepository extends CassandraRepository<Tag, UUID> {

	Optional<Tag> findByTag(String tag);

}
