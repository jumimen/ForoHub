package com.example.ForoHub.controller;

import com.example.ForoHub.domain.topic.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<ResponseDataTopic> registerTopic(@RequestBody @Valid DataRegisterTopic dataRegisterTopic,
                                                           UriComponentsBuilder uriComponentsBuilder){
        Topic topic = topicRepository.save(new Topic(dataRegisterTopic));
        ResponseDataTopic responseDataTopic = new ResponseDataTopic(topic.getId(), topic.getTitle(), topic.getMessage(),topic.getDateCreation().toString(),
                topic.getStatus().toString(), topic.getAuthor(), topic.getCourse());
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(responseDataTopic);
    }
    @GetMapping
    public ResponseEntity<Page<ListDataTopic>> toListTopic(@PageableDefault(size = 10,sort = "dateCreation") Pageable pagination ){
        return ResponseEntity.ok(topicRepository.findAll(pagination).map(ListDataTopic::new));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDataTopic> toReturnDataTopic(@PathVariable @Min(value=1, message= "ID must be an integer greater than 0") Long id){
        Optional<Topic> itemDataTopic = topicRepository.findById(id);
        if(itemDataTopic.isPresent()) {
           Topic topic = itemDataTopic.get();
            ResponseDataTopic responseDataTopic =  new ResponseDataTopic(topic.getId(), topic.getTitle(), topic.getMessage(),topic.getDateCreation().toString(),
                    topic.getStatus().toString(), topic.getAuthor(), topic.getCourse());
               return ResponseEntity.ok(responseDataTopic);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found");
        }
    }
   @PutMapping("/{id}")
   @Transactional
    public ResponseEntity toUpdateTopic(@PathVariable @NotNull Long id, @RequestBody UpdateDataTopic updateDataTopic){
        Optional<Topic> itemDataTopic = topicRepository.findById(id);
        if(itemDataTopic.isPresent()){
            Topic topic = itemDataTopic.get();
            topic.dataUpdateTopic(updateDataTopic);
            return ResponseEntity.ok(new UpdateDataTopic(topic.getTitle(), topic.getMessage(),topic.getDateCreation().toString(),
                    topic.getStatus().toString(), topic.getAuthor(), topic.getCourse()));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found");
        }
   }
    @DeleteMapping("/{id}")
    @Transactional
   public ResponseEntity toDeleteTopic(@PathVariable @NotNull Long id){
        Optional<Topic> itemDataTopic = topicRepository.findById(id);
        if(itemDataTopic.isPresent()){
            Topic topic = itemDataTopic.get();
            topicRepository.deleteById(topic.getId());
            return ResponseEntity.noContent().build();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found");
        }
    }

}
