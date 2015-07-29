package services;

import java.util.List;

import entity.WebSubsort;

public interface IWebSubsortServices {
	public List<WebSubsort> findById(Integer id);

	public String findById2(Integer id);

}
