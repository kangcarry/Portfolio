package com.itwill.ilhajob.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.entity.Review;
import com.itwill.ilhajob.user.entity.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	//findBy+entity의 컬럼이름 으로 찾아줌
	//List<Review> findByUser(String userEmail);
	
	//위 메소드 안되면 -> 아래꺼사용
	//List<Review> findByUser(User user);
	
	//Optional이 필요한경우
	//Optional<List<Review>> findByUser(String userEmail);
	
	//List<Review> findByCorp(String CorpLoginId);
	
		//위 메소드 안되면 -> 아래꺼사용
	List<Review> findByCorpId(Long Corpid);
	
	//Optional<List<Review>> findByCorp(String corpLoginId);
	
	
	//boolean existsByUserAndCorp(Long id, String CorpLoginId);
	
	Long countByUserIdAndCorpId(Long id, Long Corpid);

	
 }
/* <<ServiceImpl에서 작성이 필요한 부분>>
 * Optional<List<Person>> optionalPersons = personRepository.findByFirstName("John");
 
if (optionalPersons.isPresent()) {
List<Person> persons = optionalPersons.get();
// 결과를 처리하는 코드 작성
} else {
// 결과가 없는 경우 처리하는 코드 작성
}
*/
/*
 * boolean exists = personRepository.existsByFirstNameAndLastName("John", "Doe");
if (exists) {
    // 특정 이름과 성을 갖는 사람이 데이터베이스에 존재하는 경우 수행할 작업
} else {
    // 특정 이름과 성을 갖는 사람이 데이터베이스에 존재하지 않는 경우 수행할 작업
}

 */

