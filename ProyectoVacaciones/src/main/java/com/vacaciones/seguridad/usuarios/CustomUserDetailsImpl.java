package com.vacaciones.seguridad.usuarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vacaciones.modelos.Empleado;


public class CustomUserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Empleado empleado;
	public CustomUserDetailsImpl(Empleado empleado) {
		super();
		this.empleado = empleado;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = empleado.getRol().getDescripcion();
		System.out.println("ROLE "+ role);
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		if (role != null && role.trim().length() > 0) {
			if (role.equalsIgnoreCase("admin")) {
				authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			} else if (role.equalsIgnoreCase("jefe")) {
				authList.add(new SimpleGrantedAuthority("ROLE_JEFE"));
			} else if (role.equalsIgnoreCase("empleado")) {
				authList.add(new SimpleGrantedAuthority("ROLE_EMPLEADO"));
			}
		}
		return authList;
	}

	@Override
	public String getPassword() {
		return empleado.getContrasena();
	}

	@Override
	public String getUsername() {
		return empleado.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getName() {
		return empleado.getNombre();	
	}

}
