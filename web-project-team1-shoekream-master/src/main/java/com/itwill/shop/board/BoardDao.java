package com.itwill.shop.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.itwill.shop.common.DataSourceFactory;


/*
 * 자료실 게시판에서 데이터베이스의 접근을 전담하는 클래스. 
 * BOARD 테이블과의 접근을 담당한다.
 */
public class BoardDao {
	private DataSource dataSource;
	
	
	public BoardDao() throws Exception{
		dataSource=DataSourceFactory.getDataSource();
	}
	/**
	 * 새로운 게시물을 추가하는 메써드.
	 * @throws Exception 
	 */
	public int create(Board board) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_INSERT);
			pstmt.setString(1, board.getBoard_title());
			pstmt.setString(2, board.getBoard_content());
			pstmt.setString(3, board.getUser_id());
			int result = pstmt.executeUpdate();
			return result;
		} finally {
			con.close();
		}
		
	}

	/**
	 * 답글 게시물을 추가하는 메써드
	 */
	public int createReply(Board board) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			// 댓글을 작성할 대상글(원글)의 정보를 조회
			Board temp = this.findBoard(board.getBoard_no());

			// 영향을 받는 기존 글들의 논리적인 순서 번호 변경
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_UPDATE_STEP);
			pstmt.setInt(1, temp.getBoard_step());// step 번호
			pstmt.setInt(2, temp.getBoard_group_no());// group 번호
			pstmt.executeUpdate();
			pstmt.close();

			// 댓글 삽입
			pstmt = con.prepareStatement(BoardSQL.BOARD_INSERT_REPLY);
			pstmt.setString(1, board.getBoard_title());// 제목
			pstmt.setString(2, board.getBoard_content());// 내용
			pstmt.setInt(3, temp.getBoard_group_no());// group no
			pstmt.setInt(4, temp.getBoard_step() + 1);// step
			pstmt.setInt(5, temp.getBoard_depth() + 1);// depth
			pstmt.setString(6, board.getUser_id());// 작성자

			count = pstmt.executeUpdate();
	
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return count;

	}

	/**
	 * 게시물 리스트를 반환(게시물시작번호,게시물끝번호)
	 */
	public ArrayList<Board> findBoardList(int start, int last) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// 조회 결과에 접근하는 참조 변수
		// 데이터베이스의 데이터를 읽어서 저장할 객체 컬렉션
		ArrayList<Board> boards = new ArrayList<Board>();

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_SELECT_ALL_ROWNUM);
			pstmt.setInt(1, start);
			pstmt.setInt(2, last);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();
				board.setBoard_no(rs.getInt(2));
				board.setBoard_title(rs.getString(3));
				board.setBoard_regDate(rs.getDate(4));
				board.setBoard_readCount(rs.getInt(5));
				board.setBoard_group_no(rs.getInt(6));
				board.setBoard_step(rs.getInt(7));
				board.setBoard_depth(rs.getInt(8));
				board.setUser_id(rs.getString(9));

				boards.add(board);
			}
		} finally {
			// 6. 연결닫기
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception ex) {
				}
		}
		// System.out.println(boards);
		return boards;
	}

	/*
	 * 
	 * 
	 */
	/**
	 * 답변게시물 존재여부확인메쏘드
	 */
	public boolean countReplay(Board board) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean isExist = false;
		int cnt = 0;
		try {
			con = dataSource.getConnection();
			StringBuffer sql = new StringBuffer(300);
			sql.append("SELECT ");
			sql.append("count(*) cnt ");
			sql.append("FROM board ");
			sql.append("WHERE groupno = ? ");
			sql.append("AND depth >= ? ");
			sql.append("AND step >= ? ");
			sql.append("ORDER BY step,depth ASC");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, board.getBoard_group_no());
			pstmt.setInt(2, board.getBoard_depth());
			pstmt.setInt(3, board.getBoard_step());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 1) {
				isExist = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ex) {
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return isExist;

	}

	/**
	 * 게시물을 삭제하는 메써드.
	 */
	public int remove(int boardNo) throws Exception{

		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_DELETE_BY_BOARDNO);
			pstmt.setInt(1, boardNo);
			count = pstmt.executeUpdate();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
			try {
				if (con != null)
					con.close();;
			} catch (Exception ex) {
			}
		}
		return count;

	}

	/**
	 * 게시물내용을 수정하는 메써드.
	 */
	public int update(Board board) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_UPDATE);
			pstmt.setString(1, board.getBoard_title());
			pstmt.setString(2, board.getBoard_content());
			pstmt.setInt(3, board.getBoard_no());
			count = pstmt.executeUpdate();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
			try {
				if (con != null)
					con.close();;
			} catch (Exception ex) {
			}
		}
		return count;
	}

	/**
	 * 게시물 번호에 해당하는 게시물 정보를 반환하는 메써드.
	 */
	public Board findBoard(int boardNo) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;

		try {
			con = dataSource.getConnection();
			StringBuffer sql = new StringBuffer(300);
			sql.append("SELECT ");
			sql.append("board_no, board_title, user_id, board_content, ");
			sql.append("board_regdate, board_readcount, ");
			sql.append("board_group_no, board_step, board_depth ");
			sql.append("FROM board ");
			sql.append("WHERE board_no = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new Board();
				board.setBoard_no(rs.getInt(1));
				board.setBoard_title(rs.getString(2));
				board.setUser_id(rs.getString(3));
				board.setBoard_content(rs.getString(4));
				board.setBoard_regDate(rs.getDate(5));
				board.setBoard_readCount(rs.getInt(6));
				board.setBoard_group_no(rs.getInt(7));
				board.setBoard_step(rs.getInt(8));
				board.setBoard_depth(rs.getInt(9));
			}
		}finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ex) {
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return board;

	}

	/**
	 * 게시물 조회수를 1 증가.
	 */
	public void increaseReadCount(int number) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_INCREASE_READCOUNT);
			pstmt.setInt(1, number);
			pstmt.executeUpdate();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * 게시물 총 건수를 조회, 반환
	 */
	public int getBoardCount() throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_COUNT_SELECT);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);

		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ex) {
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return count;
	}
	
	public int countByUserId(String userId) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_COUNT_BY_USERID);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);

		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ex) {
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return count;
	}
	
	/******************************* 추가 기능 구현 *************************************/
	
	/*
	 * 특정 회원의 게시글 카운트
	 */
	public int boardCountByUserId(String userId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_COUNT_BY_USER_ID);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count(*)");
			}
			
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return count;
	}
	
	/*
	 * 회원의 게시물 리스트를 반환
	 */
	public List<Board> findBoardListByUserId(int start, int last, String userId) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boards = new ArrayList<Board>();

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_SELECT_BY_USERID_FOR_USERPAGE);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();
				board.setBoard_no(rs.getInt("board_no"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_regDate(rs.getDate("board_regdate"));
				board.setBoard_readCount(rs.getInt("board_readCount"));
				board.setBoard_group_no(rs.getInt("board_group_no"));
				board.setBoard_step(rs.getInt("board_step"));
				board.setBoard_depth(rs.getInt("board_depth"));
				board.setUser_id(rs.getString("user_id"));

				boards.add(board);
			}
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception ex) {
				}
		}
		return boards;
	}
	
	public List<Board> findBoardListByUserId(String userId) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boards = new ArrayList<Board>();

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_SELECT_BY_USERID_FOR_USERPAGE);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();
				board.setBoard_no(rs.getInt("board_no"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_regDate(rs.getDate("board_regdate"));
				board.setBoard_readCount(rs.getInt("board_readCount"));
				board.setBoard_group_no(rs.getInt("board_group_no"));
				board.setBoard_step(rs.getInt("board_step"));
				board.setBoard_depth(rs.getInt("board_depth"));
				board.setUser_id(rs.getString("user_id"));

				boards.add(board);
			}
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception ex) {
				}
		}
		return boards;
	}
	
	/*
	 * 게시글 제목으로 검색
	 */
	public List<Board> searchByTitle(int start, int last, String keyword) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boards = new ArrayList<Board>();

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_SELECT_BY_TITLE);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, last);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();
				board.setBoard_no(rs.getInt("board_no"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_regDate(rs.getDate("board_regdate"));
				board.setBoard_readCount(rs.getInt("board_readCount"));
				board.setBoard_group_no(rs.getInt("board_group_no"));
				board.setBoard_step(rs.getInt("board_step"));
				board.setBoard_depth(rs.getInt("board_depth"));
				board.setUser_id(rs.getString("user_id"));

				boards.add(board);
			}
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception ex) {
				}
		}
		return boards;
	}
	
	/*
	 * 게시글 조회수 높은 순으로 리스트
	 */
	public List<Board> sortByReadCount(int start, int last) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boards = new ArrayList<Board>();

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(BoardSQL.BOARD_SELECT_BY_READ_COUNT);
			pstmt.setInt(1, start);
			pstmt.setInt(2, last);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();
				board.setBoard_no(rs.getInt(2));
				board.setBoard_title(rs.getString(3));
				board.setBoard_regDate(rs.getDate(4));
				board.setBoard_readCount(rs.getInt(5));
				board.setBoard_group_no(rs.getInt(6));
				board.setBoard_step(rs.getInt(7));
				board.setBoard_depth(rs.getInt(8));
				board.setUser_id(rs.getString(9));

				boards.add(board);
			}
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception ex) {
				}
		}
		return boards;
	}
}
