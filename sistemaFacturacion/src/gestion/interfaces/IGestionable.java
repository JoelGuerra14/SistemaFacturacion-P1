package gestion.interfaces;

public interface IGestionable {
	public void agregar();
	public void editar();
	public void eliminar();
	public void eliminar(int id);
	public void cargarDataDb();
}
