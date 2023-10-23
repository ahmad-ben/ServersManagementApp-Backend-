package com.servers_manager_app.servers_management.resource;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servers_manager_app.servers_management.enums.ServerStatusEnum;
import com.servers_manager_app.servers_management.models.Response;
import com.servers_manager_app.servers_management.models.Server;
import com.servers_manager_app.servers_management.services.implementations.ServerServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
  private final ServerServiceImpl serverServiceImpl; 

  @GetMapping("/list")
  public ResponseEntity<Response> getServers(){

    return ResponseEntity.ok(
      Response
        .builder()
          .timeStamp(now())
          .data(of(
            "servers", 
            serverServiceImpl.list(30)
          ))
          .message("Servers retrieved")
          .status(OK)
          .statusCode(OK.value())
        .build()
    );

  }

  @GetMapping("/ping/{ipAddress}")
  public ResponseEntity<Response> pingServer(
    @PathVariable("ipAddress") String ipAddress) throws IOException{
    Server server = serverServiceImpl.ping(ipAddress);
    return ResponseEntity.ok(
      Response
        .builder()
          .timeStamp(now())
          .data(of(
            "server", 
            server
          ))
          .message(
            server.getServerStatus() == ServerStatusEnum.SERVER_UP ?
              "Ping success" : 
              "Ping failed"
          )
          .status(OK)
          .statusCode(OK.value())
        .build()
    );

  }

  @PostMapping("/save")
  public ResponseEntity<Response> saveServer (
    @RequestBody @Valid Server server) {

    return ResponseEntity.ok(
      Response
        .builder()
          .timeStamp(now())
          .data(of(
            "server", 
            serverServiceImpl.create(server)
          ))
          .message("Server created")
          .status(CREATED)
          .statusCode(CREATED.value())
        .build()
    );

  }

  @GetMapping("/get/{id}")
  public ResponseEntity<Response> getServer (
    @PathVariable("id") Long id) {

    return ResponseEntity.ok(
      Response
        .builder()
          .timeStamp(now())
          .data(of(
            "server", 
            serverServiceImpl.get(id)
          ))
          .message("Server retrieved")
          .status(OK)
          .statusCode(OK.value())
        .build()
    );

  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Response> deleteServer (
    @PathVariable("id") Long id) {

    return ResponseEntity.ok(
      Response
        .builder()
          .timeStamp(now())
          .data(of(
            "deleted", 
            serverServiceImpl.delete(id)
          ))
          .message("Server deleted")
          .status(OK)
          .statusCode(OK.value())
        .build()
    );

  }

  @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
  public byte[] getServerImage (
    @PathVariable("fileName") String fileName) throws IOException {

    return 
      Files.readAllBytes(
        Paths.get(System.getProperty("user.home") + "/Desktop/images/" + fileName)
      );

  }

  // STOP 1:06

}
