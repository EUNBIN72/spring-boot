package com.winter.app.board;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class BoardVO {
// = DTO

	private Long boardNum;
	@NotBlank  // null을 허용하지 않고 문자 1개 이상이 필수 (제목이 비어있으면 안됨)
	private String boardTitle;
	private String boardContents;
	private String boardWriter;
	private LocalDateTime boardDate;
	private Long boardHit;
	
	private List<BoardFileVO> boardFileVOs;
}
