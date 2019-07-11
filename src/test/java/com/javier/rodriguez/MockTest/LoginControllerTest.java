package com.javier.rodriguez.MockTest;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.security.Principal;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.cert.X509Certificate;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class LoginControllerTest {
	@Test
	public void testLoginConCertificado() throws CertificateException, javax.security.cert.CertificateException {

		// Arrange
		byte[] certData = new byte[0];
		X509Certificate certificado = X509Certificate.getInstance(certData);
		Connection connection = null;

		// Act
		LoginController controller = new LoginController(connection);
		boolean login = controller.login(certificado);

		// Assertion
		assertTrue(login);

	}

	@Test
	public void testLoginConCertificadoMock()
			throws CertificateException, javax.security.cert.CertificateException, SQLException {

		// Arrange
		X509Certificate certificado = mock(X509Certificate.class);
		Connection connection = mock(Connection.class);
		
		//Programar Respuestas. 
		Principal  principal = mock(Principal.class);
		when(certificado.getSubjectDN()).thenReturn(principal);
		when(principal.getName()).thenReturn("usuario");
		
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
		when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
		ResultSet resultset = mock(ResultSet.class);
		when(preparedStatement.executeQuery()).thenReturn(resultset);
		when(resultset.next()).thenReturn(true);
		when(resultset.getInt(1)).thenReturn(1);
		
		//Act
		LoginController controller = new LoginController(connection);
		boolean login = controller.login(certificado);
		
		//Assert
		assertTrue(login);
		
		
		

	}
	
	@Test
	public void TestALoLoco() throws CertificateException{
		LoginController controller = mock(LoginController.class);
		when(controller.login(null)).thenReturn(true);
		boolean login = controller.login(null);
		
		assertTrue(login);
		
	}

}
