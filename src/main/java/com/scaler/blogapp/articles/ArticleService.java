package com.scaler.blogapp.articles;

import com.scaler.blogapp.articles.dtos.CreateArticleRequest;
import com.scaler.blogapp.articles.dtos.UpdateArticleRequest;
import com.scaler.blogapp.users.UserRepository;
import com.scaler.blogapp.users.UserService;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private ArticlesRepository articlesRepository;
    private UserRepository usersRepository;

    public ArticleService(ArticlesRepository articlesRepository, UserRepository usersRepository) {
        this.articlesRepository = articlesRepository;
        this.usersRepository = usersRepository;
    }

    public Iterable<ArticleEntity> getAllArticles() {
        return articlesRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug) {
        var article = articlesRepository.findBySlug(slug);
        if (article == null) {
            throw new ArticleNotFoundException(slug);
        }
        return article;
    }

    public ArticleEntity createArticle(CreateArticleRequest a, Long authorId) {
        var author = usersRepository.findById(authorId).orElseThrow(() -> new UserService.UserNotFoundException(authorId));
        return articlesRepository.save(ArticleEntity.builder()
                .title(a.getTitle())
                .body(a.getBody())
                .author(author)
                // TODO : Create a proper slugification function
                .slug(a.getTitle().toLowerCase().replaceAll("\\s+", "-"))
                .subtitle(a.getSubtitle())
                .build());
    }

    public ArticleEntity updateArticle(Long articleId, UpdateArticleRequest u) {
        var article = articlesRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));

        if (u.getTitle() != null) {
            article.setTitle(u.getTitle());
        }
        if (u.getBody() != null) {
            article.setBody(u.getBody());
        }
        if (u.getSubtitle() != null) {
            article.setSubtitle(u.getSubtitle());
        }
        return articlesRepository.save(article);
    }

    static class ArticleNotFoundException extends IllegalArgumentException {
        public ArticleNotFoundException(String slug) {
            super("Article " + slug + " not found");
        }

        public ArticleNotFoundException(Long articleId) {
            super("Article Id : " + articleId + " ,not found...");
        }
    }

}
