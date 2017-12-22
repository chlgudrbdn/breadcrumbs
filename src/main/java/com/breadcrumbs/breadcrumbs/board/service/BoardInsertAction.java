package com.breadcrumbs.breadcrumbs.board.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.BoardDao;
import com.breadcrumbs.breadcrumbs.dto.BoardDto;
import com.breadcrumbs.breadcrumbs.dto.FormBoardDto;

@Service
public class BoardInsertAction {

	private BoardDao boardDao;

	public boolean execute(FormBoardDto formDto, HttpServletRequest request) {
		// 실제 파일 업로드 처리
		// 업로드할 폴더의 경로를 생성
		String realFolder = request.getSession().getServletContext()
				.getRealPath("boardupload");
		// 업로드할 파일의 이름을 가져오기
		String filename = formDto.getBoard_File().getOriginalFilename();
		String filepath = realFolder + "\\" + filename;
		File f = new File(filepath);
		try {
			// 업로드할려고 선택한 파일을 f에 전송
			formDto.getBoard_File().transferTo(f);
		} catch (Exception e) {
		}

		// Dao에서 사용할 데이터 만들기
		BoardDto dto = new BoardDto();
		// 글번호 설정
		dto.setBoard_Num(boardDao.maxBoardNum() + 1);
		// 글제목 설정
		dto.setBoard_Subject(formDto.getBoard_Subject());
		// 글내용 설정
		dto.setBoard_Content(formDto.getBoard_Content());
		// 글쓴이 설정
		dto.setBoard_Id(formDto.getEmail());
		// 파일이름 설정
		dto.setBoard_file(filename);
		// 글쓴 곳의 아이피 설정
		dto.setBoard_Ip(request.getRemoteAddr());

		// 데이터 삽입
		boolean r = boardDao.insertBoard(dto);

		return r;
	}
}
