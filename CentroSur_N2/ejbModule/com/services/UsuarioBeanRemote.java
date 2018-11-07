package com.services;

import java.util.LinkedList;

import javax.ejb.Remote;

import com.entities.Usuario;
import com.enums.PerfilUsuario;
import com.excepciones.TamboException;

@Remote
public interface UsuarioBeanRemote {

	void altaUsuario(String nombre, String apellido, String clave, PerfilUsuario perfil, String nombreUsuario) throws TamboException;
	void eliminarUsuario(Long  idUsuario) throws TamboException;
	Usuario buscarUsuarioLogin(String nombreUsuario, String clave)throws TamboException;
	Usuario buscarUsuario(String nombreUsuario) throws TamboException;
	Usuario buscarApellidoUsuario(String apellidoUsuario) throws TamboException;
	public LinkedList<Usuario> obtenerUsuarios()throws TamboException; 
	
	
	
	
}
