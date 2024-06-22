package com.example.ForoHub.controller;

import com.example.ForoHub.topic.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;
import java.util.List;
import java.util.Optional;


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
    public Page<ListDataTopic> toListTopic(@PageableDefault(size = 10,sort = "dateCreation") Pageable pagination ){
        return topicRepository.findAll(pagination).map(ListDataTopic::new);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ListDataTopic> itemDataTopic(@PathVariable @NotNull(message="ID cannot be blank") @Min(value=1, message= "ID must be an integer greater than 0") Long id){
        Optional<Topic> itemDataTopic = topicRepository.findById(id);
        if(itemDataTopic.isPresent()) {
            Topic topic = itemDataTopic.get();
            ListDataTopic specificDataTopic = new ListDataTopic(topic);
            return ResponseEntity.ok(specificDataTopic);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found");
        }
    }
   @PutMapping("/{id}")
   @Transactional
    public void toUpdateTopic(@PathVariable @NotNull Long id, @RequestBody UpdateDataTopic updateDataTopic){
        Optional<Topic> itemDataTopic = topicRepository.findById(id);
        if(itemDataTopic.isPresent()){
            Topic topic = itemDataTopic.get();
            topic.dataUpdateTopic(updateDataTopic);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found");
        }
   }
    @DeleteMapping("/{id}")
    @Transactional
   public void toDeleteTopic(@PathVariable @NotNull Long id){
        Optional<Topic> itemDataTopic = topicRepository.findById(id);
        if(itemDataTopic.isPresent()){
            Topic topic = itemDataTopic.get();
            topicRepository.deleteById(topic.getId());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found");
        }

}

}
