package util;

import java.util.List;

public class PageBean {

	private List list;// �秋撫嚙踝蕭豯佗蕭嚙踐�嚙賣�蕭�嚙踝蕭謅�
	private int allRow;// 嚙賡���
	private int totalPage;// 嚙賡��喉蕭嚙�
	private int currentPage;// �塗嚙賣嚙�
	private int pageSize;// �伍�喲���蕭嚙踝蕭

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getAllRow() {
		return allRow;
	}

	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private boolean isFirstPage;// 嚙質��蝞斯�蕭��
	private boolean isLastPage;// 嚙質��蝞賂蕭嚙踐�嚙賣嚙�
	private boolean hasPreviousPage;// 嚙質�蕭��蕭�蕭��
	private boolean hasNextPage;// 嚙質�蕭�ｇ蕭�蕭��

	/** */
	/**
	 * 嚙踐�嚙踝蕭謘潘蕭�輯�蝮蕭嚙�
	 */
	public void init() {
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasPreviousPage();
		this.hasNextPage = isHasNextPage();
	}

	/** */
	/**
	 * �鼎嚙踝蕭���輯�嚙賡�伍�,嚙質嚙篇etter嚙賢�嚙�is嚙賢�嚙�嚙賢��
	 * 
	 * @return
	 */

	public boolean isFirstPage() {
		return currentPage == 1;// ��謢��伐蕭�輯�謢��嚙賣嚙�
	}

	public boolean isLastPage() {
		return currentPage == totalPage;// ��嚙賣�伐蕭�輯�謢�蕭嚙踝蕭�蕭��
	}

	public boolean isHasPreviousPage() {
		return currentPage != 1;// 嚙質嚙賣�伐蕭�輯�嚙踝蕭�斯1�選蕭
	}

	public boolean isHasNextPage() {
		return currentPage != totalPage;// 嚙質嚙賣�伐蕭�輯�嚙踝蕭��蕭嚙踝蕭�選蕭
	}

	/** */
	/**
	 * ��嚙踝蕭擏嚙踝蕭嚙踐�嚙踝蕭撖蕭,��嚙踝蕭�踐�嚙賭遛嚙賡��急嚙踝�嚙踝蕭嚙�
	 * 
	 * @param pageSize
	 *            �伍�喲���蕭嚙踝蕭
	 * @param allRow
	 *            嚙賡���
	 * @return 嚙賡��喉蕭嚙�
	 */
	public static int countTotalPage(final int pageSize, final int allRow) {
		int totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow
				/ pageSize + 1;
		return totalPage;
	}

	/** */
	/**
	 * ��嚙賣�伐蕭�輯�嚙賣���塚蕭
	 * 
	 * @param pageSize
	 *            �伍�喲���蕭嚙踝蕭
	 * @param currentPage
	 *            �塗嚙質��蕭�選蕭
	 * @return �塗嚙賣�蕭�迎����
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}

	/** */
	/**
	 * ��嚙賣�伐蕭�選蕭嚙賭鼎��嚙踐▼嚙賡��蕭嚙踝RL���瘀蕭嚙�page=",嚙踐����蕭謆�
	 * 
	 * @param page
	 *            �∵��荔蕭�蕭嚙踝蕭嚙質��蝞�,嚙踝蕭,嚙踐�嚙踝蕭嚙�
	 * @return �塗嚙賣嚙�
	 */
	public static int countCurrentPage(int page) {
		final int curPage = (page <= 0 ? 1 : page);
		return curPage;
	}

}
