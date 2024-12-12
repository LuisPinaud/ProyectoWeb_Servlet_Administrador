package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Libros;
import interfaces.LibrosInterface;
import util.MySqlConexion;

public class LibrosModel implements LibrosInterface {

	@Override
	public List<Libros> listLibros() {
		// TODO Auto-generated method stub
		List<Libros> listLibros = new ArrayList<Libros>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "SELECT * FROM LIBROS";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
				Libros lb = new Libros();
				lb.setISBN(rs.getString("ISBN"));
				lb.setTitulo(rs.getString("Titulo"));
				lb.setResenia(rs.getString("Resenia"));
				lb.setPrecio(rs.getDouble("Precio"));
				lb.setStock(rs.getInt("Stock"));
				lb.setFechaPublicacion(rs.getString("FechaPublicacion"));
				lb.setIDAutor(rs.getInt("IDAutor"));
				lb.setIDEditorial(rs.getInt("IDEditorial"));
				lb.setRestriccionEdad(rs.getInt("RestriccionEdad"));
				listLibros.add(lb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return listLibros;
	}

	@Override
	public Libros getLibros(String id) {
		// TODO Auto-generated method stub
		Libros libro=null;
		Connection cn=null;
		PreparedStatement psm=null;
		ResultSet rs=null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql="SELECT * FROM LIBROS WHERE ISBN=?";
			psm = cn.prepareStatement(sql);
			psm.setString(1, id);
			rs=psm.executeQuery();
			while (rs.next()) {
				libro = new Libros();
				libro.setISBN(rs.getString("ISBN"));
				libro.setTitulo(rs.getString("Titulo"));
				libro.setResenia(rs.getString("Resenia"));
				libro.setPrecio(rs.getDouble("Precio"));
				libro.setStock(rs.getInt("Stock"));
				libro.setFechaPublicacion(rs.getString("FechaPublicacion"));
				libro.setIDAutor(rs.getInt("IDAutor"));
				libro.setIDEditorial(rs.getInt("IDEditorial"));
				libro.setRestriccionEdad(rs.getInt("RestriccionEdad"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return libro;
	}

	@Override
	public List<Libros> listLibrosCategoria(int id) {
		// TODO Auto-generated method stub
		List<Libros> listLibrosCategoria = new ArrayList<Libros>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql = "pa_ObtenerLibrosPorCategoria(?)";
			psm = cn.prepareStatement(sql);
			psm.setInt(1, id);
			rs = psm.executeQuery();
			while (rs.next()) {
				Libros lb = new Libros();
				lb.setISBN(rs.getString("ISBN"));
				lb.setTitulo(rs.getString("Titulo"));
				lb.setResenia(rs.getString("Resenia"));
				lb.setPrecio(rs.getDouble("Precio"));
				lb.setStock(rs.getInt("Stock"));
				lb.setFechaPublicacion(rs.getString("FechaPublicacion"));
				lb.setIDAutor(rs.getInt("IDAutor"));
				lb.setIDEditorial(rs.getInt("IDEditorial"));
				lb.setRestriccionEdad(rs.getInt("RestriccionEdad"));
				listLibrosCategoria.add(lb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return listLibrosCategoria;
	}

	@Override
	public List<Libros> buscarLibros(String texto) {
		// TODO Auto-generated method stub
		List<Libros> listLibrosBusqueda = new ArrayList<Libros>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		String sql;
		sql="select * from libros where titulo like '%"+texto+"%'";
		
		try {
			cn = MySqlConexion.getConexion();
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			while (rs.next()) {
				Libros lb = new Libros();
				lb.setISBN(rs.getString("ISBN"));
				lb.setTitulo(rs.getString("Titulo"));
				lb.setResenia(rs.getString("Resenia"));
				lb.setPrecio(rs.getDouble("Precio"));
				lb.setStock(rs.getInt("Stock"));
				lb.setFechaPublicacion(rs.getString("FechaPublicacion"));
				lb.setIDAutor(rs.getInt("IDAutor"));
				lb.setIDEditorial(rs.getInt("IDEditorial"));
				lb.setRestriccionEdad(rs.getInt("RestriccionEdad"));
				listLibrosBusqueda.add(lb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return listLibrosBusqueda;
	}

	@Override
	public int createLibro(Libros libro) {
		// TODO Auto-generated method stub
		int value=0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MySqlConexion.getConexion();
			
			String sql = "insert into libros values (?,?,?,?,?,?,?,?,?)";
			psm=cn.prepareStatement(sql);
			
			psm.setString(1, libro.getISBN());
			psm.setString(2, libro.getTitulo());
			psm.setString(3, libro.getResenia());
			psm.setDouble(4, libro.getPrecio());
			psm.setInt(5, libro.getStock());
			psm.setString(6, libro.getFechaPublicacion());
			psm.setInt(7, libro.getIDAutor());
			psm.setInt(8, libro.getIDEditorial());
			psm.setInt(9, libro.getRestriccionEdad());
			
			value=psm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public int updateLibros(Libros libro) {
		// TODO Auto-generated method stub
		int value=0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MySqlConexion.getConexion();
			
			String sql = "UPDATE libros set Titulo=?, Resenia=?, Precio=?, Stock=?, FechaPublicacion=?, IDAutor=?, IDEditorial=?, RestriccionEdad=? where ISBN=?";
			psm=cn.prepareStatement(sql);
			
			psm.setString(1, libro.getTitulo());
			psm.setString(2, libro.getResenia());
			psm.setDouble(3, libro.getPrecio());
			psm.setInt(4, libro.getStock());
			psm.setString(5, libro.getFechaPublicacion());
			psm.setInt(6, libro.getIDAutor());
			psm.setInt(7, libro.getIDEditorial());
			psm.setInt(8, libro.getRestriccionEdad());
			psm.setString(9, libro.getISBN());
			
			value=psm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(psm != null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int deleteLibros(String id) {
		// TODO Auto-generated method stub
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String sql="DELETE FROM Libros WHERE ISBN = ?";
			psm=cn.prepareStatement(sql);
			psm.setString(1, id);
			
			value=psm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(psm !=null) psm.close();
				if(cn != null) cn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		return 0;
	}

}
