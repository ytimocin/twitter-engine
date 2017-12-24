package com.tebeshir.twitter.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tebeshir.twitter.domain.Tag;
import com.tebeshir.twitter.service.TagService;

@RestController
@RequestMapping(TwitterRestController.TWITTER_BASE_URI)
public class TwitterRestController {

	public static final String TWITTER_BASE_URI = "te/v1";

	private final Twitter twitter;

	private final TagService tagService;

	@Autowired
	TwitterRestController(Twitter twitter, TagService tagService) {
		this.twitter = twitter;
		this.tagService = tagService;
	}

	@RequestMapping(value = "/tweets/{hashtag}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Tweet> getTweets(@PathVariable final String hashtag) {
		return this.twitter.searchOperations().search(hashtag, 20).getTweets();
	}

	@RequestMapping(value = "/tags", method = RequestMethod.POST)
	List<Tag> add(@RequestBody Tag tag) {
		return tagService.add(tag);
	}

	/*
	 * @RequestMapping(value = "/search/{keyword}", produces =
	 * MediaType.APPLICATION_JSON_UTF8_VALUE) public
	 * List<com.tebeshir.twitter.domain.Tweet> checkIfKeywordExists(@PathVariable
	 * final String keyword) { return
	 * this.twitter.searchOperations().search(hashtag, 20).getTweets(); }
	 */

}
