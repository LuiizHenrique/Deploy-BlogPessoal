package org.Generation.blogPessoal.seguranca;

import java.util.Optional;

import org.Generation.blogPessoal.model.Usuario;
import org.Generation.blogPessoal.repositary.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{
 
	@Autowired
	 private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName ) throws UsernameNotFoundException {

	Optional<Usuario> user = userRepository.findByUsuario(userName);
	if (user.isPresent()) {
		return new UserDetailsImpl(user.get());
	} else {
		throw new UsernameNotFoundException("Usuario não existe");
	}
	
	
//	user.orElseThrow(() -> new UsernameNotFoundException(userName + "Usuário não existe"));
	//return user.map(UserDetailsImpl :: new).get();
}
}