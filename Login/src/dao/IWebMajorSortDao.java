package dao;

import java.util.List;

import entity.WebMajorsort;

public interface IWebMajorSortDao {
	public List<WebMajorsort> findAll();

	public String findById(Integer id);

	public Integer findByName(String name);

}
