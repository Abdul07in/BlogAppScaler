package com.scaler.blogapp.comments;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentsRepository commentRepository;

    public CommentService(CommentsRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
