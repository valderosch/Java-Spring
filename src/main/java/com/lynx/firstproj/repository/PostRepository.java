package com.lynx.firstproj.repository;

import com.lynx.firstproj.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
