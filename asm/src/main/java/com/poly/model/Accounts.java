package com.poly.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts")
public class Accounts implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "username")
    private String username;

    private String password;
    private String fullname;
    private String email;
    private String photo;
    private boolean activated;

  
   
    
    @Column(columnDefinition = "BIT") 
    private boolean admin;
}
