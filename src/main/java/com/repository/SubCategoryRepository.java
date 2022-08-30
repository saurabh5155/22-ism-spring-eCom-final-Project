package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.CategoryBean;
import com.bean.SubCategoryBean;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategoryBean, Integer>{
    List<SubCategoryBean> findAll();
    SubCategoryBean findBySubCategoryId(Integer subCategoryId);
    List<SubCategoryBean> findByCategory(CategoryBean category);
}
