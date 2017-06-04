package com.ba.serialization;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by ashishagarwal on 6/3/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ObjectDeserializer.class})
public class ObjectDeserializerUnitTest {

	@Mock private ObjectInputStream in;
	@Mock private FileInputStream fi;
	
	@Before
	public void setup() throws Exception {
		PowerMockito.whenNew(FileInputStream.class).withArguments(Mockito.anyString()).thenReturn(fi);
		PowerMockito.whenNew(ObjectInputStream.class).withArguments(fi).thenReturn(in);
	}
	
	@Test
	public void shouldDeserialize() throws IOException, ClassNotFoundException {
		when(in.readObject()).thenReturn("new String");
		ObjectDeserializer deserializer = new ObjectDeserializer();
		String actual = (String)deserializer.deserialize(Mockito.anyString());
		assertTrue("new String".equals(actual));
	}

	@Test
	public void shouldThrowExceptionWhenDeserialize() throws Exception {
		ObjectDeserializer spy = PowerMockito.spy(new ObjectDeserializer());
		PowerMockito.doThrow(new IOException()).when(spy, "readFromClasspathResource", anyString());
		PowerMockito.doThrow(new IOException()).when(spy, "readFromFile", anyString());
		String actual = (String)spy.deserialize(Mockito.anyString());
	}
}

