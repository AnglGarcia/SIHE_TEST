package com.coppel.sihe.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coppel.sihe.entity.Empleado;
import com.coppel.sihe.repository.EmpleadoRepository;


@Service
public class EmpleadoDetailsServiceImpl implements UserDetailsService {

    @Autowired
    EmpleadoRepository empleadoRepository;
	
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
     Empleado appUser = empleadoRepository.findById(Long.valueOf(id)).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
     return new User(appUser.getId().toString(), appUser.getPassword(), new ArrayList<>());
     
    }
    
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Empleado appUser = empleadoRepository.findByCorreo(email).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
        return new User(appUser.getId().toString(), appUser.getPassword(), new ArrayList<>());
        
    }
}