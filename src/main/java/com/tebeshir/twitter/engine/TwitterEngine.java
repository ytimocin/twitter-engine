package com.tebeshir.twitter.engine;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.utils.UUIDs;
import com.tebeshir.twitter.domain.Tag;
import com.tebeshir.twitter.repository.TagRepository;
import com.tebeshir.twitter.repository.TweetRepository;

@Service
public class TwitterEngine {

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private TagRepository tagRepository;

	@Bean
	public Twitter twitter() {
		return new TwitterTemplate("kQE7CLhxvcBCL3UzdZOT6NOvp", "Aypc0IG532cfhCJloUc3TPaLntVMKlM5X3nmJ6cAYx4KwA1HF6");
	}

	@Scheduled(fixedRate = 1000 * 60)
	public void reportCurrentTime() {

		long startTime = System.currentTimeMillis();

		System.out.println("reportCurentTime.start");

		// get tags
		List<Tag> availableTags = StreamSupport.stream(tagRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());

		// go through all tags
		availableTags.forEach(tag -> {

			// get recent tweets for this tag
			List<Tweet> tweetsFromTwitterAPI = twitter().searchOperations().search(tag.getTag()).getTweets();

			// go through all recent tweets for this tag
			tweetsFromTwitterAPI.forEach(tweet -> {

				// if tweet contains the tag
				if (tweet.getText().toLowerCase().contains(tag.getTag().toLowerCase())) {
					Optional<com.tebeshir.twitter.domain.Tweet> temp = this.tweetRepository
							.findByTwitterId(tweet.getId());
					if (temp.isPresent()) {
						com.tebeshir.twitter.domain.Tweet tempTweet = temp.get();
						if (tempTweet.getRetweetCount() != tweet.getRetweetCount()) {
							HashMap<Integer, Date> rtLogs = tempTweet.getRetweetLogs();
							rtLogs.put(tweet.getRetweetCount(), new Date());
							tempTweet.setRetweetLogs(rtLogs);
						}
						tempTweet.setSearchNumber(tempTweet.getSearchNumber() + 1);
						this.tweetRepository.save(tempTweet);
					} else {
						com.tebeshir.twitter.domain.Tweet tweet2save = new com.tebeshir.twitter.domain.Tweet(
								UUIDs.timeBased(), tweet.getId(), tweet.getText(), tweet.getFromUser(),
								tweet.getFromUserId(), tweet.getRetweetCount(), tweet.getCreatedAt());
						this.tweetRepository.save(tweet2save);
					}
				}

			});
		});

		long endTime = System.currentTimeMillis();

		System.out.println("reportCurentTime.end");

		System.out.println("total.time => " + (endTime - startTime));

	}

	// @Scheduled(fixedRate = 1000 * 60)
	public void getInactiveFollowings() {
		// TwitterProfile profile = twitter().userOperations().getUserProfile("yekoko");

		List<TwitterProfile> friends = twitter().friendOperations().getFriends("yekoko");

		friends.forEach(friend -> {
			if (!friend.isProtected()) {
				List<Tweet> friendTweets = twitter().timelineOperations().getUserTimeline(friend.getScreenName());

				int diffInDays = (int) ((new Date().getTime() - friendTweets.get(0).getCreatedAt().getTime())
						/ (1000 * 60 * 60 * 24));

				System.out.println(friend.getScreenName() + " => " + diffInDays);
			}
		});
	}

}
