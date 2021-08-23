package com.vaccinelife.vaccinelifeapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.vaccinelife.vaccinelifeapi.model.VacBoardLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacBoardLikeMypageDto {

    private Long vacBoardId;
    private Long userId;
    private String title;
    private int likeCount;
    private int totalVisitors;
    private int commentCount;
    private String type;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "asia/seoul")
    @CreatedDate // 최초 생성 시점
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "asia/seoul")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @LastModifiedDate // 마지막 변경 시점
    private LocalDateTime modifiedAt;


    public static VacBoardLikeMypageDto of(VacBoardLike vacBoardLike) {
        return VacBoardLikeMypageDto.builder()
                .vacBoardId(vacBoardLike.getVacBoard().getId())
                .userId(vacBoardLike.getUser().getId())
                .title(vacBoardLike.getVacBoard().getTitle())
                .likeCount(vacBoardLike.getVacBoard().getLikeCount())
                .totalVisitors(vacBoardLike.getVacBoard().getTotalVisitors())
                .commentCount(vacBoardLike.getVacBoard().getCommentCount())
                .type(vacBoardLike.getUser().getType())
                .createdAt(vacBoardLike.getVacBoard().getCreatedAt())
                .modifiedAt(vacBoardLike.getVacBoard().getModifiedAt())
                .build();

    }

    public static List<VacBoardLikeMypageDto> list(List<VacBoardLike> vacBoardLike) {
        List<VacBoardLikeMypageDto> vacBoardLikeRequestDtos = new ArrayList<>();
        for (VacBoardLike vacBoardLikes : vacBoardLike) {
            vacBoardLikeRequestDtos.add(of(vacBoardLikes));
        }
        return vacBoardLikeRequestDtos;
    }
}
