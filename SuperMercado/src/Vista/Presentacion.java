package Vista;
import java.util.ArrayList;

import Dal.DaoProductos;
import Dal.DaoUsuarios;
import entidades.Productos;
import entidades.Usuario;

public class Presentacion {
	
	public static void main(String[] args) {
		//Funcionan MostrarTodos.
		mostrarTodosUsuarios();
		mostrarTodosProductos();
		
		//Funcionan selecionar por id.
		Usuario usuario = DaoUsuarios.obtenerPorId(1);
		Productos producto = DaoProductos.obtenerPorId(1);
		System.out.println(usuario);
		System.out.println(producto);
		
		//Funcionan Seleccion por nombre
		producto = DaoProductos.obtenerPorNombre("Papas");
		System.out.println(producto);
		
		//Funcionan Seleccion por categoria
		ArrayList<Productos> productos = DaoProductos.obtenerPorCategoria("Verdura");
		System.out.println(productos);
		
		//Funcionan Insert-modificarPorId-ModificarPorNombre-Borrar
		mostrarTodosUsuarios();
		mostrarTodosProductos();
		
		usuario = DaoUsuarios.insertar(new Usuario(null, "Newpepe@pepe.pepe", "passpepe", "pepe1"));				
		producto = DaoProductos.insertar(new Productos("Jamon", "Charcuteria", null, 2));				
		
		mostrarTodosUsuarios();
		mostrarTodosProductos();
	
		DaoUsuarios.modificar(new Usuario(usuario.getId(), "pepemodi@modi.modi","modipepe","mopepe"));
		DaoProductos.modificarPorID(new Productos("JamonModificado", "CharcuteriaModificado", producto.getId(), 3));
		
		mostrarTodosUsuarios();
		mostrarTodosProductos();
		
		DaoProductos.modificarPorNombre(new Productos("JamonModificado", "CharcuteriaModificado2", null, 4));
		
		mostrarTodosProductos();
		
		DaoUsuarios.borrar(usuario.getId());
		DaoProductos.borrar(producto.getId());
		
		mostrarTodosUsuarios();
		mostrarTodosProductos();
	}

	
	
	private static void mostrarTodosUsuarios() {
		ArrayList<Usuario> usuarios = DaoUsuarios.obtenerTodos();
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
	
	private static void mostrarTodosProductos() {
		ArrayList<Productos> productos = DaoProductos.obtenerTodos();
		for (Productos producto : productos) {
			System.out.println(producto);
		}
	}
}
