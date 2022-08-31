package com.orbisexample.demo.controllers;

import com.orbisexample.demo.config.AuthenticationRequest;
import com.orbisexample.demo.config.AuthenticationResponse;
import com.orbisexample.demo.config.JwtUtil;
import com.orbisexample.demo.config.MyUserDetailsService;
import com.orbisexample.demo.dtos.UserDto;
import com.orbisexample.demo.entities.Car;
import com.orbisexample.demo.entities.User;
import com.orbisexample.demo.services.CarService;
import com.orbisexample.demo.services.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@SecurityScheme(name = "Basic Authentication", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@SecurityScheme(name = "bearerAuth", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT")
@OpenAPIDefinition(servers = {@Server(url = "http://localhost:8080/"),
        @Server(url = "http://localhost:8081/")}, info = @Info(title = "Users API", version = "1.1", description = "Users information"))
@RestController
@Slf4j

public class Controller {


    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CarService carService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final URL url = new URL("http://localhost:8080");
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final MyUserDetailsService userDetailsService;

    @Autowired
    public Controller(CarService carService, ModelMapper modelMapper, UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, MyUserDetailsService userDetailsService) throws MalformedURLException {

        this.modelMapper = modelMapper;
        this.userService = userService;
        this.carService = carService;
        this.authenticationManager = authenticationManager;

        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping({"/hello"})
    public String firstPage() {
        return "Hello World";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    @SecurityRequirement(name = "Basic Authentication")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "post welcome User")
    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityRequirement(name = "Basic Authentication")
    @Operation(summary = "post welcome Admin")
    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @Operation(summary = "Get User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @SecurityRequirement(name = "Basic Authentication")
    @GetMapping("/users/{id}")
    public User findById(@Parameter(description = "id of User to be searched")
                         @PathVariable long id) {
        return userService.findUserByID(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Operation(summary = "Add new User")
    @SecurityRequirement(name = "Basic Authentication")
    @PostMapping("/users/add")
    public void addUser(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setRoles("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
    }

    @Operation(summary = "Get all Users")
    @SecurityRequirement(name = "Basic Authentication")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userService.findAllUsers();
    }


    @GetMapping("/users/id/{username}")
    public Long getUserIdByUserName(@PathVariable String username){
    return userService.findUserByUsername(username).getId();
    }

    @GetMapping("/users/cars/{id}")
    public List<Car> getUsersCarsById(@PathVariable Long id){
        return userService.getUsersCarsById(id);
    }


}


