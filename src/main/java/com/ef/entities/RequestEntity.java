package com.ef.entities;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor @Entity @Table(name = "requests")
public class RequestEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "date") private String date;
    @Column(name = "ip") private String ip;
    @Column(name = "method") private String method;
    @Column(name = "code") private Integer code;
    @Column(name = "header") private String header;
}
