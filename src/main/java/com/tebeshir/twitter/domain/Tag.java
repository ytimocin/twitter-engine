package com.tebeshir.twitter.domain;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.utils.UUIDs;

@Table
public class Tag {

	@PrimaryKeyColumn(name = "id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private UUID id;

	@PrimaryKeyColumn(name = "tag", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
	private String tag;

	private int requestNumber;

	private Date dateAdded = new Date();

	public Tag() {

	}

	public Tag(String tag) {
		super();
		this.id = UUIDs.timeBased();
		this.tag = tag;
		this.dateAdded = new Date();
		this.requestNumber = 1;
	}

	public Tag(UUID id, String tag, Date dateAdded) {
		super();
		this.id = id;
		this.tag = tag;
		this.dateAdded = dateAdded;
		this.requestNumber = 1;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(int requestNumber) {
		this.requestNumber = requestNumber;
	}

}
