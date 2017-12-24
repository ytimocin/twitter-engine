package com.tebeshir.twitter.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Tweet {

	@PrimaryKeyColumn(name = "id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private UUID id;

	@PrimaryKeyColumn(name = "twitterId", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
	private String twitterId;

	@PrimaryKeyColumn(name = "text", ordinal = 4, type = PrimaryKeyType.PARTITIONED)
	private String text;

	@PrimaryKeyColumn(name = "fromUser", ordinal = 5, type = PrimaryKeyType.PARTITIONED)
	private String fromUser;

	// @PrimaryKeyColumn(name = "fromUserId", ordinal = 6, type =
	// PrimaryKeyType.PARTITIONED)
	private long fromUserId;

	private int retweetCount;

	private HashMap<Integer, Date> retweetLogs;

	private Date createdAt;

	private int searchNumber;

	public Tweet(UUID id, String twitterId, String text, String fromUser, long fromUserId, int retweetCount,
			Date createdAt) {
		super();
		this.id = id;
		this.twitterId = twitterId;
		this.text = text;
		this.fromUser = fromUser;
		this.fromUserId = fromUserId;
		this.retweetCount = retweetCount;
		this.retweetLogs = new HashMap<>();
		this.retweetLogs.put(retweetCount, new Date());
		this.createdAt = createdAt;
		this.searchNumber = 1;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public int getRetweetCount() {
		return retweetCount;
	}

	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}

	public HashMap<Integer, Date> getRetweetLogs() {
		return retweetLogs;
	}

	public void setRetweetLogs(HashMap<Integer, Date> retweetLogs) {
		this.retweetLogs = retweetLogs;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getSearchNumber() {
		return searchNumber;
	}

	public void setSearchNumber(int searchNumber) {
		this.searchNumber = searchNumber;
	}

}
