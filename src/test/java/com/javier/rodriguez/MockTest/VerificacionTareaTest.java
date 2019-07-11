package com.javier.rodriguez.MockTest;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.security.Principal;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit test for simple App.
 */
public class VerificacionTareaTest {
	
	@Test
	public void testVerificarLaLlamadaAlMockDepreparedStatement() throws SQLException {
		
		//Arrange
		Tarea tarea = new Tarea("Aprender a Verificar");
		Connection connection = mock(Connection.class);
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
//		when(preparedStatement.executeUpdate())
//		.thenThrow(new SQLException("Excepción forzada"));
		when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
		
		//Act
		TareaRepository repository = new TareaRepository(connection);
		repository.save(tarea);

		//Assert
		verify(preparedStatement).executeUpdate();
		verify(preparedStatement).setString(1, "Aprender a Verificar");
		
		
	}
	
	@Test
	public void testEspiarElMetodoGetNombreDeLaTarea() throws SQLException{
		//Arrange
				Tarea tarea = new Tarea("Aprender a Verificar");
				Tarea spy = Mockito.spy(tarea);
				
				Connection connection = mock(Connection.class);
				PreparedStatement preparedStatement = mock(PreparedStatement.class);
//				when(preparedStatement.executeUpdate())
//				.thenThrow(new SQLException("Excepción forzada"));
				when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
				
				//Act
				TareaRepository repository = new TareaRepository(connection);
				repository.save(spy);

				//Assert
				verify(spy).getNombre();
				
	}

}
