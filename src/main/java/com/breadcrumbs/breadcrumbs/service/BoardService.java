package com.breadcrumbs.breadcrumbs.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.breadcrumbs.breadcrumbs.dto.BoardDto;

public interface BoardService {

	public void insert(BoardDto b) throws Exception;

	public Map<String, Object> board_list(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	public void hit(int board_num) throws Exception;

	public BoardDto board_cont(int board_num) throws Exception;

	public int edit(HttpServletResponse response, BoardDto b) throws Exception;

	public int del_ok(HttpServletResponse response, int board_num,
			String board_pass) throws Exception;

	public void reply_ok(BoardDto b) throws Exception;

}
