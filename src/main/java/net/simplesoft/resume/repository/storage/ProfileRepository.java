package net.simplesoft.resume.repository.storage;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import net.simplesoft.resume.entity.Profile;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {
	
	Profile findByUid(@Param("uid") String uid);
	
/*	@Query(nativeQuery = true, value="select * from Profile p"
			+ " left join course c on c.id_profile = p.id "
			+ " left join skill s  on s.id_profile = p.id "
			+ " left join hobby h on h.id_profile = p.id "
			+ " left join certificate cr on cr.id_profile = p.id "
			+ " left join education e on e.id_profile = p.id "
			+ " left join language l on l.id_profile = p.id "
			+ " left join practic pr on pr.id_profile = p.id "
			+ " where p.uid = ?1 ")*/
	@Query("SELECT p FROM Profile p "
			+ "LEFT JOIN FETCH p.skills LEFT JOIN FETCH p.educations "
			+ "LEFT JOIN FETCH p.practics LEFT JOIN FETCH p.hobbies  "
			+ "LEFT JOIN FETCH p.languages LEFT JOIN FETCH p.courses  "
			+ "LEFT JOIN FETCH p.certificates  "
			+ "WHERE p.uid =:uid")
	Profile selectProfileWithJoins(@Param("uid") String uid);
	
	Profile findByEmail(String email);
	
	Profile findByPhone(String phone);
	
	int countByUid(String uid);
	
	Page<Profile> findAllByCompletedTrue(Pageable pageable);
	
	List<Profile> findByCompletedFalseAndCreatedBefore(Timestamp oldDate);

}
