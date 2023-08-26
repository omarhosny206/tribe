package com.tribe.service.impl;

import com.tribe.dto.ContentDto;
import com.tribe.dto.PostRequestDto;
import com.tribe.entity.Post;
import com.tribe.entity.Tribe;
import com.tribe.entity.User;
import com.tribe.entity.UserTribe;
import com.tribe.exception.ApiError;
import com.tribe.repository.PostRepository;
import com.tribe.service.PostService;
import com.tribe.service.TribeService;
import com.tribe.service.UserTribeService;
import com.tribe.util.UserTribeId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final TribeService tribeService;
    private final UserTribeService userTribeService;

    public PostServiceImpl(PostRepository postRepository, TribeService tribeService, UserTribeService userTribeService) {
        this.postRepository = postRepository;
        this.tribeService = tribeService;
        this.userTribeService = userTribeService;
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllByUserId(long userId) {
        return postRepository.findAllByUserId(userId);
    }

    @Override
    public Post getById(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> ApiError.notFound("Post not found with id=" + id));
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post save(User authenticatedUser, PostRequestDto postRequestDto) {
        if (postRequestDto.getTribe() == null) {
            postRequestDto.setTribe("own.tribe." + authenticatedUser.getId());
        }
        Tribe tribe = tribeService.getByName(postRequestDto.getTribe());
        UserTribe userTribe = userTribeService.getByIdOrNull(new UserTribeId(
                authenticatedUser.getId(),
                tribe.getId()
        ));
        if (userTribe == null) {
            throw ApiError.badRequest("Cannot save post, you are not a member of this tribe");
        }
        Post post = new Post(postRequestDto.getContent(), authenticatedUser, tribe);
        return save(post);
    }

    @Override
    public Post update(User authenticatedUser, long id, ContentDto contentDto) {
        Post post = getById(id);
        if (post.getUser().getId() != authenticatedUser.getId()) {
            throw ApiError.badRequest("Cannot update the post, you are not the author.");
        }
        post.setContent(contentDto.getContent());
        return save(post);
    }

    @Override
    public Post upvote(long id) {
        Post post = getById(id);
        post.setVotes(post.getVotes() + 1);
        return save(post);
    }

    @Override
    public Post downvote(long id) {
        Post post = getById(id);
        if (post.getVotes() == 0) {
            throw ApiError.badRequest("Cannot downvote the post, current votes equal 0");
        }
        post.setVotes(post.getVotes() - 1);
        return save(post);
    }

    @Override
    public void deleteById(User authenticatedUser, long id) {
        Post post = getById(id);
        if (post.getUser().getId() != authenticatedUser.getId()) {
            throw ApiError.badRequest("Cannot delete the post, you are not the author.");
        }
        postRepository.deleteById(id);
    }
}
