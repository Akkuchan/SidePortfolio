package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.post.FaqDtos;
import com.newproject.projectn.dto.post.LocalInfoDtos;
import com.newproject.projectn.dto.post.ParentingEventDtos;
import com.newproject.projectn.dto.post.ParentingProductDtos;
import com.newproject.projectn.dto.post.PostDtos;
import com.newproject.projectn.dto.post.ThriftShopProductDtos;
import com.newproject.projectn.entitiy.Comment;
import com.newproject.projectn.entitiy.post.FAQ;
import com.newproject.projectn.entitiy.post.LocalInfo;
import com.newproject.projectn.entitiy.post.ParentingEvent;
import com.newproject.projectn.entitiy.post.ParentingProduct;
import com.newproject.projectn.entitiy.post.Post;
import com.newproject.projectn.entitiy.post.ThriftShopProduct;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-11T19:01:36+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post postPostDtoToPostEntity(PostDtos.PostPostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        Post post = new Post();

        post.setTitle( postDto.getTitle() );
        post.setBody( postDto.getBody() );
        List<String> list = postDto.getImgList();
        if ( list != null ) {
            post.setImgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = postDto.getTags();
        if ( list1 != null ) {
            post.setTags( new ArrayList<String>( list1 ) );
        }

        return post;
    }

    @Override
    public Post patchPostDtoToPostEntity(PostDtos.PatchDto patchPostDto) {
        if ( patchPostDto == null ) {
            return null;
        }

        Post post = new Post();

        if ( patchPostDto.getPostId() != null ) {
            post.setPostId( patchPostDto.getPostId() );
        }
        post.setTitle( patchPostDto.getTitle() );
        post.setBody( patchPostDto.getBody() );
        List<String> list = patchPostDto.getImgList();
        if ( list != null ) {
            post.setImgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = patchPostDto.getTags();
        if ( list1 != null ) {
            post.setTags( new ArrayList<String>( list1 ) );
        }

        return post;
    }

    @Override
    public PostDtos.ResponseDtoForDetailPage PostEntityToResponseDtoForDetailPage(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDtos.ResponseDtoForDetailPage.ResponseDtoForDetailPageBuilder responseDtoForDetailPage = PostDtos.ResponseDtoForDetailPage.builder();

        responseDtoForDetailPage.postId( post.getPostId() );
        responseDtoForDetailPage.title( post.getTitle() );
        responseDtoForDetailPage.body( post.getBody() );
        if ( post.getRecommend() != null ) {
            responseDtoForDetailPage.recommend( post.getRecommend() );
        }
        if ( post.getView() != null ) {
            responseDtoForDetailPage.view( post.getView() );
        }
        List<String> list = post.getImgList();
        if ( list != null ) {
            responseDtoForDetailPage.imgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = post.getTags();
        if ( list1 != null ) {
            responseDtoForDetailPage.tags( new ArrayList<String>( list1 ) );
        }
        List<Comment> list2 = post.getCommentList();
        if ( list2 != null ) {
            responseDtoForDetailPage.commentList( new ArrayList<Comment>( list2 ) );
        }

        return responseDtoForDetailPage.build();
    }

    @Override
    public FAQ postFAQDtoToFAQEntity(FaqDtos.PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        FAQ fAQ = new FAQ();

        fAQ.setTitle( postDto.getTitle() );
        fAQ.setBody( postDto.getBody() );
        List<String> list = postDto.getImgList();
        if ( list != null ) {
            fAQ.setImgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = postDto.getTags();
        if ( list1 != null ) {
            fAQ.setTags( new ArrayList<String>( list1 ) );
        }
        fAQ.setQuestion( postDto.getQuestion() );
        fAQ.setAnswer( postDto.getAnswer() );

        return fAQ;
    }

    @Override
    public FAQ patchFAQDtoToFAQEntity(FaqDtos.PatchDto patchDto) {
        if ( patchDto == null ) {
            return null;
        }

        FAQ fAQ = new FAQ();

        if ( patchDto.getPostId() != null ) {
            fAQ.setPostId( patchDto.getPostId() );
        }
        fAQ.setTitle( patchDto.getTitle() );
        fAQ.setBody( patchDto.getBody() );
        List<String> list = patchDto.getImgList();
        if ( list != null ) {
            fAQ.setImgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = patchDto.getTags();
        if ( list1 != null ) {
            fAQ.setTags( new ArrayList<String>( list1 ) );
        }
        fAQ.setQuestion( patchDto.getQuestion() );
        fAQ.setAnswer( patchDto.getAnswer() );

        return fAQ;
    }

    @Override
    public FaqDtos.ResponseDtoForDetailPage FAQEntityToSingleResponseDto(FAQ faq) {
        if ( faq == null ) {
            return null;
        }

        FaqDtos.ResponseDtoForDetailPage.ResponseDtoForDetailPageBuilder responseDtoForDetailPage = FaqDtos.ResponseDtoForDetailPage.builder();

        responseDtoForDetailPage.regTime( faq.getRegTime() );
        responseDtoForDetailPage.updateTime( faq.getUpdateTime() );
        responseDtoForDetailPage.title( faq.getTitle() );
        responseDtoForDetailPage.body( faq.getBody() );
        responseDtoForDetailPage.recommend( faq.getRecommend() );
        responseDtoForDetailPage.view( faq.getView() );
        List<String> list = faq.getImgList();
        if ( list != null ) {
            responseDtoForDetailPage.imgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = faq.getTags();
        if ( list1 != null ) {
            responseDtoForDetailPage.tags( new ArrayList<String>( list1 ) );
        }
        responseDtoForDetailPage.question( faq.getQuestion() );
        responseDtoForDetailPage.answer( faq.getAnswer() );

        return responseDtoForDetailPage.build();
    }

    @Override
    public LocalInfo postLocalInfoDtoToLocalInfoEntity(LocalInfoDtos.PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        LocalInfo localInfo = new LocalInfo();

        localInfo.setTitle( postDto.getTitle() );
        localInfo.setBody( postDto.getBody() );
        List<String> list = postDto.getImgList();
        if ( list != null ) {
            localInfo.setImgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = postDto.getTags();
        if ( list1 != null ) {
            localInfo.setTags( new ArrayList<String>( list1 ) );
        }

        return localInfo;
    }

    @Override
    public LocalInfo patchLocalInfoDtoToLocalInfoEntity(LocalInfoDtos.PatchDto patchDto) {
        if ( patchDto == null ) {
            return null;
        }

        LocalInfo localInfo = new LocalInfo();

        if ( patchDto.getPostId() != null ) {
            localInfo.setPostId( patchDto.getPostId() );
        }
        localInfo.setTitle( patchDto.getTitle() );
        localInfo.setBody( patchDto.getBody() );
        List<String> list = patchDto.getImgList();
        if ( list != null ) {
            localInfo.setImgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = patchDto.getTags();
        if ( list1 != null ) {
            localInfo.setTags( new ArrayList<String>( list1 ) );
        }

        return localInfo;
    }

    @Override
    public LocalInfoDtos.ResponseDtoForDetailPage LocalInfoEntityToResponseDtoForDetailPage(LocalInfo localInfo) {
        if ( localInfo == null ) {
            return null;
        }

        LocalInfoDtos.ResponseDtoForDetailPage.ResponseDtoForDetailPageBuilder responseDtoForDetailPage = LocalInfoDtos.ResponseDtoForDetailPage.builder();

        responseDtoForDetailPage.postId( localInfo.getPostId() );
        responseDtoForDetailPage.title( localInfo.getTitle() );
        responseDtoForDetailPage.body( localInfo.getBody() );
        if ( localInfo.getRecommend() != null ) {
            responseDtoForDetailPage.recommend( localInfo.getRecommend() );
        }
        if ( localInfo.getView() != null ) {
            responseDtoForDetailPage.view( localInfo.getView() );
        }
        List<String> list = localInfo.getImgList();
        if ( list != null ) {
            responseDtoForDetailPage.imgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = localInfo.getTags();
        if ( list1 != null ) {
            responseDtoForDetailPage.tags( new ArrayList<String>( list1 ) );
        }
        List<Comment> list2 = localInfo.getCommentList();
        if ( list2 != null ) {
            responseDtoForDetailPage.commentList( new ArrayList<Comment>( list2 ) );
        }

        return responseDtoForDetailPage.build();
    }

    @Override
    public ParentingEvent postParentingEventDtoToParentingEventEntity(ParentingEventDtos.PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        ParentingEvent parentingEvent = new ParentingEvent();

        parentingEvent.setTitle( postDto.getTitle() );
        parentingEvent.setBody( postDto.getBody() );
        List<String> list = postDto.getImgList();
        if ( list != null ) {
            parentingEvent.setImgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = postDto.getTags();
        if ( list1 != null ) {
            parentingEvent.setTags( new ArrayList<String>( list1 ) );
        }

        return parentingEvent;
    }

    @Override
    public ParentingEvent patchParentingEventDtoToParentingEventEntity(ParentingEventDtos.PatchDto patchDto) {
        if ( patchDto == null ) {
            return null;
        }

        ParentingEvent parentingEvent = new ParentingEvent();

        if ( patchDto.getPostId() != null ) {
            parentingEvent.setPostId( patchDto.getPostId() );
        }
        parentingEvent.setTitle( patchDto.getTitle() );
        parentingEvent.setBody( patchDto.getBody() );
        List<String> list = patchDto.getImgList();
        if ( list != null ) {
            parentingEvent.setImgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = patchDto.getTags();
        if ( list1 != null ) {
            parentingEvent.setTags( new ArrayList<String>( list1 ) );
        }

        return parentingEvent;
    }

    @Override
    public ParentingEventDtos.ResponseDtoForDetailPage ParentingEventEntityToResponseDtoForDetailPageDto(ParentingEvent parentingEvent) {
        if ( parentingEvent == null ) {
            return null;
        }

        ParentingEventDtos.ResponseDtoForDetailPage.ResponseDtoForDetailPageBuilder responseDtoForDetailPage = ParentingEventDtos.ResponseDtoForDetailPage.builder();

        responseDtoForDetailPage.postId( parentingEvent.getPostId() );
        responseDtoForDetailPage.title( parentingEvent.getTitle() );
        responseDtoForDetailPage.body( parentingEvent.getBody() );
        if ( parentingEvent.getRecommend() != null ) {
            responseDtoForDetailPage.recommend( parentingEvent.getRecommend() );
        }
        if ( parentingEvent.getView() != null ) {
            responseDtoForDetailPage.view( parentingEvent.getView() );
        }
        List<String> list = parentingEvent.getImgList();
        if ( list != null ) {
            responseDtoForDetailPage.imgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = parentingEvent.getTags();
        if ( list1 != null ) {
            responseDtoForDetailPage.tags( new ArrayList<String>( list1 ) );
        }
        List<Comment> list2 = parentingEvent.getCommentList();
        if ( list2 != null ) {
            responseDtoForDetailPage.commentList( new ArrayList<Comment>( list2 ) );
        }
        responseDtoForDetailPage.startTime( parentingEvent.getStartTime() );
        responseDtoForDetailPage.endTime( parentingEvent.getEndTime() );

        return responseDtoForDetailPage.build();
    }

    @Override
    public ParentingProduct postParentingProductDtoToParentingProductEntity(ParentingProductDtos.PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        ParentingProduct parentingProduct = new ParentingProduct();

        parentingProduct.setTitle( postDto.getTitle() );
        parentingProduct.setBody( postDto.getBody() );
        List<String> list = postDto.getImgList();
        if ( list != null ) {
            parentingProduct.setImgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = postDto.getTags();
        if ( list1 != null ) {
            parentingProduct.setTags( new ArrayList<String>( list1 ) );
        }
        if ( parentingProduct.getUrlList() != null ) {
            List<String> list2 = postDto.getUrlList();
            if ( list2 != null ) {
                parentingProduct.getUrlList().addAll( list2 );
            }
        }

        return parentingProduct;
    }

    @Override
    public ParentingProduct patchParentingProductDtoToParentingProductEntity(ParentingProductDtos.PatchDto patchDto) {
        if ( patchDto == null ) {
            return null;
        }

        ParentingProduct parentingProduct = new ParentingProduct();

        if ( patchDto.getPostId() != null ) {
            parentingProduct.setPostId( patchDto.getPostId() );
        }
        parentingProduct.setTitle( patchDto.getTitle() );
        parentingProduct.setBody( patchDto.getBody() );
        List<String> list = patchDto.getImgList();
        if ( list != null ) {
            parentingProduct.setImgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = patchDto.getTags();
        if ( list1 != null ) {
            parentingProduct.setTags( new ArrayList<String>( list1 ) );
        }
        if ( parentingProduct.getUrlList() != null ) {
            List<String> list2 = patchDto.getUrlList();
            if ( list2 != null ) {
                parentingProduct.getUrlList().addAll( list2 );
            }
        }

        return parentingProduct;
    }

    @Override
    public ParentingProductDtos.ResponseDtoForDetailPage ParentingProductEntityToResponseDtoForDetailPageSto(ParentingProduct parentingProduct) {
        if ( parentingProduct == null ) {
            return null;
        }

        ParentingProductDtos.ResponseDtoForDetailPage.ResponseDtoForDetailPageBuilder responseDtoForDetailPage = ParentingProductDtos.ResponseDtoForDetailPage.builder();

        responseDtoForDetailPage.postId( parentingProduct.getPostId() );
        responseDtoForDetailPage.title( parentingProduct.getTitle() );
        responseDtoForDetailPage.body( parentingProduct.getBody() );
        if ( parentingProduct.getRecommend() != null ) {
            responseDtoForDetailPage.recommend( parentingProduct.getRecommend() );
        }
        if ( parentingProduct.getView() != null ) {
            responseDtoForDetailPage.view( parentingProduct.getView() );
        }
        List<String> list = parentingProduct.getImgList();
        if ( list != null ) {
            responseDtoForDetailPage.imgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = parentingProduct.getTags();
        if ( list1 != null ) {
            responseDtoForDetailPage.tags( new ArrayList<String>( list1 ) );
        }
        List<Comment> list2 = parentingProduct.getCommentList();
        if ( list2 != null ) {
            responseDtoForDetailPage.commentList( new ArrayList<Comment>( list2 ) );
        }
        List<String> list3 = parentingProduct.getUrlList();
        if ( list3 != null ) {
            responseDtoForDetailPage.urlList( new ArrayList<String>( list3 ) );
        }

        return responseDtoForDetailPage.build();
    }

    @Override
    public ThriftShopProduct postThriftShopProductDtoToThriftShopProductEntity(ThriftShopProductDtos.PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        ThriftShopProduct thriftShopProduct = new ThriftShopProduct();

        thriftShopProduct.setTitle( postDto.getTitle() );
        thriftShopProduct.setBody( postDto.getBody() );
        List<String> list = postDto.getImgList();
        if ( list != null ) {
            thriftShopProduct.setImgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = postDto.getTags();
        if ( list1 != null ) {
            thriftShopProduct.setTags( new ArrayList<String>( list1 ) );
        }

        return thriftShopProduct;
    }

    @Override
    public ThriftShopProduct patchThriftShopProductDtoToThriftShopProductEntity(ThriftShopProductDtos.PatchDto patchDto) {
        if ( patchDto == null ) {
            return null;
        }

        ThriftShopProduct thriftShopProduct = new ThriftShopProduct();

        if ( patchDto.getPostId() != null ) {
            thriftShopProduct.setPostId( patchDto.getPostId() );
        }
        thriftShopProduct.setTitle( patchDto.getTitle() );
        thriftShopProduct.setBody( patchDto.getBody() );
        List<String> list = patchDto.getImgList();
        if ( list != null ) {
            thriftShopProduct.setImgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = patchDto.getTags();
        if ( list1 != null ) {
            thriftShopProduct.setTags( new ArrayList<String>( list1 ) );
        }

        return thriftShopProduct;
    }

    @Override
    public ThriftShopProductDtos.ResponseDtoForDetailPage thriftShopProductEntityToResponseDtoForDetailPageDto(ThriftShopProduct thriftShopProduct) {
        if ( thriftShopProduct == null ) {
            return null;
        }

        ThriftShopProductDtos.ResponseDtoForDetailPage.ResponseDtoForDetailPageBuilder responseDtoForDetailPage = ThriftShopProductDtos.ResponseDtoForDetailPage.builder();

        responseDtoForDetailPage.postId( thriftShopProduct.getPostId() );
        responseDtoForDetailPage.title( thriftShopProduct.getTitle() );
        responseDtoForDetailPage.body( thriftShopProduct.getBody() );
        if ( thriftShopProduct.getRecommend() != null ) {
            responseDtoForDetailPage.recommend( thriftShopProduct.getRecommend() );
        }
        if ( thriftShopProduct.getView() != null ) {
            responseDtoForDetailPage.view( thriftShopProduct.getView() );
        }
        List<String> list = thriftShopProduct.getImgList();
        if ( list != null ) {
            responseDtoForDetailPage.imgList( new ArrayList<String>( list ) );
        }
        List<String> list1 = thriftShopProduct.getTags();
        if ( list1 != null ) {
            responseDtoForDetailPage.tags( new ArrayList<String>( list1 ) );
        }
        List<Comment> list2 = thriftShopProduct.getCommentList();
        if ( list2 != null ) {
            responseDtoForDetailPage.commentList( new ArrayList<Comment>( list2 ) );
        }
        responseDtoForDetailPage.productName( thriftShopProduct.getProductName() );
        responseDtoForDetailPage.howToDeliver( thriftShopProduct.getHowToDeliver() );
        responseDtoForDetailPage.price( thriftShopProduct.getPrice() );
        responseDtoForDetailPage.onSale( thriftShopProduct.getOnSale() );

        return responseDtoForDetailPage.build();
    }
}
