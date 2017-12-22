package com.breadcrumbs.breadcrumbs.board.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.BoardDao;
import com.breadcrumbs.breadcrumbs.dto.FormBoardDto;

@Service
public class BoardUpdateAction {

	@Autowired
	private BoardDao boardDao;

	public boolean execute(FormBoardDto formdto, HttpServletRequest request) {
		// Dao에게 넘겨줄 Map 생성
		Map<String, Object> map = new HashMap<String, Object>();
		// 파일이 실제 업로드 될 폴더 경로 생성
		String realFolder = request.getSession().getServletContext()
				.getRealPath("boardupload");
		// 맵 설정
		map.put("board_num", request.getParameter("board_num"));
		map.put("board_subject", formdto.getBoard_Subject());
		map.put("board_content", formdto.getBoard_Content());
		// request의 getRemoteAddr()을 호출하면
		// 현재 접속된 곳의 ip 주소를 문자열로
		// 리턴합니다.
		map.put("board_ip", request.getRemoteAddr());
		boolean result = false;
		try {
			// 선택된 파일이 있다면
			if (formdto.getBoard_File().getOriginalFilename().length() > 0) {
				// 맵에 새로 선택한 파일명을 입력
				map.put("board_file", formdto.getBoard_File()
						.getOriginalFilename());
				// 파일을 다시 업로드
				File f = new File(realFolder + "\\"
						+ formdto.getBoard_File().getOriginalFilename());
				formdto.getBoard_File().transferTo(f);
			}
			// 선택한 파일이 없으면 기존 파일 이름 설정
			else {
				map.put("board_file", request.getParameter("board_oldfile"));
			}
			// 데이터 수정
			result = boardDao.updateBoard(map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;

	}
}
