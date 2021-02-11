package br.com.spring.codeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.codeblog.model.Post;


public interface BlogRepository extends JpaRepository<Post, Long>{

}
