package com.yunlongstudio.algorithm.tree;

public interface MyIterator {
	/*
	 * 迭代器ADT接口
	 */
	boolean	hasNext();//检查迭代器中是否还有剩余的元素
	Object	getNext();//返回迭代器中的下一元素
}
