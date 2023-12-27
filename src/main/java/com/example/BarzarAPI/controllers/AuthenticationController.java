package com.example.BarzarAPI.controllers;

import com.example.BarzarAPI.dto.AuthenticationRequest;
import com.example.BarzarAPI.dto.AuthenticationResponse;
import com.example.BarzarAPI.services.jwt.UserDetailsServiceImpl;
import com.example.BarzarAPI.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@RestController
public class AuthenticationController {
  @Autowired
  private JwtUtil jwtUtil;
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @PostMapping("/authenticate")
  @CrossOrigin(origins = "http://localhost:3000")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
      HttpServletResponse response) throws IOException {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
          authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect username or password!");
    } catch (DisabledException disabledException) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
      return null;
    }

    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

    final String username = userDetails.getUsername();

    String roles = "user";
    if ("admin@gmail.com".equals(username) || "admin@gmail.com".equals(username)) {
      roles = "admin";
    }

    final String jwt = jwtUtil.generateToken(userDetails.getUsername());

    return ResponseEntity.ok(new AuthenticationResponse(username, jwt, roles));
  }
}