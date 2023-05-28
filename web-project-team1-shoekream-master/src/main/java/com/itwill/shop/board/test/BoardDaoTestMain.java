package com.itwill.shop.board.test;

import java.util.ArrayList;
import java.util.List;

import com.itwill.shop.board.Board;
import com.itwill.shop.board.BoardDao;

public class BoardDaoTestMain {

	public static void main(String[] args) throws Exception {
		BoardDao boardDao = new BoardDao();
//		List<Board> boardListByReadCount = boardDao.sortByReadCount(0, 0);
//		System.out.println(boardListByReadCount);
		
		System.out.println("회원의 게시글 수 찾기");
		System.out.println(boardDao.boardCountByUserId("cream1"));
		System.out.println("test");
		
		List<Board> boardList = boardDao.findBoardListByUserId("cream2");
		for (Board board : boardList) {
			System.out.println(board.getUser_id());
		}
		
		//		List<Board> boardList = new ArrayList<Board>();
//		boardList = boardDao.searchByTitle(1, 100, "test");
//		System.out.println(boardList);
//		for (Board board : boardList) {
//			System.out.println(board.getBoard_title());
		}
//		boardList = boardDao.findBoardListByUserId("cream1");
//		System.out.println(boardList.get(0).getBoard_content());
//		System.out.println(boardList.get(0));
//		
//		System.out.println(boardDao.findBoardList(1, 2));
	}

