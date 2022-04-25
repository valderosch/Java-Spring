package com.lynx.firstproj.controller;
import com.lynx.firstproj.models.Post;
import com.lynx.firstproj.models.VideoPost;
import com.lynx.firstproj.repository.VideoPostRepository;
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
public class VideoBlogController {

    @Autowired
    private VideoPostRepository videoPostRepository1;

    @GetMapping("/videoblog")
    public String videoBlogMain(Model model) {
        Iterable<VideoPost> videoposts = videoPostRepository1.findAll();
        model.addAttribute("videoposts", videoposts);
        return "videoblogMain";
    }

    @GetMapping("/videoblog/add")
    public String videoblogAdd(Model model) {
        return "videoblogAdd";
    }


    @PostMapping("/videoblog/add")
    public String videoblogPostAdd(@RequestParam String title, @RequestParam String announce, @RequestParam String full_text, @RequestParam String video_link, Model model) {
        VideoPost videopost = new VideoPost(title, announce, video_link,full_text);
        videoPostRepository1.save(videopost);
        return "redirect:/videoblog";
    }

    @GetMapping("/videoblog/{id}")
    public String videoblogDetails(@PathVariable(value = "id") long id, Model model)
    {
        if(!videoPostRepository1.existsById(id)){
            return "redirect:/videoblog";
        }

        Optional<VideoPost> videopost = videoPostRepository1.findById(id);
        ArrayList<VideoPost> res = new ArrayList<>();
        videopost.ifPresent(res::add);
        model.addAttribute("videopost", res);
        return "videoblogDetails";
    }

    @GetMapping("/videoblog/{id}/edit")
    public String videopostEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!videoPostRepository1.existsById(id)){
            return "redirect:/videoblog";
        }

        Optional<VideoPost> post = videoPostRepository1.findById(id);
        ArrayList<VideoPost> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("videopost", res);
        return "videoblogEdit";
    }

    @PostMapping("/videoblog/{id}/edit")
    public String videoblogPostUpdate( @PathVariable(value = "id") long id,@RequestParam  String title, @RequestParam  String announce, @RequestParam  String full_text, @RequestParam String video_link, Model model){
        VideoPost videopost = videoPostRepository1.findById(id).orElseThrow();
        videopost.setTitle(title);
        videopost.setAnnounce(announce);
        videopost.setFull_text(full_text);
        videopost.setVideo_link(video_link);
        videoPostRepository1.save(videopost);
        return "redirect:/videoblog";
    }

    @PostMapping("/videoblog/{id}/remove")
    public String videoblogPostDelete( @PathVariable(value = "id") long id, Model model){
        VideoPost videopost = videoPostRepository1.findById(id).orElseThrow();
        videoPostRepository1.delete(videopost);
        return "redirect:/videoblog";
    }



}

