package com.newproject.projectn.controller.post;

import com.newproject.projectn.Service.post.FaqService;
import com.newproject.projectn.dto.post.FaqDtos;
import com.newproject.projectn.entitiy.post.FAQ;
import com.newproject.projectn.mapper.PostMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("faq")
@AllArgsConstructor
public class FaqController {

    FaqService faqService;
    PostMapper  mapper;

    @PostMapping
    public ResponseEntity<FAQ> postFaq(@RequestBody FaqDtos.PostDto postDto){
        FAQ newFaq = mapper.postFAQDtoToFAQEntity(postDto);
        FAQ createdFaq  = faqService.createFaq(newFaq);

        return new ResponseEntity<FAQ>( createdFaq, HttpStatus.OK);
    }

    @GetMapping("/{faqId}")
    public ResponseEntity<FAQ> getFaq(@PathVariable Long faqId){
        FAQ foundFAQ = faqService.findFaq(faqId);
        return new ResponseEntity<FAQ>( foundFAQ, HttpStatus.OK);

    }
    @PatchMapping
    public ResponseEntity<FAQ> patchFaq(@RequestBody FaqDtos.PatchDto patchDto) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        FAQ editFaq = mapper.patchFAQDtoToFAQEntity(patchDto);
        FAQ editedFaq = faqService.editFaq(editFaq);
        return new ResponseEntity<FAQ>( editedFaq, HttpStatus.OK);

    }
    @DeleteMapping("/{faqId}")
    public void deleteFaq(@PathVariable Long faqId){
        faqService.deleteFqa(faqId);

    }

}
