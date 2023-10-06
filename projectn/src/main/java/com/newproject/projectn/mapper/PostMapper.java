package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.post.*;
import com.newproject.projectn.entitiy.post.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")// 매핑 인터페이스가 스프링의 매퍼라고 인식시킴
public interface PostMapper {

    Post postPostDtoToPostEntity(PostDtos.PostPostDto postDto);
    Post patchPostDtoToPostEntity(PostDtos.PatchDto patchPostDto);
    PostDtos.ResponseDtoForDetailPage PostEntityToResponseDtoForDetailPage(Post post);

    FAQ postFAQDtoToFAQEntity(FaqDtos.PostDto postDto);

    FAQ patchFAQDtoToFAQEntity(FaqDtos.PatchDto patchDto);

    FaqDtos.ResponseDtoForDetailPage FAQEntityToSingleResponseDto(FAQ faq);

    LocalInfo postLocalInfoDtoToLocalInfoEntity(LocalInfoDtos.PostDto postDto);

    LocalInfo patchLocalInfoDtoToLocalInfoEntity(LocalInfoDtos.PatchDto patchDto);

    LocalInfoDtos.ResponseDtoForDetailPage LocalInfoEntityToResponseDtoForDetailPage(LocalInfo localInfo);



    ParentingEvent postParentingEventDtoToParentingEventEntity(ParentingEventDtos.PostDto postDto);
    ParentingEvent patchParentingEventDtoToParentingEventEntity(ParentingEventDtos.PatchDto patchDto);
    ParentingEventDtos.ResponseDtoForDetailPage ParentingEventEntityToResponseDtoForDetailPageDto(ParentingEvent parentingEvent);



    ParentingProduct postParentingProductDtoToParentingProductEntity(ParentingProductDtos.PostDto postDto);

    ParentingProduct patchParentingProductDtoToParentingProductEntity(ParentingProductDtos.PatchDto patchDto);

    ParentingProductDtos.ResponseDtoForDetailPage ParentingProductEntityToResponseDtoForDetailPageSto(ParentingProduct parentingProduct);

    ThriftShopProduct postThriftShopProductDtoToThriftShopProductEntity(ThriftShopProductDtos.PostDto postDto);

    ThriftShopProduct patchThriftShopProductDtoToThriftShopProductEntity(ThriftShopProductDtos.PatchDto patchDto);

    ThriftShopProductDtos.ResponseDtoForDetailPage thriftShopProductEntityToResponseDtoForDetailPageDto(ThriftShopProduct thriftShopProduct);


}