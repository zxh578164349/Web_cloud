package services;

import java.util.List;

import entity.WebMajorsort;

public interface IWebMajorSortServices {
	public List<WebMajorsort> findAll();

	public String findById(Integer id);

}
