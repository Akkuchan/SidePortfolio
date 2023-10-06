package com.newproject.projectn.controller.post;

import com.newproject.projectn.Service.post.FaqService;
import com.newproject.projectn.config.UriMaker;
import com.newproject.projectn.dto.post.FaqDtos;
import com.newproject.projectn.entitiy.post.FAQ;
import com.newproject.projectn.mapper.PostMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("faq")
@AllArgsConstructor
public class FaqController {

    FaqService faqService;
    PostMapper  mapper;

    UriMaker uriMaker;

    @PostMapping("/create/v1")
    public ResponseEntity<String> postFaq(@RequestBody FaqDtos.PostDto postDto){
        FAQ newFaq = mapper.postFAQDtoToFAQEntity(postDto);
        FAQ createdFaq  = faqService.createFaq(newFaq, postDto.getUserId());
        String redirectUri = uriMaker.uriMaker("faq", "" + createdFaq.getPostId());
        return new ResponseEntity<String>(redirectUri, HttpStatus.OK);
    }

    @GetMapping("/{faqId}/v1")
    public ResponseEntity<FaqDtos.ResponseDtoForDetailPage> getFaq(@PathVariable Long faqId){
        FAQ foundFAQ = faqService.findFaq(faqId);
        FaqDtos.ResponseDtoForDetailPage detailedResponseDto = mapper.FAQEntityToSingleResponseDto(foundFAQ);
        detailedResponseDto.setUserId(foundFAQ.getPostUser().getUserId());
        detailedResponseDto.setNickName(foundFAQ.getPostUser().getNickName());

        return new ResponseEntity<FaqDtos.ResponseDtoForDetailPage>(detailedResponseDto, HttpStatus.OK);

    }

    @GetMapping("/main/list/v1")
    public ResponseEntity< List<FaqDtos.ListResponseDto>> getMainFaqList(){
        List<FaqDtos.ListResponseDto> foundFAQList = faqService.findFaqList(0, 4);
        return new ResponseEntity< List<FaqDtos.ListResponseDto>>(foundFAQList, HttpStatus.OK);
    }

    @GetMapping("/list/{pageIdx}/v1")
    public ResponseEntity< List<FaqDtos.ListResponseDto>> getFaqList(@PathVariable Integer pageIdx, @RequestParam Integer elementPerPage){
        List<FaqDtos.ListResponseDto> foundFAQList = faqService.findFaqList(pageIdx, elementPerPage);
        return new ResponseEntity< List<FaqDtos.ListResponseDto>>(foundFAQList, HttpStatus.OK);
    }



    @PatchMapping("/edit/v1")
    public ResponseEntity<String> patchFaq(@RequestBody FaqDtos.PatchDto patchDto) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        FAQ editFaq = mapper.patchFAQDtoToFAQEntity(patchDto);
        FAQ editedFaq = faqService.editFaq(editFaq);
        String redirectUri = uriMaker.uriMaker("faq", "/" + editedFaq.getPostId());
        return new ResponseEntity<String>( redirectUri, HttpStatus.OK);

    }
    @DeleteMapping("/delete/{faqid}/v1")
    public void deleteFaq(@PathVariable Long faqId){
        faqService.deleteFaq(faqId);
    }

}
