package com.ba.serialization;

import static org.mockito.Mockito.doThrow;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ba.RandomGenerator;

/**
 * Created by ashishagarwal on 6/3/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ObjectSerializer.class})
public class ObjectSerializerUnitTest {

	@Mock private ObjectOutputStream out;
	@Mock private FileOutputStream fo;

	private ObjectSerializer serializer;
	
	@Before
	public void setup() throws Exception {
		PowerMockito.whenNew(FileOutputStream.class).withArguments(Mockito.anyString()).thenReturn(fo);
		PowerMockito.whenNew(ObjectOutputStream.class).withArguments(fo).thenReturn(out);
		
		serializer = new ObjectSerializer();
	}
	
	@Test
	public void shouldSerialize() throws Exception {
		Integer i = new Integer(RandomGenerator.getRandomNumber(5));
		serializer.serialize(i);
		
		Mockito.verify(out, Mockito.times(1)).writeObject(i);
		Mockito.verify(out, Mockito.times(1)).flush();
	}

	@Test
	public void shouldThrowExceptionOnSerialize() throws Exception {
		Integer i = new Integer(RandomGenerator.getRandomNumber(5));
		doThrow(new IOException()).when(out).writeObject(i);
		doThrow(new IOException()).when(out).close();
		
		serializer.serialize(i);
	}
}
