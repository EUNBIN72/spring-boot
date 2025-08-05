package com.winter.app.board.qna;

import java.time.LocalDateTime;

import com.winter.app.board.BoardVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaVO extends BoardVO {
	
	private Long boardRef;
	private Long boardStep;
	private Long boardDepth;

}
