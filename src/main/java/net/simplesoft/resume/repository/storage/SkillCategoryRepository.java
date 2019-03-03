package net.simplesoft.resume.repository.storage;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.RepositoryDefinition;

import net.simplesoft.resume.entity.SkillCategory;

@RepositoryDefinition(domainClass=SkillCategory.class, idClass = Long.class)
public interface SkillCategoryRepository {
	
	List<SkillCategory> findAll(Sort sort);

}
