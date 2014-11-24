package com.projectx.rest.utils;

public class SimulationThread implements Runnable {

	@Override
	public void run() {
		

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("After simulation");
	}

}
