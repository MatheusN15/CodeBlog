package br.com.spring.codeblog.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spring.codeblog.model.Post;
import br.com.spring.codeblog.service.BlogService;


@Controller
public class CodeBlogController {
	
	@Autowired
    BlogService blogService;

	
	@RequestMapping(value="/posts", method=RequestMethod.GET)
    public ModelAndView getPosts(){
        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = blogService.findAll();
        mv.addObject("posts", posts);
        return mv;
    }
	
	@RequestMapping(value="/posts/{id}", method=RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView("postDetails");
        Post posts = blogService.findById(id);
        mv.addObject("posts", posts);
        return mv;
    }
	
	@RequestMapping(value="/newpost", method=RequestMethod.GET)
    public String getPostForm(){
        return "postForm";
    }
	
	@RequestMapping(value="/newpost", method=RequestMethod.POST)
    public String savePost(@Validated Post post, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors() | post.getTitulo().isEmpty() | post.getAutor().isEmpty() | post.getTexto().isEmpty()){
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
            return "redirect:/newpost";
        }
        post.setData(LocalDate.now());
        blogService.save(post);
        return "redirect:/posts";
    }
	
}
