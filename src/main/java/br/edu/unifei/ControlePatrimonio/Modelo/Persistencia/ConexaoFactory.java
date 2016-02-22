package br.edu.unifei.ControlePatrimonio.Modelo.Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

public class ConexaoFactory {

	public static Connection getConnection() {

		System.out.println("Conectando ao banco");

		try {

			
				//Class.forName("com.mysql.jdbc.Driver");
				 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				System.out.println("Conectado ao banco");
				return DriverManager.getConnection("jdbc:mysql://localhost:3306/controle_lab_eco_bd", "root", "root");

		
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
			
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco 2.");
			throw new RuntimeException(e);

		}

	}

}