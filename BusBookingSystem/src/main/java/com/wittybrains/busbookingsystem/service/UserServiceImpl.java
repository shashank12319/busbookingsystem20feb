package com.wittybrains.busbookingsystem.service;


//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.wittybrains.busbookingsystem.model.MyUser;
//import com.wittybrains.busbookingsystem.repository.BusRepository;
//import com.wittybrains.busbookingsystem.repository.UserRepository;
//
//
//
//
//
//@Service
//public class UserServiceImpl implements UserDetailsService{
//@Autowired
//    private  UserRepository userRepository;
//    public void create(MyUser user) {
//        user = userRepository.save(user);
//    }
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	MyUser user = userRepository.findByEmail(username);
//	if(user==null) {
//		throw new UsernameNotFoundException(username);
//	}
//	return new org.springframework.security.core.userdetails.User(username, user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER")));
//	}
//
//	
//}
