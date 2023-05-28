package com.itwill.shop.board.test;

import java.util.List;

import com.itwill.shop.board.Board;
import com.itwill.shop.board.BoardListPageMakerDto;
import com.itwill.shop.board.BoardService;

public class BoardServiceTestMain {

	public static void main(String[] args) throws Exception {
//		BoardDao boardDao = new BoardDao();
//		System.out.println("search by title");
//		List<Board> searchResultList = new ArrayList<Board>();
//		searchResultList = boardDao.searchByTitle(1, 10, "테스트");
//		System.out.println(searchResultList);
//		
		BoardListPageMakerDto temp = new BoardListPageMakerDto();
//		temp = BoardService.getInstance().sortByReadCount(1);
		temp = BoardService.getInstance().searchByTitle(1, "test");
		List<Board> boardList = temp.itemList;
		for (Board board : boardList) {
			System.out.println(board);
		}
	}

}
