package dao;

import java.util.List;

import entity.WebSubsort;

public interface IWebSubSortDao {
	public List<WebSubsort> findById(Integer id);

	public String findById2(Integer id);

}
