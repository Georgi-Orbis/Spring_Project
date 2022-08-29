package com.orbisexample.demo.controllers;
import com.orbisexample.demo.dtos.UserDto;
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
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@SecurityScheme(name = "Basic Authentication", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(servers = {@Server(url = "http://localhost:8080/"),
@Server(url = "http://localhost:8081/")},info = @Info(title = "Users API", version = "1.1", description = "Users information"))
@RestController
@Slf4j
public class Controller {



    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CarService carService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final URL url = new URL("http://localhost:8080");



    @Autowired
    public Controller(CarService carService, ModelMapper modelMapper, UserService userService) throws MalformedURLException  {

        this.modelMapper = modelMapper;
        this.userService = userService;
        this.carService = carService;

    }

    @GetMapping("/")
    public String home() {

        return ("<h1>Welcome</h1>");
    }

    @SecurityRequirement(name = "Basic Authentication")
    @Operation(summary = "post welcome User")
    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @SecurityRequirement(name = "Basic Authentication")
    @Operation(summary = "post welcome Admin")
    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @Operation(summary = "Get User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
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




}


