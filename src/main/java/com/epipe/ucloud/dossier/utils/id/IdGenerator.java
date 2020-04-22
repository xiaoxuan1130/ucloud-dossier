package com.epipe.ucloud.dossier.utils.id;

interface IdGenerator {

	/**
	 * 获取ID
	 * 
	 * @return id值
	 */
	public long nextId();

	/**
	 * 获取ID
	 * 
	 * @return id值
	 */
	public String nextId(int addPos);
}
