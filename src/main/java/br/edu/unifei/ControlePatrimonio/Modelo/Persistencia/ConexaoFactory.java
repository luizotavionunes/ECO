package br.edu.unifei.ControlePatrimonio.Modelo.Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;
/**
 * Classe utilizada para conectar-se com o banco de dados
 * @author Estagio
 *
 */
public class ConexaoFactory {

	public static Connection getConnection() {

		try {

			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/controle_lab_eco_bd", "root", "scpECO2016");

		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco.");
			throw new RuntimeException(e);

		}

	}

}