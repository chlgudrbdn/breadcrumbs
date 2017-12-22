package com.breadcrumbs.breadcrumbs.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.BoardDao;
import com.breadcrumbs.breadcrumbs.dto.BoardDto;

@Service
public class BoardListAction {

	@Autowired
	private BoardDao boardDao;

	// HttpServletRequest를 매개변수로 받아서
	// 작업을 수행
	public Map<String, Object> execute(HttpServletRequest request) {
		// 기본적으로 사용할 페이지 번호 설정
		int page = 1;
		// 한 페이지에 출력할 데이터 개수 설정
		int limit = 10;
		// page 파라미터의 값이 있으면 정수로 변환해서
		// page에 대입
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// page와 limit을 이용해서 시작하는 데이터 번호와
		// 끝나는 번호를 계산
		// page=1 start=01, page=2 start=11, page=3 start=21
		int start = (page - 1) * limit + 1;
		int end = start + limit - 1;
		// 위 2개의 값을 Map을 생성해서 start와 end라는
		// 키로 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);

		// 데이터 가져오기
		List<BoardDto> list = boardDao.getBoardList(map);
		// 전체 데이터 개수 가져오기
		int listcount = boardDao.getListCount();

		// 가장 큰 페이지 번호 계산
		int maxpage = (int) ((double) listcount / limit + 0.95);
		// 시작하는 페이지 번호 계산
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// 마지막 페이지 번호 계산
		int endpage = maxpage;
		if (endpage > (startpage + 9)) {
			endpage = startpage + 9;
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("page", page);
		resultMap.put("listcount", listcount);
		resultMap.put("boardlist", list);
		resultMap.put("maxpage", maxpage);
		resultMap.put("startpage", startpage);
		resultMap.put("endpage", endpage);

		return resultMap;

	}

}
