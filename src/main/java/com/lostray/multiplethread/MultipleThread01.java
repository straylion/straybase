package com.lostray.multiplethread;

public class MultipleThread01 extends Thread {

	private String name;

	public MultipleThread01(String name) {
		this.name = name;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(name + "运行  :  " + i);
			try {
				sleep((int) Math.random() * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		MultipleThread01 mTh1 = new MultipleThread01("A");
		MultipleThread01 mTh2 = new MultipleThread01("B");
		mTh1.start();
		mTh2.start();
	}

}
