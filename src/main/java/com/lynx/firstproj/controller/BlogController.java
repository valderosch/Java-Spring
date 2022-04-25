package com.lynx.firstproj.controller;

import com.lynx.firstproj.models.Post;
import com.lynx.firstproj.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;



    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blogMain";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blogAdd";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam  String title, @RequestParam  String announce, @RequestParam  String full_text, @RequestParam String structure_img, @RequestParam String result_img, @RequestParam String code, Model model){
        Post post = new Post(title, announce, full_text, structure_img, code, result_img );
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model)
    {
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blogDetails";
    }

    @GetMapping("/blog/{id}/edit")
    public String postEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blogEdit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate( @PathVariable(value = "id") long id,@RequestParam  String title, @RequestParam  String announce, @RequestParam  String full_text, @RequestParam  String structure_img, @RequestParam  String code, @RequestParam  String result_img, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnnounce(announce);
        post.setFull_text(full_text);
        post.setStructure_img(structure_img);
        post.setCode(code);
        post.setResult_img(result_img);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete( @PathVariable(value = "id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }

}
