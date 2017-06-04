package com.ba.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by ashishagarwal on 6/3/17.
 */
public class ObjectSerializer {
	
	public ObjectSerializer() { }

	public void serialize(Object o, String file) {
		ObjectOutputStream objectOutputStream=null;
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(o);
			objectOutputStream.flush();
		} catch (IOException e) {
			System.out.println("IOException occurred " + e.getMessage());
		} finally {
			try {
				objectOutputStream.close();
			} catch (IOException e) {
				System.out.println("IOException occurred " + e.getMessage());
			}
		}
	}
}
