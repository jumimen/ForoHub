package com.example.ForoHub.controller;

import com.example.ForoHub.topic.DataRegisterTopic;
import com.example.ForoHub.topic.ListDataTopic;
import com.example.ForoHub.topic.Topic;
import com.example.ForoHub.topic.TopicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public void registerTopic(@RequestBody @Valid DataRegisterTopic dataRegisterTopic){
        topicRepository.save(new Topic(dataRegisterTopic));
    }
    @GetMapping
    public List<ListDataTopic> toListTopic(){
        return topicRepository.findAll().stream().map(ListDataTopic::new).toList();
    }

}
