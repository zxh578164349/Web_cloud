package util;

import java.util.List;

public class Page {
	private int page = 1;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	private int rows = 10;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private int pageSize;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private List list;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
