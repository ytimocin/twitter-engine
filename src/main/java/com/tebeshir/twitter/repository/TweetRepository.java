package com.tebeshir.twitter.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.tebeshir.twitter.domain.Tweet;

@Repository
public interface TweetRepository extends CassandraRepository<Tweet, UUID> {

	@Query(allowFiltering = true)
	Optional<Tweet> findByTwitterId(String twitterId);

}
