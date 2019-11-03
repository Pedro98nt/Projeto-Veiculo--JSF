package br.edu.faculdadedelta.projetoveiculojsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.projetoveiculojsf.modelo.Veiculo;
import br.edu.faculdadedelta.projetoveiculojsf.util.Conexao;

public class VeiculoDAO {

	public void incluir(Veiculo veiculo) 
			throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO veiculos (marca_veiculo, fabricante_veiculo, "
				+ " valor_veiculo, data_fabricacao_veiculo) VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, veiculo.getMarca().trim());
		ps.setString(2, veiculo.getFabricante().trim());
		ps.setDouble(3, veiculo.getValor());
		ps.setDate(4, new java.sql.Date(veiculo.getDataFabricacao().getTime()));
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void alterar(Veiculo veiculo) 
			throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "UPDATE veiculos "
				+ " SET marca_veiculo = ?, "
				+ " fabricante_veiculo = ?, "
				+ " valor_veiculo = ?, "
				+ " data_fabricacao_veiculo = ? "
				+ " WHERE id_veiculo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, veiculo.getMarca().trim());
		ps.setString(2, veiculo.getFabricante().trim());
		ps.setDouble(3, veiculo.getValor());
		ps.setDate(4, new java.sql.Date(veiculo.getDataFabricacao().getTime()));
		ps.setLong(5, veiculo.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void excluir(Veiculo veiculo) 
			throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM veiculos WHERE id_veiculo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, veiculo.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public List<Veiculo> listar() 
			throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_veiculo, marca_veiculo, "
				+ " fabricante_veiculo, valor_veiculo, data_fabricacao_veiculo "
				+ " FROM veiculos";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Veiculo> listaRetorno = new ArrayList<>();
		while(rs.next()) {
			Veiculo veiculo = new Veiculo();
			veiculo.setId(rs.getLong("id_veiculo"));
			veiculo.setMarca(rs.getString("marca_veiculo").trim());
			veiculo.setFabricante(rs.getString("fabricante_veiculo").trim());
			veiculo.setValor(rs.getDouble("valor_veiculo"));
			veiculo.setDataFabricacao(rs.getDate("data_fabricacao_veiculo"));
			
			listaRetorno.add(veiculo);
		}
		ps.close();
		rs.close();
		conn.close();
		return listaRetorno;
	}
}





