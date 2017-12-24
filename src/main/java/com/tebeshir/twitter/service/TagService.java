package com.tebeshir.twitter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tebeshir.twitter.domain.Tag;
import com.tebeshir.twitter.repository.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository tagRepository;

	public List<Tag> add(Tag tag) {
		List<Tag> result = new ArrayList<>();
		List<String> tags = Arrays.asList(tag.getTag().trim().split("\\s+"));
		tags.forEach(current -> {
			Optional<Tag> checkTag = this.tagRepository.findByTag(current);
			if (checkTag.isPresent()) {
				Tag tempTag = checkTag.get();
				tempTag.setRequestNumber(tempTag.getRequestNumber() + 1);
				tempTag = this.tagRepository.save(tempTag);
				result.add(tempTag);
			} else {
				Tag tempTag = new Tag(current);
				tempTag = this.tagRepository.save(new Tag(current));
				result.add(tempTag);
			}
		});
		return result;
	}

}
